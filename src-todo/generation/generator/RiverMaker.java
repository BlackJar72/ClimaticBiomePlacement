package jaredbgreat.climaticbiomes.generation.generator;

import java.util.Random;

/**
 * A class the generate rivers.
 *
 * @author Jared Blackburn
 */
public class RiverMaker {
    private final MapMaker  map;
    private final Random rand;
    private final BasinNode[] starts, ends;
    private final int num;
    private final SizeScale scale;
    private final int regx, regz;
    
    
    public RiverMaker(MapMaker mapIn, long seed, Region region, int x, int z, SizeScale sc) {
        map = mapIn;
        rand = new Random(seed);
        num = rand.nextInt(3 + sc.log) + 5 + (sc.whole * sc.log);  
        starts = region.getBasins(num, true);
        ends = region.getBasins(num, false);      
        scale = sc;
        regx = x;
        regz = z;
    }


	public void build() {
        for(int i = 0; i < num; i++) {
            int endX, endZ;
            switch(rand.nextInt(4)) {
                case 0:
                    endX = rand.nextInt(MapMaker.RSIZE * scale.whole);
                    endZ = 0;
                    break;
                case 1:
                    endX = rand.nextInt(MapMaker.RSIZE * scale.whole);
                    endZ = MapMaker.RSIZE * scale.whole;
                    break;
                case 2:
                    endZ = rand.nextInt(MapMaker.RSIZE * scale.whole);
                    endX = 0;
                    break;
                case 3:
                    endZ = rand.nextInt(MapMaker.RSIZE * scale.whole);
                    endX = MapMaker.RSIZE * scale.whole;
                    break;
                default: // Should never actually happern
                    endX = ends[i].x;
                    endZ = ends[i].z;
            }
            River river = new River(i + 1, starts[i], endX, endZ, map, scale);
            river.build(rand);
        }
    } 
}
