package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetJungle extends BiomeList {
	private static GetJungle jungle;
	private GetJungle() {
		super();
		init();
	}

	
	public void init() {
		if(ConfigHandler.cleanSlate) {
			DefReader.readBiomeData(this, "Jungle.cfg");
			return;
		}
		this.addItem(new LeafBiome(21), 3);
		this.addItem(new LeafBiome(23), 2);
		if(ConfigHandler.useBoP) BoP.addJungle(this);
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(this, "Jungle.cfg");
		}
	}
	
	
	public static GetJungle getJungle() {
		if(jungle == null) {
			jungle = new GetJungle();
		}
		return jungle;
	}

}
