package jaredbgreat.climaticbiome.generation.chunk.biomes.generic;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class BiomeTable implements IBiomeSpecifier {
	public static final int SIZE = 50;
	private IBiomeSpecifier[] data;
	
	
	public BiomeTable init(IBiomeSpecifier[] in) {
		data = in;
		return this;
	}
	
	
	public int getBiome(ChunkTile tile) {
		return data[(tile.getTemp() * 10) + tile.getWet()].getBiome(tile);
	}

}
