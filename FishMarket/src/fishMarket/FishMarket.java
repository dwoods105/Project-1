package fishMarket;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is the main program for Fish Market. It has 2 constructors. One constructor takes
 * no weight, and the other one does. The constructors populate the array automatically,
 * they then call the generateCSV function and generate a CSV file with the seafood
 *
 * @author Daniel Woods
 */

public class FishMarket {
	//Global Variables
	double priceShrimp;
	double priceScallop;
	double priceCrab;
	double priceFish;
	double maxWeight;
	double fishWeight = 1;
	double shrimpWeight = 1;
	double scallopWeight = 1;
	double crabWeight = 1;
	ArrayList<Seafood> seafoodArr;
	
	
	
	 /**
	    * Constructor one, takes all of the global parameters and calls generateSeafood() and 
	    * generateCSV(). At return, there is a new csv file in the root of the project. 
	    *
	    * @param  int parNum Seafood   the number of seafood to generate
	    * @param double parMaxWeight	The maximum weight of a seafood item
	    * @param String file			The name of the file to output
	    * @param double priceShrimp		The price of a Shrimp
	    * @param double priceScallop	The price of a Scallop
	    * @param double priceFish		The price of a Fish
	    * @param double priceCrab		The Price of a Crab
	    * 
	    * 
	    * @return         Nothing
	    */
public FishMarket(int parNumSeafood, double parMaxWeight, String file, double priceShrimp, double priceScallop, double priceFish, double priceCrab) { 
	//set global variables
	this.priceShrimp = priceShrimp;
	this.priceScallop = priceScallop;
	this.priceFish = priceFish;
	this.priceCrab = priceCrab;
	maxWeight = parMaxWeight;
	//call generateSeafood with the specified number of Seafood objects
	seafoodArr = generateSeafood(parNumSeafood);
	//call generateCSV with the ArrayList created in the last step and the file name specified
	generateCSV(seafoodArr, file);
	
}
/**
 * @Overload
 * Constructor two, takes all of the global parameters and calls generateSeafood() and 
 * generateCSV(). At return, there is a new csv file in the root of the project. This constructor 
 * includes weights
 *
 * @param  int parNum Seafood   the number of seafood to generate
 * @param double parMaxWeight	The maximum weight of a seafood item
 * @param String file			The name of the file to output
 * @param double priceShrimp		The price of a Shrimp
 * @param double priceScallop	The price of a Scallop
 * @param double priceFish		The price of a Fish
 * @param double priceCrab		The Price of a Crab
 * @param int fishWeight		Selection weight of a fish
 * @param int shrimpWeight		Selection weight of a shrimp
 * @param int scallopWeight		Selection weight of a scallop
 * @param int CrabWeight		Selection weight of a crab
 * 
 * 
 * @return         Nothing
 */
public FishMarket(int parNumSeafood, double parMaxWeight, String file, double priceShrimp, double priceScallop, double priceFish, double priceCrab, int fishWeight, int shrimpWeight, int scallopWeight, int crabWeight) { 
	// set global variables
	this.priceShrimp = priceShrimp;
	this.priceScallop = priceScallop;
	this.priceFish = priceFish;
	this.priceCrab = priceCrab;
	this.fishWeight = fishWeight;
	this.shrimpWeight = shrimpWeight;
	this.scallopWeight = scallopWeight;
	this.crabWeight = crabWeight;
	maxWeight = parMaxWeight;
	//call generateSeafood method with number of requested Seafood objects
	seafoodArr = generateSeafood(parNumSeafood);
	//call generateCSV method with ArrayList generated in last step and file name specified
	generateCSV(seafoodArr, file);
	
}

/**
 * Takes the number of seafood requested in a list and generates an ArrayList of 
 * Objects with the requested seafood. 
 *
 * @param int numSeafood		Number of seafood objects you would like in arraylist
 * @return         Arraylist with requested number of seafood objects. 
 */

private ArrayList<Seafood> generateSeafood(int numSeafood) {
	Random rand = new Random();
	ArrayList<Seafood> seafood = new ArrayList<Seafood>();
	//for loop that generates a random integer and creates an object based on that integer
	for(int i=0;i<=numSeafood;) {
		//tempType holds the integer which determines which seafood will be created
		int tempType = rand.nextInt(4);
		
		//0 = crab, create a new crab and append to array list
		if(tempType == 0) {
			//for loop will generate more than 1 crab if a weight is specified
			for(int j = 0; j<crabWeight;j++) {
				if (i>numSeafood) break; //check if we are at our num so we dont go over while weighting
				
				//generate a weight that is a double and below maxWeight
				double weight = rand.nextDouble()*(maxWeight-.01)+.01;
				//construct new crab
			Crab addCrab = new Crab(weight,priceCrab);
			//add to arraylist
			seafood.add(addCrab);
			//increment i every time loop runs, required because of the weighting
				i++;
		}
		}
		//1 = Scallop, create a new scallop and append to array list
		else if (tempType == 1) {
			//for loop will generate more than 1 scallop if a weight is specified
			for(int j = 0; j<scallopWeight;j++) {
				if (i>numSeafood) break;//check if we are at our num so we dont go over while weighting
								//generate a weight that is a double and below maxWeight
				double weight = rand.nextDouble()*(maxWeight-.01)+.01;
				//construct new scallop
			Scallop addScallop = new Scallop(weight,priceScallop);
			//add to arraylist
			seafood.add(addScallop);
			//increment i every time loop runs, required because of the weighting
				i++;

		}
		}
		//2 = Shrimp, create a new shrimp and append to array list
		else if (tempType == 2){
			//for loop will generate more than 1 shrimp if a weight is specified
			for(int j = 0; j<shrimpWeight;j++) {
				if (i>numSeafood) break;//check if we are at our num so we dont go over while weighting
				
				//generate a weight that is a double and below maxWeight
				double weight = rand.nextDouble()*(maxWeight-.01)+.01;
				//construct new shrimp
			Shrimp addShrimp = new Shrimp(weight,priceShrimp);
			//add to arraylist
			seafood.add(addShrimp);
			//increment i every time loop runs, required because of the weighting
				i++;
		}
		}
		//3 = Fish, create a new fish and append to array list
		else if (tempType == 3) {
			//for loop will generate more than 1 fish if a weight is specified
			for(int j = 0; j<fishWeight;j++) {
				if (i>numSeafood) break;//check if we are at our num so we dont go over while weighting
				
				//generate a weight that is a double and below maxWeight
				double weight = rand.nextDouble()*(maxWeight-.01)+.01;
				//construct new fish
			Fish addFish = new Fish(weight,priceCrab);
			//add to arraylist
			seafood.add(addFish);
			//increment i every time loop runs, required because of the weighting
				i++;
			}
			
		}
	}
	return seafood;
}

/**
 * This method creates (or overwrites if file does not exist) a file in CSV format with
 * headers on the top and data from a new object on each new line.
 * 
 * @throws IOException,		 can throw an error if it is unable to write to specified path. If user doesn't supply
 * a path and just types in a name, then it will wither create a new file or overwrite an existing
 * file if the name matches.
 * 
 * @param ArrayList<Seafood> seafood		ArrayList of Seafood objects
 * @param String filename					The name of the file to write to
 * @return Nothing, method is void
 */
private void generateCSV(ArrayList<Seafood> seafood, String filename) {
	//Try Catch for IOException
	try {
		FileWriter file = new FileWriter(filename);
		BufferedWriter writer = new BufferedWriter(file);
		//open BufferedWriter and FileWriter
		
		//print headers to file
		writer.write("index, type, weight, price");
		//go to a new line
		writer.newLine();
		//loop through all items on the array list
		for(int i = 1; i<seafood.size();i++) {
			//concatenate a string to write to file
			String line = "" + i + ", " + seafood.get(i).getLine();
			//write the line
			writer.write(line);
			//go to a new line
			writer.newLine();
			
		}
		//close the buffered writer
		writer.close();
		//close the file writer
		file.close();
		
		
	} catch (IOException e) {
		//print out a statement if file is unable to be created or altered
		System.out.println("Unable to write file");
	}
	
	
	
}
/**
 * This method iterates over the ArrayList of seafood and prints out the data on a 
 * new line for each object in the ArrayList. 
 *
 * @return    String, headers and data seperated by newline characters for objects and commas for data
 * belonging to the same object. 
 */

public String toString() {
	String bldStr = "index, type, weight, price\n"; //add headers to string
	//iterate over all items in ArrayList
	for(int i = 0; i < seafoodArr.size();i++) {
		//Add all the arraylist items to the String
		bldStr += "" + i + ", " + seafoodArr.get(i).getLine()+ "\n";
	}
	return bldStr;
	
}

}
