package jaredbgreat.climaticbiome.generation.chunk.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * An item list selecting items.  Its a very simple system, favoring 
 * speed over memory (which should be small for this kind of use anyway).
 * 
 * @author Jared Blackburn
 */
public class SelectionIntList {
	private final int[] items;
	
	/**
	 * The constructor takes a list of formated strings (either {val:p" 
	 * or "val p") consisting of an integer (a biome ID in its intended 
	 * use) and the number of chances (p above, though not technically a 
	 * probability).  Each value val will be inserted into the backing 
	 * array p times.
	 * 
	 * @param in
	 */
	public SelectionIntList(List<String> in) {
		int max = 0;
		int val, index;
		StringTokenizer tokens;
		for(String s : in) {
			tokens = new StringTokenizer(s, " :=");
			tokens.nextToken();
			max += Integer.getInteger(tokens.nextToken());
		}
		items = new int[max];
		max = index = 0;
		for(String s : in) {
			tokens = new StringTokenizer(s, " :");
			val = Integer.getInteger(tokens.nextToken());
			max += Integer.getInteger(tokens.nextToken());;
			for(; index < max; index++) {
				items[index] = val;
			}
		}
	}
	
	
	/**
	 * This will retrieve a int from the array.  It is technically not 
	 * internally randomized, and simply returns the value at the input  
	 * as an index if the index is within the arrays bounds, or the 
	 * remainder of the input divided by the array length otherwise.  
	 * 
	 * The intended use of this is to use a randomized input to select 
	 * an output.
	 * 
	 * @param in
	 * @return
	 */
	public int getRandomItem(int in) {
		return items[in % items.length];
	}

}
