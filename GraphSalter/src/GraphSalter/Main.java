package GraphSalter;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String inputFile = "Test.csv";
		String outputFile = "TestSalt.csv";
		int rangeLow = 15;
		int rangeHigh = 50;
		int maxAdd = 10;
		int minAdd = -10;
		
		GraphSalter gs = new GraphSalter(inputFile, outputFile, rangeLow, rangeHigh, maxAdd, minAdd);

	}

}
