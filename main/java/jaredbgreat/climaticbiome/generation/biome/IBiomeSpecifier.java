package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

public interface IBiomeSpecifier {	
	public long getBiome(ChunkTile tile);
	public boolean isEmpty();

}
