package jaredbgreat.climaticbiome.generation.chunk;

public enum TerrainType {
	VARIABLE,    // This would be the norm if using non-biome height/scale data
	VANILLA,     // This would force the used of biome height/scale data
	MOUNTIANOUS, // For mountains, this adds variable and vanilla height, uses the higher scale
	STEEP;       // Like vanilla but reduces some averaging; for plateaus and technical biomes
}
