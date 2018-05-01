package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetTaiga implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		switch(seed % 8) {
			case 0:
			case 1:
			case 2:
				if((seed % 5) == 0) {
					return 158;
				} else {
					return 30;
				}
			case 3:
			case 4:
			case 5:
				if((seed % 5) == 0) {
					return 160;
				} else {
					return 32;
				}
			case 6:
				return 30;
			case 7:
			default:
				return 31;
		}
	}

}
