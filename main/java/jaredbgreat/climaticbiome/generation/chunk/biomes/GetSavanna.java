package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetSavanna implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int out;
		if((tile.getBiomeSeed() % 4) == 0) {
			out = 36;			
		} else {
			out = 35;
		}
		return out;
	}

}
