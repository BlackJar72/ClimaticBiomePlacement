package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class LeafBiome implements IBiomeSpecifier {
	private final long biome;

	
	public LeafBiome(long biome) {
		this.biome = biome;
	}

	
	public LeafBiome(Biome biome) {
		this.biome = Biome.getIdForBiome(biome);
	}
	
	
	@Override
	public long getBiome(ChunkTile tile) {
		return biome;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
