package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.HeightNoise;
import jaredbgreat.climaticbiome.util.SpatialNoise;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SurvivalIslandMaker extends LandmassMaker {
    
    
    SurvivalIslandMaker(int rx, int ry, SpatialNoise rand, 
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
                        = getFromCenter(out[(i * size) + j], scale.inv);
                out[(i * size) + j].val = (int)out[(i * size) + j].height;
                out[(i * size) + j].height /= 10.0;
                out[(i * size) + j].height = out[(i * size) + j].height 
                        + Math.abs(heights[i][j]);
            }
        
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(out[(i * size) + j].height > 0.6) {
                    out[(i * size) + j].rlBiome = 1;
                    if(ConfigHandler.extraBeaches || 
                    		out[(i * size) + j].height < beachThreshold) {
                        out[(i * size) + j].beach = true;
                    }
                } else {
                    out[(i * size) + j].rlBiome = 0;                    
                }
            }
        
        return out;
    }
    
    private double getFromCenter(ChunkTile tile, double inv) {
    	double x = tile.getTX() * inv;
    	double z = tile.getTZ() * inv;
    	double dist = Math.sqrt((x * x) + (z * z));
    	return Math.max(0.0, ConfigHandler.sisize - dist);    	
    }    
    
}
