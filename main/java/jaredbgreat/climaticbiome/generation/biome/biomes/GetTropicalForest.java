package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetTropicalForest extends BiomeList {
	private static GetTropicalForest  tforest;
	private GetTropicalForest() {
		super();
	}
	
	
	public void init() {
		addItem(new SeedDoubleBiome(151, 5, 23));
		addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.tropicalForestHills)));
		addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.tropicalForest)), 2);
	}
	
	
	public static GetTropicalForest getForest() {
		if(tforest == null) {
			tforest = new GetTropicalForest();
		}
		return tforest;
	}

}
