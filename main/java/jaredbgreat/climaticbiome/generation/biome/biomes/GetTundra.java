package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetTundra extends BiomeList {
	private static GetTundra tundra;
	private GetTundra() {
		super();
		init();
	}
	
	
	public void init() {
		addItem(new LeafBiome(12), 5);
		addItem(new TempDoubleBiome(140, 2, 12));
		addItem(new TempDoubleBiome(12,  2, 30));
		if(ConfigHandler.useBoP) BoP.addTundra(this);
	}
	
	
	public static GetTundra getTundra() {
		if(tundra == null) {
			tundra = new GetTundra();
		}
		return tundra;
	}

}
