package mh;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 
 * @author Daniel Woods
 * 
 * This program is the main driver for my Monte Hall Simulator program. The constructor takes 
 * the number of games, whether or not you would like to switch the door when the 
 * game master opens one.
 *
 */


public class MonteHallDriver {
	//Global Variable so that the simulations don't have to keep instantiating it
	Random rand = new Random();
	
	/**
	 * constructor, takes number of games and whether or not you would like to switch doors
	 * The constructor calls the games method, which returns an array list of games with
	 * a winner door stored in the object. It them calls simulate which generates a number 
	 * of wins. It calculates the percentage of the wins when it calls calculatePercentWin method
	 * and it prints a toString. 
	 * 
	 * @param numGames			int Number of games you would like to generate and play
	 * @param Switch			Boolean of whether you would like to switch doors or not
	 */
	public MonteHallDriver(int numGames, boolean Switch) {
		ArrayList<Game> listGames = games(numGames);
		int win = simulate(numGames, Switch, listGames);
		String percentWin = calculatePercentWin(numGames, win);
		System.out.println(toString(numGames, percentWin, win));
		
	}
	/**
	 * games method takes the number of games, generates the games and returns an 
	 * arraylist of them. The games already have their winner door determined.
	 * 
	 * @param numGames		int How many games would you like to generate
	 * @return ArrayList of games with their winner door stored in object
	 */
	
	private ArrayList<Game> games(int numGames){
		ArrayList<Game> game = new ArrayList<Game>();
		for(int i = 0; i<numGames; i++) {
			
			game.add(new Game(rand.nextInt(3)));
		}
		return game;
	}
	
	/**
	 * simulate method takes the number of games, the arraylist, the boolean of whether
	 * you should switch or not and it runs the simulation by picking a door and seeing if 
	 * it is a winner.
	 * 
	 * 
	 * @param numGames			int number of games in arraylist
	 * @param shouldSwitch		boolean of whether to switch doors when one is opened
	 * @param listGames			ArrayList<Game> ArrayList of the games object
	 * @return int number of games that were won
	 */
	
	private int simulate(int numGames, boolean shouldSwitch, ArrayList<Game> listGames){
		int win=0;
		if(!shouldSwitch) { //if the boolean is false and were not switching
			for(int i =0; i<numGames; i++) { //iterate through the list
				if(playWithoutChange(listGames.get((i)))) { //if the game is won
					win++; //increment win
				}
			}
		}
		else { //if we're not switching
			for(int i =0; i<numGames; i++) { // iterate thought the list
				if(playWithChange(listGames.get((i%numGames)))) { //if the game is won with switching
					win++; //increment win
				}
			}
		}
		return win;
	}
	
	/**
	 * playWithoutChange takes the Game object and plays the game without changing
	 * the door when the game master opens a door. It returns true for a win and
	 * false for a loss
	 * 
	 * @param parGame		game object to be simulated
	 * @return boolean true for a win and false for a loss
	 */
	
	private boolean playWithoutChange(Game parGame) {
		int selDoor = rand.nextInt(3); //select a door and random
		if(selDoor==parGame.getDoor()) { //if my selected door is the same as the win door
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * playWithChange takes the game object and runs the simulation when we change our door
	 * after the game master opens the door
	 * 
	 * @param parGame		Game object
	 * @return boolean true for win false for loss
	 */
	
	private boolean playWithChange(Game parGame) {
		int selDoor = rand.nextInt(3); //select a random door
		int goatDoor = parGame.getGoat(); // gets a door that isn't a winner
		while(selDoor == goatDoor) { // if the non winner door is the selected door, try again
			selDoor=rand.nextInt(3);
		}
		selDoor=3-selDoor-goatDoor;  //change door , will not pick selected door or goat door;
		if(selDoor==parGame.getDoor()) {  // if the selected door is the winner
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * calculateWin takes the number of games and the number of wins and
	 * returns a String containing the percentage of wins formatted to 2 decimal places
	 * 
	 * @param numGames		int Number of games played
	 * @param win			int Number of games won
	 * @return String containing the percentage of games won 
	 */
	private String calculatePercentWin(int numGames, int win) {
		DecimalFormat formatter = new DecimalFormat("#.##"); //format to 2 decomal places
		return formatter.format(((double)win/(double)numGames)*100);
	}
	
	/**
	 * @override
	 * 
	 * toString returns a nicely formatted string with the data from the simulation. 
	 * 
	 * @param numGames 			int number of games played 
	 * @param percentWin		String percent of games won (from calculatePercentWin)
	 * @param won				int Number of games won
	 * @return Nicely formatted String with info from the simulations
	 */
public String toString(int numGames, String percentWin, int won) {
	return"Number of Simulations: " + numGames +" Number won: "+ won +" Percent won " + percentWin;
	
}	
	

}
