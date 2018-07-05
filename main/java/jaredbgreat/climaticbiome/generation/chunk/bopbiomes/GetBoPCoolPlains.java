package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPCoolPlains implements IBiomeSpecifier {
	private static int meadow, moor;

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 3) {
			case 0:
			case 1:
				return meadow;
			case 2:
			default:
				return moor;
		}
	}

	
	public static void init() {
		
	}
	
}
