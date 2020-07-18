package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.NoiseMap;
import jaredbgreat.climaticbiome.util.SpatialHash;

public class SurvivalIslandMaker extends LandmassMaker {
    
    
    SurvivalIslandMaker(int rx, int ry, SpatialHash rand, 
                BasinNode[] basinAr, SizeScale sc, int startW,
                int xoffIn, int zoffIn) {
    	super(rx, ry, rand, basinAr, sc, startW, xoffIn, zoffIn);
    }
    
    
    public ChunkTile[] generate(ClimaticWorldSettings settings) {
    	double beachThreshold = 0.70;
        ChunkTile[] out = new ChunkTile[size * size];
        for(int i = 0; i < size; i++) 
            for(int j = 0; j < size; j++) {
                out[(i * size) + j] 
                        = new ChunkTile(i, j, xoff, zoff);
        }        
        NoiseMap heightmaker 
                = new NoiseMap(random, size, 16 * scale.whole, 1.0, regx, regz);
        double[][] heights = heightmaker.process(0);
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                out[(i * size) + j].height
                        = getFromCenter(out[(i * size) + j], scale.inv, settings);
                out[(i * size) + j].val = (int)out[(i * size) + j].height;
                out[(i * size) + j].height /= 10.0;
                out[(i * size) + j].height = out[(i * size) + j].height 
                        + Math.abs(heights[i][j]);
            }
        
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(out[(i * size) + j].height > 0.6) {
                    out[(i * size) + j].rlBiome = 1;
                    if(settings.extraBeaches || 
                    		out[(i * size) + j].height < beachThreshold) {
                        out[(i * size) + j].beach = true;
                    }
                } else {
                    out[(i * size) + j].rlBiome = 0;                    
                }
            }
        
        return out;
    }
    
    private double getFromCenter(ChunkTile tile, double inv, 
    			ClimaticWorldSettings settings) {
    	double x = tile.getTX() * inv;
    	double z = tile.getTZ() * inv;
    	double dist = Math.sqrt((x * x) + (z * z));
    	return Math.max(0.0, settings.sisize - dist);    	
    }    
    
}
