package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPSubtropicalForest implements IBiomeSpecifier {
	private static int bamboo, mystic, sacred;

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 5) {
			case 0:
			case 1:
				return bamboo;
			case 2:
			case 3:
				tile.nextBiomeSeed();
				return EnumBiomeType.SFOREST.specifier.getBiome(tile);
			case 4:
			default:
				if((tile.getBiomeSeed() & 1) == 0) {
					return mystic;
				} else {
					return sacred;
				}
		}
	}
	
	
	public static void init() {
		bamboo = Biome.getIdForBiome(BOPBiomes.bamboo_forest.get());
		mystic = Biome.getIdForBiome(BOPBiomes.mystic_grove.get());
		sacred = Biome.getIdForBiome(BOPBiomes.sacred_springs.get());		
	}

}
