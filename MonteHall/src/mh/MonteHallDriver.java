package mh;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MonteHallDriver {
	//Global Variable so that the simulations don't have to keep instantiating it
	Random rand = new Random();
	public MonteHallDriver(int numGames, boolean Switch, int numSims) {
		ArrayList<Game> listGames = games(numGames);
		int win = simulate(numGames,listGames.size(), Switch, listGames);
		String percentWin = calculatePercentWin(numSims, win);
		System.out.println(toString(numSims, percentWin, win));
		
	}
	
	private ArrayList<Game> games(int numGames){
		ArrayList<Game> game = new ArrayList<Game>();
		for(int i = 0; i<numGames; i++) {
			
			game.add(new Game(rand.nextInt(3)));
		}
		return game;
	}
	
	private int simulate(int numSims, int size, boolean shouldSwitch, ArrayList<Game> listGames){
		int win=0;
		if(!shouldSwitch) {
			for(int i =0; i<size; i++) {
				if(playWithoutChange(listGames.get((i%numSims)))) {
					win++;
				}
			}
		}
		else {
			for(int i =0; i<size; i++) {
				if(playWithChange(listGames.get((i%numSims)))) {
					win++;
				}
			}
		}
		return win;
	}
	
	private boolean playWithoutChange(Game parGame) {
		int selDoor = rand.nextInt(3);
		if(selDoor==parGame.getDoor()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean playWithChange(Game parGame) {
		int selDoor = rand.nextInt(3);
		int goatDoor = parGame.getGoat();
		while(selDoor == goatDoor) {
			selDoor=rand.nextInt(3);
		}
		selDoor=3-selDoor-goatDoor;  //change door , will not pick selected door or goat door;
		if(selDoor==parGame.getDoor()) {
			return true;
		}
		else {
			return false;
		}
	}
	private String calculatePercentWin(int numSims, int win) {
		DecimalFormat formatter = new DecimalFormat("#.##"); 
		return formatter.format(((double)win/(double)numSims)*100);
	}
public String toString(int numSim, String percentWin, int won) {
	return"Number of Simulations: " + numSim +" Number won: "+ won +" Percent won " + percentWin;
	
}	
	

}
