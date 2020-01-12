package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.util.Logging;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * A river generator.
 * 
 * @author Jared Blackburn
 */
public class River {
    final int MAX;
    MapMaker map;
    BasinNode begin, end;
    int id, x, z, oc, dstx, dstz;
    final Deque<ChunkTile> Q;
    SizeScale scale;
    boolean merged;
    ChunkTile[] possible;
    
    
    public River(int riverId, BasinNode high, int endX, int endZ, 
                MapMaker mapIn, SizeScale sc) {
        map = mapIn;
        id = riverId;
        scale = sc;
        x = high.x * sc.whole - map.getXoff();
        z = high.z * sc.whole - map.getXoff();
        dstx = endX;
        dstz = endZ;
        MAX = (MapMaker.RSIZE * sc.whole) - 2;
        Q = new ArrayDeque<>();
        merged = false;
        begin = high;
        possible = new ChunkTile[8];
    }
    
    
    /**
     * Creates the river based on the parameters provided in the constructor.
     * 
     * First, find the rivers course, then trace back over it to widen the 
     * river.
     * 
     * @param r 
     */
    public void build(Random r) {
    	if(((x * MapMaker.RSIZE * scale.whole) + z) < 0) {
    		Logging.logError("Tried to make river starting out side map! "
    				+ "\n\r x = " + x + ", z = " + z + ", index = "
    				+ ((x * MapMaker.RSIZE * scale.whole) + z));
    		return;
    	}
        ChunkTile t = map.getTile(x, z);
        while(!shouldEnd(t)) {
            t.river = id;
            Q.add(t);
            t = findNext(t, r);
        }
        for(ChunkTile tr : Q) {
            // Must be done after the river has found a course; otherwise 
            // it blocks itself from moving in either positive direction.
            makeRiver(tr);
        }
    }
    
    /**
     * This finds the next tile to move the river into.  First, it eliminates 
     * tiles already occupied by the river to prevent backtracking.  Then 
     * it pick the best remaining option.  If there are no tiles available 
     * (i.e., the river has cornered itself) it will move randomly until it 
     * finds a way out and can progress again.
     * 
     * @param t
     * @param r
     * @return 
     */
    private ChunkTile findNext(ChunkTile t, Random r) {
        int n = 0;
        possible[0] = null;
        if(testIfUsed(map.getTile(t.x + 1, t.z + 1) , n)) n++;
        if(testIfUsed(map.getTile(t.x + 1, t.z), n)) n++;
        if(testIfUsed(map.getTile(t.x + 1, t.z - 1), n)) n++;
        if(testIfUsed(map.getTile(t.x, t.z + 1), n)) n++;
        if(testIfUsed(map.getTile(t.x, t.z - 1), n)) n++;
        if(testIfUsed(map.getTile(t.x - 1, t.z + 1), n)) n++;
        if(testIfUsed(map.getTile(t.x - 1, t.z), n)) n++;
        if(testIfUsed(map.getTile(t.x - 1, t.z - 1), n)) n++;
        ChunkTile out = possible[0];
        for(int i = 1; i < n; i++) {
            if(getAdjusted(possible[i]) > getAdjusted(out)) out = possible[i];
        }
        if(out == null) {
            out = map.getTile(t.x + r.nextInt(3) - 1, t.z + r.nextInt(3) - 1);
        }
        return out;
    }
    
    /**
     * Get the overall goodness of a tile as the next step to move, considering 
     * the changes in fake height along with a pull in desired direction.
     * 
     * @param t
     * @return 
     */
    private double getAdjusted(ChunkTile t) {
        // The right balance of directionaly and downward flow is required.
        // Too much directionality results in unbelievable straight line 
        // rivers; too much downward flow results in short rivers with too 
        // many lakes which often go nowhere.
        // Like many things in procedural generation this is more art than 
        // science -- the formula is derived by testing to see what looks good.
        return (Math.sqrt(((t.x - dstx) * (t.x - dstx))
                    + ((t.z - dstz) * (t.z - dstz))) 
                        / (10.0 + (10.0 * scale.fract))) 
                    - t.height;
    }
    
    /**
     * Tells if a giver tile is already occupied by the current river.
     * This actually returns true if the tile does not contain the 
     * river (i.e., is a good tile to enter), or false otherwise.
     * 
     * @param t
     * @param n
     * @return false if occupied, true if available
     */
    private boolean testIfUsed(ChunkTile t, int n) {
        boolean out = !(t.river == id);
        if(out) possible[n] = t;
        return out;
    }
    
    /**
     * Tells if the river has reached its a stopping point and should stop 
     * generating.
     * 
     * @param t
     * @return 
     */
    private boolean shouldEnd(ChunkTile t) {
        if(merged || (t == null)) {
            return true;
        } else {
            // Allow the river to progress a bit after hitting "ocean" to 
            // discourage immediately ending in a tiny body of water.
            if(t.height < 0.5) {
                oc++;
            } else {
                oc = 0;
            }
            // Allow one more move after hitting another river to encourage 
            // full merging.
            merged = (t.river > 0) && (t.river != id); 
            return ((t.height < 0.5) && (oc > (8 * scale.whole))) 
                    || outOfBounds(t.x, t.z);
        }
    }
    
    /**
     * Safety bounds checking to keep the river from trying to leave the map.
     * 
     * @param x
     * @param z
     * @return 
     */    
    private boolean outOfBounds(int x, int z) {        
        return (x <= 1) || (z <= 1) 
                ||(x >= MAX) || (z >= MAX);
    }
    
    /**
     * Widens the river around the given tile.  This is required to prevent 
     * diagonals where the river is block as well as to create a good, wide,
     * fully navigable waterway.
     * 
     * @param t 
     */
    private void makeRiver(ChunkTile t) {
        map.getTile(t.x + 1, t.z).river = id;
        map.getTile(t.x + 1, t.z + 1).river = id;
        map.getTile(t.x, t.z + 1).river = id;        
    }
    
}
