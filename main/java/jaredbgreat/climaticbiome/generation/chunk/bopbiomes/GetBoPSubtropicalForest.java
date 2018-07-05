package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPSubtropicalForest implements IBiomeSpecifier {
	private static int bamboo, mysticgrove, sacredspring;

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 5) {
			case 0:
			case 1:
				return bamboo;
			case 2:
			case 3:
				tile.nextBiomeSeed();
				return EnumBiomeType.SFOREST.specifier.getBiome(tile);
			case 4:
			default:
				if((tile.getBiomeSeed() & 1) == 0) {
					return mysticgrove;
				} else {
					return sacredspring;
				}
		}
	}
	
	
	public static void init() {
		
	}

}
