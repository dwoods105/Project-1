package fishMarket;
/**
 * This class is the main driver class for the Fish Market Program
 * You can specify with sufficient granularity different parameters
 * that are self explanatory from the variable name
 *
 *
 * @author Daniel Woods
 */

public class main {

	public static void main(String[] args) {
		final double priceShrimp = 4.99;
		final double priceScallop = 4.99;
		final double priceCrab = 4.99;
		final double priceFish = 4.99;
		final String file = "TEst.csv";
		final int fishWeight = 5; //5 times more likely to catch a fish
		final int scallopWeight = 1;
		final int crabWeight = 1;
		final int shrimpWeight = 1;
		final double maxWeight = 5.0;
		final int numSeafood = 300;
		
		//FishMarket fm = new FishMarket(numSeafood, maxWeight, file, priceShrimp, priceScallop, priceFish, priceCrab);
		FishMarket fmw = new FishMarket(numSeafood, maxWeight, file, priceShrimp, priceScallop, priceFish, priceCrab, fishWeight, shrimpWeight, scallopWeight, crabWeight);
		System.out.println(fmw.toString());
	}

}
