package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;

public class GetVolcano extends BiomeList {
	private static GetVolcano volcanoes;
	private GetVolcano() {
		super();
		init();
	}
	

	public void init() {
		DefReader.readBiomeData(this, "IslandVolcanoes.cfg");
		if(isEmpty()) {
			addItem(new LeafBiome(ModBiomes.activeVolcano));
		}
	}
	
	
	public static GetVolcano getVolcanoes() {
		if(volcanoes == null) {
			volcanoes = new GetVolcano();
		}
		return volcanoes;
	}

}
