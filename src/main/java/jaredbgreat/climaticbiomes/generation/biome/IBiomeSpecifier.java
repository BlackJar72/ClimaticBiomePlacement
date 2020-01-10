package jaredbgreat.climaticbiomes.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public interface IBiomeSpecifier {	
	public long getBiome(ChunkTile tile);
	public boolean isEmpty();

}
