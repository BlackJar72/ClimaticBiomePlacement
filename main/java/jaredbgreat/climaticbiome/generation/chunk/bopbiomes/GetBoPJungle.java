package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPJungle implements IBiomeSpecifier {
	private static int rainforest, trainforest;
	
	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() & 1) == 0) {
			return rainforest;
		} else {
			return trainforest;
		}
	}
	
	
	public static void init() {
		rainforest  = Biome.getIdForBiome(BOPBiomes.rainforest.get());
		trainforest = Biome.getIdForBiome(BOPBiomes.tropical_rainforest.get());
	}

}
