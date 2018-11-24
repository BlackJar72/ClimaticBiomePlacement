package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetHotForest extends BiomeList {
	private static GetHotForest  tforest;
	private GetHotForest() {
		super();
	}
	
	
	public void init() {
		addItem(new SeedDoubleBiome(151, 5, 23));
		addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.tropicalForestHills)));
		addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.tropicalForest)), 2);
	}
	
	
	public static GetHotForest getForest() {
		if(tforest == null) {
			tforest = new GetHotForest();
		}
		return tforest;
	}

}
