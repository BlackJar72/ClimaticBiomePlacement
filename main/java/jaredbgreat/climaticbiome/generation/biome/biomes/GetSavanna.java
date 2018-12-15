package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;

public class GetSavanna extends BiomeList {
	private static GetSavanna savanna;
	private GetSavanna() {
		super();
		init();
	}
	
	
	public void init() {
		DefReader.readBiomeData(this, "Savanna.cfg");
		if(isEmpty()) {
			addItem(new LeafBiome(36));
			addItem(new NoiseDoubleBiome(35, 6, 36), 2);
			addItem(new LeafBiome(35), 3);
		}
	}
	
	
	public static GetSavanna getSavanna() {
		if(savanna == null) {
			savanna = new GetSavanna();
		}
		return savanna;
	}

}
