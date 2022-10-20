package mh;
/**
 * 
 * @author Daniel Woods
 * 
 * Main driver file for Monte Hall Simulation Program
 *
 */

public class Main {

	public static void main(String[] args) {
		final int numGames = 40;
		final int numSims = 40;
		final boolean shouldSwitch = true;
		MonteHallDriver mh = new MonteHallDriver(numGames, shouldSwitch);
	}

}
