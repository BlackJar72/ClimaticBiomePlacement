package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPPark implements IBiomeSpecifier {
	private static int grove, lakes, shrubs;

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 2) {
			case 0: 
				return grove;
			case 1:
				return lakes;
			default:
				return shrubs; // Error!
		}
	}
	
}
