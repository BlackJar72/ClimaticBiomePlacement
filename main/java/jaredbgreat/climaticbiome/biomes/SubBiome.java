package jaredbgreat.climaticbiome.biomes;

import net.minecraft.world.biome.Biome;

public class SubBiome extends Biome {
	private int id; // Sub-ID

	public SubBiome(Biome parent, int sid, BiomeProperties properties) {
		super(properties);
		id = Biome.getIdForBiome(parent) | (sid << 8);
//		System.err.println();
//		System.err.println("***********************");
//		System.err.println(parent.getBiomeName() + ":" + sid + " = " + getSubId());
//		System.err.println("***********************");
//		System.err.println();
	}
	
	
	public int getSubId() {
		return id;
	}

}
