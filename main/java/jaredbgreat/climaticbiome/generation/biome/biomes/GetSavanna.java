package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetSavanna extends BiomeList {
	private static GetSavanna savanna;
	private GetSavanna() {
		super();
		init();
	}
	
	
	public void init() {
		addItem(new LeafBiome(36));
		addItem(new NoiseDoubleBiome(35, 6, 36), 2);
		addItem(new LeafBiome(35), 3);
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(this, "Savanna.cfg");
		}
	}
	
	
	public static GetSavanna getSavanna() {
		if(savanna == null) {
			savanna = new GetSavanna();
		}
		return savanna;
	}

}
