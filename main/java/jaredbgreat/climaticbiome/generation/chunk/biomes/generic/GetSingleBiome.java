package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

/**
 * This will return a single biome, and will not consider any variants.  
 * Generally this should not be used; in most case it GetLeafBiome should 
 * be used instead.  However, this may be useful in some situations where 
 * no variants are desired.  In such cases this is much more efficient, 
 * though a GetLeafBiome intance could also be created with in which all 
 * variants were the same.
 */
public class GetLeafBiome implements IBiomeSpecifier {
	private final int biome;
	
	
	public GetLeafBiome(int biome) {
		this.biome = biome;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		return biome;
	}

}
