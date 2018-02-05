package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.ClimaticBiomePlacement;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

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
				return forest;
			case 4:
			case 5:
				return hills;
			case 6:
			case 7:
				return pinewoods;
			case 8:
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
		forest    = ClimaticBiomePlacement.configHandler.getBiomeID("warm forest");
		hills     = ClimaticBiomePlacement.configHandler.getBiomeID("warm forest hills");
		pinewoods = ClimaticBiomePlacement.configHandler.getBiomeID("pinewoods");
	}
}
