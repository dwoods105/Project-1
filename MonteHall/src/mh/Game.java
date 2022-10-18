package mh;
import java.util.Random;

public class Game {
	int door;
	
	public Game(int door) {
		this.door=door;
	}
	
	public int getDoor() {
		return door;
	}
	public int getGoat() {
		Random rand = new Random();
		int TempDoor =door;
		do {
			TempDoor = rand.nextInt(3);
			
		}
		while(TempDoor==door);
		return TempDoor;
	}

}
