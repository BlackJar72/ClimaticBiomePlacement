package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.BiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetSubtropicalForest implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		// FIXME: These IDs should be configurable and retrieved from a variable.
		int seed = tile.getBiomeSeed();
		if((seed % 5) == 0) {
			return BiomeType.ALPINE.specifier.getBiome(tile);
		}
		switch(seed % 12) {
			case 0:
			case 2:
			case 3:
				return 66;
			case 4:
				return 69;
			case 5:
			case 6:
				return 68;
			case 7:
			case 8:
				if((seed % 7) == 0) {
					return 157;
				} else {
					return 29;
				}
			case 9:
				return 6;
			case 10:
				return 1;
			default:
				return 66;
		}
	}
}
