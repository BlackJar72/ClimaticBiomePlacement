package jaredbgreat.climaticbiome.generation.generator;

import java.util.Random;

/**
 *
 * @author Jared Blackburn
 */
public class RiverMaker {
    private final MapMaker  map;
    private final Random rand;
    private final BasinNode[] starts, ends;
    private final int num;
    
    public RiverMaker(MapMaker mapIn, long seed, Region region) {
        map = mapIn;
        rand = new Random(seed);
        num = rand.nextInt(3) + 5;  
        starts = region.getBasins(num, true);
        ends = region.getBasins(num, false);      
    }
    
    public void build() {
        for(int i = 0; i < num; i++) {
            River river = new River(starts[i], ends[i], map);
            river.build(rand);
        }
    }
}
