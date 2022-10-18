package fishMarket;
/**
 * This class is a parent class of all the different seafood, it is only called by it's children, 
 * but it could be called by itself if desired. 
 *
 * @author Daniel Woods
 */
public class Seafood {
	double weight;
	double price;
	String type;
	
	public Seafood(double weight, double price, String type) {
	this.weight = weight;
	this.price = price;
	this.type = type;
}
	 /**
	    * return a string of all the parameters of the seafood it has been constructed 
	    * to represent 
	    *
	    * @return         String summarizing the seafood
	    */
	public String getLine() {
		return "" + type + ", " + weight + ", " + price;
		
	}
}
