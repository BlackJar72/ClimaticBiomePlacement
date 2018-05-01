package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetAlpine implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		boolean plus = (seed % 7) < tile.getWet();
		switch(seed % 3) {
			case 0:
				if(plus) {
					return 34;
				} else {
					return 3;
				}
				case 1:
					if(plus) {
						return 162;
					} else {
						return 131;
					}
			case 2:
			default:
				if(plus) {
					if(tile.getNoise() < 5) return 34;
					else return 162;
				} else {
					if(tile.getNoise() < 5) return 3;
					else return 131;
				}
		} 
	}

}
