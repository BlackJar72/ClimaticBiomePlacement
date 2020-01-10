package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.biomes.ModBiomes;
import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;

public class GetVolcano extends BiomeList {
	private static GetVolcano volcanoes;
	private GetVolcano() {
		super();
		init();
	}
	

	public void init() {
		DefReader.readBiomeData(this, "IslandVolcanoes.cfg");
		if(isEmpty()) {
			if(ConfigHandler.includeMountains) {
				addItem(new LeafBiome(ModBiomes.activeVolcano));
			} else {
				addItem(GetAlpine.getAlpine());
			}
		}
	}
	
	
	public static GetVolcano getVolcanoes() {
		if(volcanoes == null) {
			volcanoes = new GetVolcano();
		}
		return volcanoes;
	}

}
