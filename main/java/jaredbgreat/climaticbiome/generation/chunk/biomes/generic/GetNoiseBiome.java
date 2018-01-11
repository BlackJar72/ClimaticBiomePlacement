package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetNoiseBiome implements IBiomeSpecifier {
	private final int a, bound, b;
	
	
	public GetNoiseBiome(int lowerBiome, int cutOff, int upperBiome) {
		a = lowerBiome;
		bound = cutOff;
		b = upperBiome;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		if(tile.getNoise() < bound) {
			return a;
		} else {
			return b;
		}
	}

}
