package randCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @author Daniel Woods
 * 
 * This is an extra program I wrote for this class to generate the random CSV for the salter and Smoother
 * 
 */

public class RandomCSV {
	public RandomCSV(int rows, int maxNum, int minNum, String filename) {
		int[] randRows = generateRandInt(rows, maxNum, minNum);
		generateCSV(rows, randRows, filename);
		
		
	}
	
	private int[] generateRandInt(int rows, int maxNum, int minNum) {
		int[] randRows= new int[rows];
		Random rand = new Random();
		for(int i = 1;i<rows;i++) {
			randRows[i]=rand.nextInt(minNum, maxNum+1);
		}
		return randRows;
	}
	
	
	
	private void generateCSV(int rows, int[] randInt, String filename) {
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
			for(int i = 1; i<randInt.length;i++) {
				//concatenate a string to write to file
				String line = "" + i + "," + randInt[i];
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
