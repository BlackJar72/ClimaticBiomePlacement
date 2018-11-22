package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetFrozenOcean implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		return 10;
	}

}
