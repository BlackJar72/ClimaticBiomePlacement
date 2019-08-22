package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import net.minecraft.world.biome.Biome;

public class GetChaparral extends BiomeList {
	private static GetChaparral scrub;
	private GetChaparral() {
		super();
		init();
	} 
	
	
	public void init() {
		DefReader.readBiomeData(this, "ChaparralScrub.cfg");
		if(isEmpty()){
			addItem(new SeedDoubleBiome(
					Biome.getIdForBiome(ModBiomes.denseScrubHills), 3, 
					Biome.getIdForBiome(ModBiomes.denseScrub)), 2);
			addItem(new SeedDoubleBiome(
					Biome.getIdForBiome(ModBiomes.dryScrubHills), 3, 
					Biome.getIdForBiome(ModBiomes.dryScrub)));
		}
	}
	
	
	public static GetChaparral getChaparral() {
		if(scrub == null) {
			scrub = new GetChaparral();
		}
		return scrub;
	}
}
