package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

/**
 * This is used to seperate two biomes within the same biomal area based on a 
 * noise function derived from cellular automata.  This still done on the scale 
 * of chunks, and uses a simple form of cellular automata originally designed 
 * to create cavelike areas for 2D roguelike games.  This is the correct way 
 * to separate plateaus from valleys in the mesa biomes (or similar).
 */
public class GetNoiseBiome implements IBiomeSpecifier {
	private int bound;
	private IBiomeSpecifier a, b;
	
	
	public GetNoiseBiome init(IBiomeSpecifier lowerBiome, int cutOff, IBiomeSpecifier upperBiome) {
		a = lowerBiome;
		bound = cutOff;
		b = upperBiome;
		return this;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		if(tile.getNoise() < bound) {
			return a.getBiome(tile);
		} else {
			return b.getBiome(tile);
		}
	}

}
