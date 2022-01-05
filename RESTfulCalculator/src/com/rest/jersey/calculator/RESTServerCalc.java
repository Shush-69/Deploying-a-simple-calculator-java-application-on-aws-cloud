package com.rest.jersey.calculator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/calc")
@Consumes(MediaType.APPLICATION_JSON)
public class RESTServerCalc {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//Invocation string: http://localhost:8080/RESTfullCalculatorMvn/calc?a=6&b=7&op=add 
	public Response Calculator(@QueryParam("a") double a, @QueryParam("b") double b, @QueryParam("op") String op) throws JSONException {
		JSONObject json = new JSONObject();
		double c;
		switch(op)
	    {
	        case "+":
	        case "add":
	            c = a + b;
	            break;
	        case "-":
	        case "sub":
	            c = a - b;
	            break;
	        case "*":
	        case "mul":
	            c = a * b;
	            break;
	        case "/":
	        case "div":
	            if (b==0) {
	        		return Response.status(400).entity("400 Arithmetic exception: division by zero error").build();	
	            } 
	            else {c = a / b;};
	            break;
	        default:
        		return Response.status(400).entity("400 Invalid Operator").build();	
	    }
		json.put("a", a);
		json.put("b", b);
		json.put("op", op);
		json.put("c", c);
		String result = ""+json;
		return Response.status(200).entity(result).build();	
	}
	
	@GET
	@Path("/sub/{a}/{b}")
	@Produces(MediaType.TEXT_XML)
	//Invocation string: http://localhost:8080/RESTfullCalculatorMvn/calc/sub/5/9
	public String sub(@PathParam("a") double a, @PathParam("b") double b) {
		return  "<?xml version=\"1.0\"?>\n" +
				"<calc>\n" +
				"  <operands>\n" +
			    "    <a>" + a + "</a>\n" +
			    "    <b>" + b + "</b>\n" +
				"  </operands>\n" +
			    "  <operation>sub</operation>\n" +
				"  <result>\n" +
			    "     <c>" + (a - b) + "</c>\n" +
				"  </result>\n" +
			    "</calc>";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	//Invocation string: http://localhost:8080/RESTfullCalculatorMvn/calc/add?a=5&b=9
	public Response addJSON(@QueryParam("a") double a, @QueryParam("b") double b) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("a", a);
		json.put("b", b);
		json.put("op", "add");
		json.put("c", a + b);
		String result = ""+json;
		return Response.status(200).entity(result).build();	
	}

	@POST
	@Path("/mul")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//Invocation string: http://localhost:8080/RESTfullCalculatorMvn/calc/mul
	public Response mulJSON(Operands operands) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("a", operands.getA());
		json.put("b", operands.getB());
		json.put("op", "mul");
		json.put("c", operands.getA() * operands.getB());
		String result = ""+json;
		return Response.status(200).entity(result).build();	
	}

	@POST
	@Path("/div")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//Invocation string: http://localhost:8080/RESTfullCalculatorMvn/calc/div
	public Response divJSON(Operands operands) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("a", operands.a);
		json.put("b", operands.b);
		json.put("op", "div");
		json.put("c", operands.getA() / operands.getB());
		String result = ""+json;
		return Response.status(200).entity(result).build();	
	}
}