package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.biomeprovider.ChunkTile;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class TerrainBiome extends AbstractTerminalSpecifier {
	private final long biome;

	
	public TerrainBiome(long biome) {
		this.biome = biome;
	}

	
	public TerrainBiome(Biome biome) {
		this.biome = Biome.getIdForBiome(biome);
	}
	
	
	public TerrainBiome(String biome, IForgeRegistry biomeReg) {
		this.biome = getBiomeNumber(biome, biomeReg); 
	}
	
	
	@Override
	public long getBiome(ChunkTile tile) {
		tile.setNormalTerrain();
		return biome;
	}


	@Override
	public boolean isEmpty() {
		return (biome < 0);
	}

}
