package GraphSalter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Daniel Woods
 * 
 * Takes a csv list of x and y values and writes a csv file of x and y values with a random number added to it 
 * in a specified range
 */
public class GraphSalter {
	/**
	 * GraphSalter has one constructor that does all of the work. It calls all the needed methods in this class.
	 * 
	 * 
	 * @param inputFile		String name of the file you would like to salt	
	 * @param outputFile	String name of the output file
	 * @param rangeLow		index you would like to start at
	 * @param rangeHigh		index you would like to finish at
	 * @param maxAdd		maximum of the random number you would like to add
	 * @param minAdd		minimum of the random number you would like to add
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GraphSalter(String inputFile, String outputFile, int rangeLow, int rangeHigh, int maxAdd, int minAdd) throws FileNotFoundException, IOException {
		ArrayList<Integer> unsmoothInts = readCSV(inputFile);
		ArrayList<Integer> smoothInts = salt(unsmoothInts, rangeLow, rangeHigh, maxAdd, minAdd);
		generateCSV(smoothInts, outputFile);
		
		
	}

	/**
	 * readCSV reads the CSV and returns an arraylist of only the y values
	 * 
	 * @param filename String name of the file you would like to read
	 * @return ArrayList of integers of the y values
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
	
	public ArrayList<Integer> readCSV(String filename) throws FileNotFoundException, IOException {
		ArrayList<Integer> ints = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    br.readLine(); //get rid of header
		    while ((line = br.readLine()) != null) { //read every line in CSV
		    	String[] splitLine = line.split(","); // split the line at the comma and store values in a String Array
		    	int y = Integer.parseInt(splitLine[1]); // I only need the y value
		        ints.add(y); //add to the ArrayList
		    }
		}
		return ints;
	}
	/**
	 * Salt takes the ArrayList of integers and does the Salting. It returns an ArrayList of Integers that has 
	 * the specified range salted
	 * 
	 * @param data		ArrayList of Integers. These are the Y values you would like salted
	 * @param rangeLow	int The starting index of the values you would like to salt	
	 * @param rangeHigh	int The ending index of the values you would like to salt	
	 * @param max		int The highest random value we can salt by
	 * @param min		int the lowest random value we can salt by
	 * @return
	 */
	
	public ArrayList<Integer> salt(ArrayList<Integer> data, int rangeLow, int rangeHigh, int max, int min){
		ArrayList<Integer> saltData = new ArrayList<Integer>();
		Random rand = new Random();
		rangeLow = rangeLow - 1; //adjust for 0 index
		rangeHigh = rangeHigh - 1;

		for(int i = 0; i<data.size();i++) { //iterate thorough the index	
			if(i>=rangeLow&&i<=rangeHigh) { //if we are in the specified range
				int newNum = data.get(i)+ rand.nextInt(max-min)+ min; //generate and add that random integer
				saltData.add(newNum); //add the new integer to the new ArrayList
			}
			else {//if we are not in the specified range	
				saltData.add(data.get(i)); //add the old data to the list
				
			}
		}
		return saltData;
	}

	/**
	 * generateCSV takes an ArrayList on integers (y-values) and prints them on a line in a CSV next to their index
	 * @param ints		ArrayList of Integers (y-values)
	 * @param filename	Desired name of the output file
	 */
	
	private void generateCSV(ArrayList<Integer> ints, String filename) {
		//Try Catch for IOException
		try {
			FileWriter file = new FileWriter(filename);
			BufferedWriter writer = new BufferedWriter(file);
			//open BufferedWriter and FileWriter
			
			//print headers to file
			writer.write("x,y");
			//go to a new line
			writer.newLine();
			//loop through all items on the array list
			for(int i = 0; i<ints.size();i++) {
				//concatenate a string to write to file
				String line = "" + i + "," + ints.get(i);
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
}

