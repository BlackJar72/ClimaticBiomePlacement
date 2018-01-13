package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

/**
 * This class will repressent a biome, along with all its potential 
 * variants (if any).  That is hill variants, mutated variants, and 
 * and mountain biome that might sometimes take its place.  This 
 * should be the final terminating end in the biome search tree.
 * 
 * Note that mesa plateau / valley distinction and similar (including 
 * islands) should use GetNoiseBiome to find their distiction; 
 * GetNoiseBiome should then return an instance of either this or 
 * possibly a GetSingleBiome instance.
 */
public class GetLeafBiome implements IBiomeSpecifier {
	private int main, hill, mutated, mhill, mountain;
	
	
	public GetLeafBiome init(int main, int hill, int mutated, int mhill, int mountain) {
		this.main     = main;
		this.hill     = hill;
		this.mutated  = mutated;
		this.mhill    = mhill;
		this.mountain = mountain;
		return this;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		int determiner = (tile.getBiomeSeed() & 0x7f000000) >> 24;
		if((determiner & 0x70) == 0x70) {
			if(((determiner & 0x01)  == 1) 
					&& (((determiner & 0x2) == 2) || (tile.getNoise() > 4))) {
				return mhill;
			} else {
				return mutated;
			}
		} else {
			if((determiner & 0x01) == 1) {
				if((determiner & 0x6) == 6) {
					return mountain;
				} else if(((determiner & 0x02) == 0x02) || (tile.getNoise() > 4)) {
					return hill;
				}
			return main;
			} 
		}
		return main;
	}

}
