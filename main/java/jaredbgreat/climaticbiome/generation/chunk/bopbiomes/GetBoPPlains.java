package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPPlains implements IBiomeSpecifier {
	private static int flowers, moor, lavender, pasture;
	
	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 5) == 0) {
			return pasture;
		}
		switch(tile.getBiomeSeed() % 3) {
			case 0:
				return flowers;
			case 1: 
				return lavender;
			case 3:
			default:
				return moor;
		}
	}
	
	
	public static void init() {
		
	}

}
