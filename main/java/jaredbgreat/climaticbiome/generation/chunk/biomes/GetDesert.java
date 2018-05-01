package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetDesert implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 10) {
			case 0:
			case 1:
			case 2:
			case 3:
				return 2;
			case 4:
			case 5:
				return 17;
			case 6:
				return 130;
			case 7: 
			case 8: 
				if(tile.getNoise() > 4) {
					return 39;
				} else {
					return 37;
				}
			case 9:
				if(tile.getNoise() > 4) {
					return 38;
				} else {
					return 37;
				}
			default:
				return 2;				
		}
	}

}
