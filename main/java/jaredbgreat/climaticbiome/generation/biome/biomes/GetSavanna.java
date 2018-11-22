package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetSavanna implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed() % 6;
		if(seed == 0) {
			return 36;			
		} else if(seed < 3) {
			if(tile.getNoise() > 5){
				return 36;
			} else {
				return 35;
		 	}
		} else {
			return 35;
		}
	}

}
