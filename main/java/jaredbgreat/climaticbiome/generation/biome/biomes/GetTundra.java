package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetTundra implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		switch(seed % 7) {
			case 0:
			case 1:
			case 2:
			case 3:
				if((seed % 5) == 0) {
					return 140;
				}
				else return 12;
			case 4:
			case 5:
				return 5;
			case 6:
				if((seed % 5) == 0) {
					return 158;
				} else return 30;
			default:
				return 5;
		}
	}

}
