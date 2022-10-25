package statsLibrary;

import java.util.List;

public class StatsLib {
	public StatsLib() {}
	
	public int average(List<Integer> data) {
		int total = 0;
		for(int i : data) {
			total += data.get(i);
		}
		return total/data.size();
	}

}
