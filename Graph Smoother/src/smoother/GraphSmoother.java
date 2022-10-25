package smoother;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class GraphSmoother {
	
	public GraphSmoother(String inputFile, String outputFile, int rangeLow, int rangeHigh) throws FileNotFoundException, IOException {
		ArrayList<Integer> unsmoothInts = readCSV(inputFile);
		ArrayList<Integer> smoothInts = smooth(unsmoothInts, rangeLow, rangeHigh);
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
	
	public ArrayList<Integer> smooth(ArrayList<Integer> data, int rangeLow, int rangeHigh){
		ArrayList<Integer> smoothData = new ArrayList<Integer>();
		rangeLow = rangeLow - 1;
		rangeHigh = rangeHigh - 1;
		
		int low = 0;
		int high = 0;
		for(int i = 0; i<data.size();i++) {
			if(i>=rangeLow&&i<=rangeHigh) {
				if(i == 0) {
					low = data.get(i);
					high = data.get(i+1);
					smoothData.add((high+low)/2);
					
				}
				else if (i==data.size()-1) {
					low = data.get(i-1);
					high = data.get(i);
					smoothData.add((high+low)/2);
					
				}
				else {
					low = data.get(i-1);
					high = data.get(i+1);
					smoothData.add((high+low)/2);
				}
			}
			else {
				smoothData.add(data.get(i));
				
			}
		}
		return smoothData;
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
