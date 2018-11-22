package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetCoolPlains implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 3) == 0) {
			return 140;
		}
		return 1;
	}

}
