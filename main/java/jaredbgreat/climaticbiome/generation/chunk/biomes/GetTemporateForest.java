package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetTemporateForest implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		if((seed % 5) == 0) {
			return EnumBiomeType.ALPINE.specifier.getBiome(tile);
		}
		switch(seed % 12) {
			case 0:
			case 2:
			case 3:
				if((seed % 3) == 0) {
					return 18;
				} else return 4;
			case 4:
				return 132;
			case 5:
			case 6:
				if((seed % 3) == 0) {
					return 28;
				} else if ((seed % 5) == 0) {
					return 155;
				} else return 27;
			case 7:
			case 8:
				if((seed % 7) == 0) {
					return 157;
				} else return 29;
			case 9: 
				return 6;
			case 10:
				return 1;
			default:
				return 4;
		}
	}

}
