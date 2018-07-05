package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPTaiga implements IBiomeSpecifier {
	private static int boreal, maple, snowycone;

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		int temp = tile.getTemp() + (seed & 1) - ((seed & 2) >> 1);
		if(temp > 5) {
			switch(seed % 3) {
				case 0:
					return maple;
				case 1:
				case 2:
				default:
					return boreal;
			}			
		} else {
			return snowycone;
		}
	}
	
	
	public static void init() {
		
	}

}
