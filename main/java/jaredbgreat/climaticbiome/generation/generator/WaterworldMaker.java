package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.HeightNoise;
import jaredbgreat.climaticbiome.util.SpatialHash;

public class WaterworldMaker extends LandmassMaker {
    
    
    WaterworldMaker(int rx, int ry, SpatialHash rand, 
                BasinNode[] basinAr, SizeScale sc, int startW,
                int xoffIn, int zoffIn) {
    	super(rx, ry, rand, basinAr, sc, startW, xoffIn, zoffIn);
    }
    
    
    public ChunkTile[] generate() {
    	double beachThreshold = 0.70;
        ChunkTile[] out = new ChunkTile[size * size];
        for(int i = 0; i < size; i++) 
            for(int j = 0; j < size; j++) {
                out[(i * size) + j] 
                        = new ChunkTile(i, j, xoff, zoff);
        }        
        HeightNoise heightmaker 
                = new HeightNoise(random, size, 16 * scale.whole, 1.0, regx, regz);
        double[][] heights = heightmaker.process(0);
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                out[(i * size) + j].height
                	= edgeFix(out[(i * size) + j], 
                        BasinNode.summateEffect(basins, 
                                out[(i * size) + j], 
                        scale.inv));
                out[(i * size) + j].val = (int)out[(i * size) + j].height;
                out[(i * size) + j].height /= 20.0;
                out[(i * size) + j].height = ((out[(i * size) + j].height 
                        + (heights[i][j] / 2.0) + 0.5) 
                        * out[(i * size) + j].height) 
                        + heights[i][j];
                out[(i * size) + j].rlBiome = 0;
            }
        return out;
    } 
    
}
