package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPColdPlains implements IBiomeSpecifier {
	private static int tundra;
	
	@Override
	public int getBiome(ChunkTile tile) {
		return tundra;
	}

	
	public static void init() {
		
	}
	
}
