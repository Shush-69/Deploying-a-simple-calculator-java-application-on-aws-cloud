package com.rest.jersey.calculator;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Entity;
import org.glassfish.jersey.client.ClientConfig;

public class RESTClientCalc {

	private static final String webServiceURI = "http://localhost:8080/RESTfulCalculator";

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
		WebTarget webTarget = client.target(serviceURI);
//-----------------------			
		System.out.println("\nUsing GET QUERY parameters to invoke a RESTful service returning JSON output:");
					
		System.out.println(webTarget.path("calc").queryParam("a", "74").queryParam("b", "12").queryParam("op", "+").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class));
//-----------------------			
		System.out.println("\nUsing GET PATH parameters to invoke a RESTful service returning XML output:");
		
		System.out.println(webTarget.path("calc").path("sub").path("67").path("45").request()
				.accept(MediaType.TEXT_XML).get(String.class));
//-----------------------			
		System.out.println("\nUsing GET both PATH and QUERY parameters to invoke a RESTful service returning JSON output:");
					
		System.out.println(webTarget.path("calc").path("add").queryParam("a", "56").queryParam("b", "34").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class));
//-----------------------
		System.out.println("\nUsing POST with Raw JSON parameters to invoke a RESTful service returning JSON output:");

		double a = 8.0;
		double b = 6.0;
		String request = String.format("{\"a\":%s,\"b\":%s}", a, b);		
		// String request = ("{\"a\":" + a + ",\"b\":" + b + "}");			
		System.out.println(webTarget.path("calc").path("div").request()
				.post(Entity.entity(request, MediaType.APPLICATION_JSON), String.class));			
//-----------------------
		System.out.println("\nUsing POST with Object2JSON parameters to invoke a RESTful service returning JSON output:");

		Operands operands = new Operands();
	   	operands.setA(6.0);
	   	operands.setB(7.0);
		System.out.println(webTarget.path("calc").path("mul").request(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON).post(Entity.json(operands), String.class));
	}

}
