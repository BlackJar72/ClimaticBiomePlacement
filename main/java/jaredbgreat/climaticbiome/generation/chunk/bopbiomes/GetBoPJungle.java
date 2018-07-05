package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPJungle implements IBiomeSpecifier {
	private static int rainforest, trainforest;
	
	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() & 1) == 0) {
			return rainforest;
		} else {
			return trainforest;
		}
	}
	
	
	public static void init() {
		
	}

}
