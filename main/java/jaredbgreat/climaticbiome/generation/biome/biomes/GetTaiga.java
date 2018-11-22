package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetTaiga implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		int temp = tile.getTemp() + (seed % 2);
		if(temp > 7) {
			switch(seed % 9) {
				case 0:
					if(temp > 8) {
						return 27;
					}
				case 1:
				case 2:
					return 5;
				case 3:
				case 4:
					if((seed % 5) == 0) {
						return 160;
					} else {
						return 32;
					}
				case 5:
				case 6:
					return 19;
				case 7:
				case 8:
					return 133;
				default: 
					return 5;
			}
		} else {
			switch(seed % 9) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
					return 30;
				case 5:
				case 6:
				case 7:
				case 8:
					return 158;
				default: 
					return 30;				
			}
		}
	}

}
