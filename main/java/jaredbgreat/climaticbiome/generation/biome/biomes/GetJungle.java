package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;

public class GetJungle extends BiomeList {
	private static GetJungle jungle;
	private GetJungle() {
		super();
		init();
	}

	
	public void init() {
		DefReader.readBiomeData(this, "Jungle.cfg");
		if(isEmpty()) {
			addItem(new LeafBiome(21), 3);
			addItem(new LeafBiome(23), 2);
		}
	}
	
	
	public static GetJungle getJungle() {
		if(jungle == null) {
			jungle = new GetJungle();
		}
		return jungle;
	}

}
