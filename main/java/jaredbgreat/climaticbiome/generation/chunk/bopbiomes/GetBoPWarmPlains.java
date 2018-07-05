package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPWarmPlains implements IBiomeSpecifier {
	private static int prairie, pasture, grassland;
	
	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 5) {
			case 0:
			case 1:
				return prairie;
			case 2:
			case 3:
				return grassland;
			case 5:
			default:
				return pasture;
		}
	}

}
