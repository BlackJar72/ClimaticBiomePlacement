package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class LeafBiome extends AbstractTerminalSpecifier {
	private final long biome;

	
	public LeafBiome(long biome) {
		this.biome = biome;
	}

	
	public LeafBiome(Biome biome) {
		this.biome = Biome.getIdForBiome(biome);
	}
	
	
	public LeafBiome(String biome, IForgeRegistry biomeReg) {
		this.biome = getBiomeNumber(biome, biomeReg); 
	}
	
	
	@Override
	public long getBiome(ChunkTile tile) {
		return biome;
	}


	@Override
	public boolean isEmpty() {
		return (biome < 0);
	}

}
