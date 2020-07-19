package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.biomeprovider.ChunkTile;

public interface IBiomeSpecifier {	
	public long getBiome(ChunkTile tile);
	public boolean isEmpty();

}
