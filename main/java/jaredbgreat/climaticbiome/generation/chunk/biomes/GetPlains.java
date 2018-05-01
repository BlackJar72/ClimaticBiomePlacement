package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetPlains implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		if((seed % 4) == 0) {
			return EnumBiomeType.ALPINE.specifier.getBiome(tile);
		}
		if((seed % 10) == 0) {
			return 129;
		}
		return 1;
	}

}
