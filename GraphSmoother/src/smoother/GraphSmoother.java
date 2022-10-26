package smoother;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
* @author Daniel Woods
* Graph Smoother is a function that takes a CSV file and replaces the values in it with the average of the value above it and the value below it
*
 */
public class GraphSmoother {
	/**
	 * GraphSmoother has a single constructor that calls all the functions necessary to write the new file. It writes the file in the 
	 * local directory
	 * 
	 * 
	 * @param inputFile		String name of the file you want it to read
	 * @param outputFile	String name of the file you want it to write to
	 * @param rangeLow		int of the bottom index you would like to start at
	 * @param rangeHigh		int of the top index you would like to stop at
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GraphSmoother(String inputFile, String outputFile, int rangeLow, int rangeHigh) throws FileNotFoundException, IOException {
		ArrayList<Integer> unsmoothInts = readCSV(inputFile);
		ArrayList<Integer> smoothInts = smooth(unsmoothInts, rangeLow, rangeHigh);
		generateCSV(smoothInts, outputFile);
		
		
	}
	
	/**
	 * readCSV reads the CSV and returns an arraylist of only the y values
	 * 
	 * @param filename 		String name of the file you want to read
	 * @return				ArrayList of integers in the Y column of the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Integer> readCSV(String filename) throws FileNotFoundException, IOException {
		ArrayList<Integer> ints = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    br.readLine(); //get rid of header
		    while ((line = br.readLine()) != null) { //read every line in the CSV
		    	String[] splitLine = line.split(",");//split the line by the comma. put both pieces in StringArray
		    	int y = Integer.parseInt(splitLine[1]); //convert the y value to an int
		        ints.add(y); //append the int to an arrayList
		    }
		}
		return ints;
	}
	/**
	 * smooth takes the array list of integers and does the average calculations. It returns a new arrayList of integers 
	 * with the new values
	 * 
	 * @param data			ArrayList of integers with the Y values you would like to smooth
	 * @param rangeLow		int of the bottom index you would like to start at
	 * @param rangeHigh		int of the top index you would like to stop at
	 * @return ArrayList of integers with the new smoothed values
	 */
	
	public ArrayList<Integer> smooth(ArrayList<Integer> data, int rangeLow, int rangeHigh){
		ArrayList<Integer> smoothData = new ArrayList<Integer>();
		rangeLow = rangeLow - 1;
		rangeHigh = rangeHigh - 1;
		
		int low = 0;
		int high = 0;
		// iterate through the list
		for(int i = 0; i<data.size();i++) {
			//if we are in the proper specified range
			if(i>=rangeLow&&i<=rangeHigh) {
				//this if is for the first entry so we dont go out of bounds
				if(i == 0) {
					low = data.get(i);
					high = data.get(i+1);
					smoothData.add((high+low)/2);
					
				}
				//this if is for the last entry so we dont go out of bounds
				else if (i==data.size()-1) {
					low = data.get(i-1);
					high = data.get(i);
					smoothData.add((high+low)/2);
					
				}
				//This is for every index in between. 
				else {
					low = data.get(i-1); //get the value below
					high = data.get(i+1); //get the value above
					smoothData.add((high+low)/2); //append average to the new list
				}
			}
			else {
				smoothData.add(data.get(i));
				
			}
		}
		return smoothData;
	}

	/**
	 * generateCSV takes an ArrayList of integers and prints them next to their index in a csv
	 * @param ints		Arraylist of integers 	
	 * @param filename	String filename of the output file
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
