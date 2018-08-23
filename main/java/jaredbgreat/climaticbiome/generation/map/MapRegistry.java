package jaredbgreat.climaticbiome.generation.map;

import jaredbgreat.climaticbiome.generation.cache.Cache;
import net.minecraft.util.math.BlockPos;


/**
 * This is a class for storing and handling biome maps.  It 
 * has nothing to do with Forge registries, its just the 
 * best name I came up with.  Essentially, its acts more as 
 * a manager that handle a a cache of maps in use while 
 * coordinating the generation of new maps and the loading of 
 * stored maps. 
 * 
 * @author JaredBGreat
 */
public class MapRegistry {
	private Cache<RegionMap> data;
	
	
	
	public RegionMap getMap(int x, int z) {
		RegionMap out = data.get(x, z);
		if(out == null) {
			out = new RegionMap(x, z);
			initializeMap(out);
			data.add(out);
		}
		return out;
	}
	
	
	public RegionMap getMap(BlockPos pos) {
		return getMap((pos.getX() - 2048) / 4096, (pos.getZ() - 2048) / 4096);
	}
	
	
	public RegionMap getMapFromBlockCoord(int x, int z) {
		return getMap((x - 2048) / 4096, (z - 2048) / 4096);
	}
	
	
	public RegionMap getMapFromChunkCoord(int x, int z) {
		return getMap((x - 128) / 256, (z - 128) / 256);
	}
	
	
	private void initializeMap(RegionMap map) {
		// FIXME / TODO: A temporary stand in; attach to 
		//               real generator.
		for(int i = 0; i < 4096; i++)
			for(int j = 0; j < 4096; j++) {
				map.setBiomeExpress(1, i, j); // One is a stand in (I think plains).
			}
	}

}
