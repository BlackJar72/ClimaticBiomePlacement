package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetHotForest implements IBiomeSpecifier {
	private static GetHotForest tforest;
	private GetHotForest() {
		super();
		init();
	}
	private BiomeList forests;
	private GetAlpine alpine;
	private GetPlains plains;
	private GetSwamp swamp;
	
	
	public void init() {
		forests = new BiomeList();
		alpine  = GetAlpine.getAlpine();
		swamp   = GetSwamp.getSwamp();
		DefReader.readBiomeData(forests, "ForestTropical.cfg");
		if(!ConfigHandler.includeForests) {
			DefReader.readBiomeData(forests, "Jungle.cfg");			
		}
		if(forests.isEmpty()) {
			if(ConfigHandler.includeForests) {
				forests.addItem(new SeedDoubleBiome(151, 5, 23));
				forests.addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.tropicalForestHills)));
				forests.addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.tropicalForest)), 3);
			} else {
				forests.addItem(GetJungle.getJungle());
			}
		}
	}



	@Override
	public long getBiome(ChunkTile tile) {
		int role1 = tile.getBiomeSeed() % 5;
		int role2 = tile.getBiomeSeed() % 7;
		tile.nextBiomeSeed();
		if((role1) == 0) {
			return alpine.getBiome(tile);
		}
		if((role2) == 0) {
			return swamp.getBiome(tile);
		}
		return forests.getBiome(tile);
	}
	
	
	public static GetHotForest getForest() {
		if(tforest == null) {
			tforest = new GetHotForest();
		}
		return tforest;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
