package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPIsland implements IBiomeSpecifier {
	public static int flower, origin, obeach, tropical, volcanic;

	@Override
	public int getBiome(ChunkTile tile) {
		if(((tile.getBiomeSeed() % 31) == 0) 
				&& (tile.getVal() < 4) 
				&& (tile.getNoise() > 4)) {
			return volcanic;
		}
		int temp = tile.getTemp();
		if(temp < 4) return getFrozenIsland(tile);
		if(temp < 8) return getColdIsland(tile);
		if(temp < 12) return getCoolIsland(tile);
		if(temp < 16) return getMidIsland(tile);
		if(temp < 21) return getWarmIsland(tile);
		return getHotIsland(tile);
	}
	
	
	public int getFrozenIsland(ChunkTile tile) {
		EnumBiomeType.findLandBiome(tile);
		return tile.getRlBiome().specifier.getBiome(tile);
	}
	
	
	public int getColdIsland(ChunkTile tile) {
		EnumBiomeType.findLandBiome(tile);
		return tile.getRlBiome().specifier.getBiome(tile);		
	}
	
	
	public int getCoolIsland(ChunkTile tile) {
		EnumBiomeType.findLandBiome(tile);
		return tile.getRlBiome().specifier.getBiome(tile);		
	}
	
	
	public int getMidIsland(ChunkTile tile) {
		int seed  = tile.getBiomeSeed();
		int noise = tile.getNoise();	
		if(((tile.getWet() > 3) && (tile.getBiomeSeed() % 5) == 0)) {
			if(noise < (3 + (seed % 1) - ((seed % 2) - 1))) {
				return obeach;
			}
			return origin;
		}
		EnumBiomeType.findLandBiome(tile);
		return tile.getRlBiome().specifier.getBiome(tile);		
	}
	
	
	public int getWarmIsland(ChunkTile tile) {
		if(((tile.getWet() > 4) && (tile.getBiomeSeed() % 5) == 0)) {
			return flower;
		}
		EnumBiomeType.findLandBiome(tile);
		return tile.getRlBiome().specifier.getBiome(tile);		
	}
	
	
	public int getHotIsland(ChunkTile tile) {
		if(((tile.getWet() > 3) && (tile.getBiomeSeed() % 3) == 0)) {
			return tropical;
		}
		EnumBiomeType.findLandBiome(tile);
		return tile.getRlBiome().specifier.getBiome(tile);
		
	}
	
	
	
	public static void init() {
		flower   = Biome.getIdForBiome(BOPBiomes.flower_island.get());
		obeach   = Biome.getIdForBiome(BOPBiomes.origin_beach.get());
		origin   = Biome.getIdForBiome(BOPBiomes.origin_island.get());
		tropical = Biome.getIdForBiome(BOPBiomes.tropical_island.get());
		volcanic = Biome.getIdForBiome(BOPBiomes.volcanic_island.get());
	}

}
