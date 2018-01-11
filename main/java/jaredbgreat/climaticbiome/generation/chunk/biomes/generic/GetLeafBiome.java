package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

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
