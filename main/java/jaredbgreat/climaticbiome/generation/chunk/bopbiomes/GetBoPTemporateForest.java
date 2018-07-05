package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPTemporateForest implements IBiomeSpecifier {
	private static int cherries, orchard, redwood, woodland;

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() & 1) == 0) {
			return woodland;
		}
		switch(tile.getBiomeSeed() % 3) {
			case 0:
				return cherries;
			case 1:
				return orchard;
			case 2:
				return redwood;
			default:
				return woodland;
		}
	}
	
	
	public static void init() {
		cherries = Biome.getIdForBiome(BOPBiomes.cherry_blossom_grove.get());
		orchard  = Biome.getIdForBiome(BOPBiomes.orchard.get());
		redwood  = Biome.getIdForBiome(BOPBiomes.redwood_forest.get());
		woodland = Biome.getIdForBiome(BOPBiomes.woodland.get());
	}

}
