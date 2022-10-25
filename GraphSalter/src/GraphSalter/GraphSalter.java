package GraphSalter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class GraphSalter {
	
	public GraphSalter(String inputFile, String outputFile, int rangeLow, int rangeHigh, int maxAdd, int minAdd) throws FileNotFoundException, IOException {
		ArrayList<Integer> unsmoothInts = readCSV(inputFile);
		ArrayList<Integer> smoothInts = salt(unsmoothInts, rangeLow, rangeHigh, maxAdd, minAdd);
		generateCSV(smoothInts, outputFile);
		
		
	}
	
	
	public ArrayList<Integer> readCSV(String filename) throws FileNotFoundException, IOException {
		ArrayList<Integer> ints = new ArrayList<Integer>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    br.readLine(); //get rid of header
		    while ((line = br.readLine()) != null) {
		    	String[] splitLine = line.split(",");
		    	int y = Integer.parseInt(splitLine[1]);
		        ints.add(y);
		    }
		}
		return ints;
	}
	
	public ArrayList<Integer> salt(ArrayList<Integer> data, int rangeLow, int rangeHigh, int max, int min){
		ArrayList<Integer> saltData = new ArrayList<Integer>();
		Random rand = new Random();
		rangeLow = rangeLow - 1;
		rangeHigh = rangeHigh - 1;

		for(int i = 0; i<data.size();i++) {
			if(i>=rangeLow&&i<=rangeHigh) {
				int newNum = data.get(i)+ rand.nextInt(max-min)+ min;
				saltData.add(newNum);
			}
			else {
				saltData.add(data.get(i));
				
			}
		}
		return saltData;
	}
	
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

