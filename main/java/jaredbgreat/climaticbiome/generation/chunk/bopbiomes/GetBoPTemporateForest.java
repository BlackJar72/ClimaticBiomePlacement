package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPTemporateForest implements IBiomeSpecifier {
	private static int cherries, orchard, redwood, woodland;

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() & 1) == 0) {
			return woodland;
		}
		switch(tile.getBiomeSeed() % 3) {
			case 0:
				return cherries;
			case 1:
				return orchard;
			case 2:
				return redwood;
			default:
				return woodland;
		}
	}
	
	
	public static void init() {
		
	}

}
