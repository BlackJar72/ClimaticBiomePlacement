package jaredbgreat.climaticbiome.generation.biome;

import java.util.StringTokenizer;

import jaredbgreat.climaticbiome.generation.biome.biomes.GetOcean;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class IslandBiome extends AbstractTerminalSpecifier {
	private final long island;
	private static GetOcean oceans;

	
	public IslandBiome(long island) {
		init(); // This could be called once else where, but is safer here
		this.island = island;
	}

	
	public IslandBiome(Biome island) {
		init(); // This could be called once else where, but is safer here
		this.island = Biome.getIdForBiome(island);
	}
	
	
	public IslandBiome(String island, IForgeRegistry biomeReg) {
		init(); // This could be called once else where, but is safer here
		this.island = getBiomeNumber(island, biomeReg);
	}
	
	
	public final void init() {
		if(oceans == null) oceans = GetOcean.getOcean();
	}
	
	
	@Override
	public long getBiome(ChunkTile tile) {
		if(tile.getNoise() > (4 + (tile.getBiomeSeed() % 3))) {
			return island;
		}
		return oceans.getForIsland(tile);
	}
	

	@Override
	public boolean isEmpty() {
		return (island < 0);
	}

}
