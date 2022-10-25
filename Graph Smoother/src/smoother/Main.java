package smoother;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String inputFile = "Test.csv";
		String outputFile = "TestSmooth.csv";
		int rangeLow = 15;
		int rangeHigh = 50;
		
		GraphSmoother gs = new GraphSmoother(inputFile, outputFile, rangeLow, rangeHigh);

	}

}
