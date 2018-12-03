package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetChaparral extends BiomeList {
	private static GetChaparral scrub;
	private GetChaparral() {
		super();
		init();
	} 
	
	
	public void init() {
		addItem(new SeedDoubleBiome(
				Biome.getIdForBiome(ModBiomes.denseScrubHills), 3, 
				Biome.getIdForBiome(ModBiomes.denseScrub)), 2);
		addItem(new SeedDoubleBiome(
				Biome.getIdForBiome(ModBiomes.dryScrubHills), 3, 
				Biome.getIdForBiome(ModBiomes.dryScrub)));
		if(ConfigHandler.useBoP) BoP.addChaparral(this);
	}
	
	
	public static GetChaparral getChaparral() {
		if(scrub == null) {
			scrub = new GetChaparral();
		}
		return scrub;
	}
}
