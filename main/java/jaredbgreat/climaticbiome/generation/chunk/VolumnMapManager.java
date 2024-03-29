package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.cache.WeakCache;
import jaredbgreat.climaticbiome.util.SpatialHash;
import static jaredbgreat.climaticbiome.generation.chunk.HeightMapArea.*;

public class VolumnMapManager {	
	private static final WeakCache<VolumnMapArea> cache = new WeakCache<>();
	
	
	public WeakCache<VolumnMapArea> getCache() {
		return cache;
	}
	
	
	public VolumnMapArea getChunkNoise(int x, int z, SpatialHash rand) {
    	x += 0x4fffffff; z += 0x4fffffff;
		int ax = x / CSIZE;
		int az = z / CSIZE;
		VolumnMapArea area = cache.get(ax, az);
		if(area == null) {
			area = new VolumnMapArea(ax, az, rand, this);
			cache.add(area);			
		}
		return area;		
	}

}
