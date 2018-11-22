package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import net.minecraft.world.biome.Biome;

public class GetSubtropicalForest implements IBiomeSpecifier {
	private static int forest;
	private static int hills;
	private static int pinewoods;

	@Override
	public int getBiome(ChunkTile tile) {
		// FIXME: These IDs should be configurable and retrieved from a variable.
		int seed = tile.getBiomeSeed();
		if((seed % 5) == 0) {
			return EnumBiomeType.ALPINE.specifier.getBiome(tile);
		}
		switch(seed % 12) {
			case 0:
			case 2:
			case 3:
			case 4:				
				return forest;
			case 5:
			case 6:
				return hills;
			case 7:
				return pinewoods;
			case 9:
				if((seed % 7) == 0) {
					return 157;
				} else {
					return 29;
				}
			case 10:
				return 6;
			case 11:
				return 1;
			default:
				return forest;
		}
	}
	
	
	public static void init() {
		forest    = Biome.getIdForBiome(ModBiomes.warmForest);
		hills     = Biome.getIdForBiome(ModBiomes.warmForestHills);
		pinewoods = Biome.getIdForBiome(ModBiomes.pineWoods);
	}
}
