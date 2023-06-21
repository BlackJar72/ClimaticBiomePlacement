package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.cache.AbstractWeaklyCacheable;
import jaredbgreat.climaticbiome.util.ModMath;
import jaredbgreat.climaticbiome.util.SpatialHash;
import jaredbgreat.climaticbiome.util.HeightNoiseMap3D;

public class VolumnMapArea extends AbstractWeaklyCacheable {
	public static final int CSIZE = 8;          // Width and depth in chunks
	public static final int BSIZE = 16 * CSIZE; // Width and depth in blocks
	public static final int SCALE = 192;        // Multiplier for height
	
	private final HeightNoiseMap3D noiseMap;
	
	private int startx, startz;
	
	
	public VolumnMapArea(int x, int z, SpatialHash rand, VolumnMapManager manager) {
		super(x, z, manager.getCache());
		startx = x * CSIZE;
		startz = z * CSIZE;
		noiseMap = new HeightNoiseMap3D(16, 32, 4, 16, 4)
    						 .process(rand, x * BSIZE, z * BSIZE);
	}
	
	
	public int getHieght(int x, int y, int z, float[] biomeData) {
    	int px   = x - startx;
    	int pz   = z - startz;
    	int index1 = (ModMath.modRight(x, 16) * 16) + ModMath.modRight(y, 16);
    	return (int)((noiseMap.getValue(x,  y,  z) * 192 * biomeData[index1 + 256]) 
	    					+ (biomeData[index1] * 20) + 63);
	}
	
	
	public float getNoise(int x, int y, int z, float[] biomeData) {
    	int px   = x - startx;
    	int pz   = z - startz;
    	int index1 = (ModMath.modRight(x, 16) * 16) + ModMath.modRight(y, 16);
	    return noiseMap.getValue(x, y,  z);
	}
	

}
