package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetWarmPlains implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 3) == 0) {
			return EnumBiomeType.SAVANNA.specifier.getBiome(tile);
		}
		return EnumBiomeType.GRASS.specifier.getBiome(tile);
	}

}
