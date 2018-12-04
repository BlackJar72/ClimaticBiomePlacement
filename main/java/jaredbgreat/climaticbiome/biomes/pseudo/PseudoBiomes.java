package jaredbgreat.climaticbiome.biomes.pseudo;

import jaredbgreat.climaticbiome.biomes.SubBiomeRegistry;
import net.minecraft.world.biome.Biome;

public class PseudoBiomes {
	public static BiomeDeepRiver deepRiver;	
	
	
	public static void createBiomes() {
		deepRiver = new BiomeDeepRiver(Biome.getBiome(7), 1, 
				new Biome.BiomeProperties("River").setBaseHeight(-0.8f).setHeightVariation(0.0f));
	}
	
	
	public static void registerBiomes() {
		SubBiomeRegistry reg = SubBiomeRegistry.getSubBiomeRegistry();
		reg.add(deepRiver);
	}
	

}
