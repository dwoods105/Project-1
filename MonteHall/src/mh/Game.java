package mh;
import java.util.Random;

/**
 * 
 * @author Daniel Woods
 * 
 * Game object for monte hall simulation. Stores the winning door.
 *
 */

public class Game {
	//global variable, winning door
	int door;
	/**
	 * Only constructor takes an int of the winning door and stores it globally
	 * 
	 * @param door
	 */
	
	public Game(int door) {
		this.door=door;
	}
	/**
	 * getDoor()
	 * accessor method to get winning door
	 * 
	 * @return int winning door
	 */
	
	public int getDoor() {
		return door;
	}
	
	/**
	 * getGoat()
	 * 
	 * picks a door that is not the winning door and returns is
	 * @return int loser door
	 */
	public int getGoat() {
		Random rand = new Random();
		int TempDoor =door;
		do {
			TempDoor = rand.nextInt(3);
			
		}
		while(TempDoor==door); // if door is the winning door, generate a new one
		return TempDoor;
	}

}
