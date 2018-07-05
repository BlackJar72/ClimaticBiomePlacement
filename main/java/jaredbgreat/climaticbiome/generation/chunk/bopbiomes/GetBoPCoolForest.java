package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPCoolForest implements IBiomeSpecifier {
	private static int dead, coniferous, ominous, seasonal, shield;

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 10) {
			case 0:
			case 1:
				return coniferous;
			case 2:
			case 3:
				return seasonal;
			case 4:
				return dead;
			case 5:
				return ominous;
			case 6:
			case 7:
				return shield;
			case 8:
			case 9:
			default:
				return getOtherOption(tile);
				
		}
	}
	
	
	private int getOtherOption(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		int temp = tile.getTemp() + (seed & 1) - ((seed & 2) >> 1);
		tile.nextBiomeSeed();
		if(temp > 9) {
			return EnumBiomeType.FOREST.specifier.getBiome(tile);			
		} else {
			return EnumBiomeType.BTAIGA.specifier.getBiome(tile);
		}
		
	}
	
	
	public static void init() {
		
	}

}
