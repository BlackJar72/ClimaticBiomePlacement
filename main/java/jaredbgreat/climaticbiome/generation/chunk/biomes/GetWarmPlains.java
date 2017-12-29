package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.BiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetWarmPlains implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 3) == 0) {
			return BiomeType.SAVANNA.specifier.getBiome(tile);
		}
		return BiomeType.GRASS.specifier.getBiome(tile);
	}

}
