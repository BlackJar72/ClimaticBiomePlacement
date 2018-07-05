package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPVanillaTaiga implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		int temp = tile.getTemp() + (seed & 1) - ((seed & 2) >> 1);
		if(temp > 5) {
			switch(seed % 9) {
				case 0:
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
