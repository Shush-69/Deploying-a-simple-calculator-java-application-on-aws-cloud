import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Operands {
    public double a;
    public double b;

    public Operands() {    	
    } 
    
    public double getA() {
        return a;
    }
    
    public double getB() {
        return b;
    }
    
    public void setA(double a) { 
        this.a = a; 
    }
    
    public void setB(double b) { 
        this.b = b; 
    } 
}
