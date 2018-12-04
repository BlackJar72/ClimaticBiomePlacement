package jaredbgreat.climaticbiome.biomes;

import net.minecraft.world.biome.Biome;

public class SubBiome extends Biome {
	private int id; // Sub-ID

	public SubBiome(Biome parent, int sid, BiomeProperties properties) {
		super(properties);
		id = Biome.getIdForBiome(parent) | (sid << 8);
	}
	
	
	public int getSubId() {
		return id;
	}

}
