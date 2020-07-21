package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.cache.WeakCache;
import jaredbgreat.climaticbiome.util.SpatialHash;
import static jaredbgreat.climaticbiome.generation.chunk.HeightMapArea.*;

public class HeightMapManager {	
	private static final WeakCache<HeightMapArea> cache = new WeakCache<>();
	
	
	public WeakCache<HeightMapArea> getCache() {
		return cache;
	}
	
	
	public int[][] getChunkHieghts(int x, int z, SpatialHash rand, float[] biomeData) {
    	x += 0x4fffffff; z += 0x4fffffff;
		int ax = x / CSIZE;
		int az = z / CSIZE;
		HeightMapArea area = cache.get(ax, az);
		if(area == null) {
			area = new HeightMapArea(ax, az, rand, this);
			cache.add(area);			
		}
		return area.getChunkHieghts(x, z, biomeData);		
	}

}
