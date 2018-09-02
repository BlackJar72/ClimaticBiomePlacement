package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class LeafBiome implements IBiomeSpecifier {
	private final int biome;

	
	public LeafBiome(int biome) {
		this.biome = biome;
	}

	
	public LeafBiome(Biome biome) {
		this.biome = Biome.getIdForBiome(biome);
	}
	
	
	@Override
	public int getBiome(ChunkTile tile) {
		return biome;
	}

}
