package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetWarmForest implements IBiomeSpecifier {
	private static GetWarmForest sforest;
	private GetWarmForest() {
		super();
		init();
	}
	private BiomeList forests;
	private GetAlpine alpine;
	private GetPlains plains;
	private GetSwamp  swamp;
	
	
	public void init() {
		forests = new BiomeList();
		alpine  = GetAlpine.getAlpine();
		plains  = GetPlains.getPlains();
		swamp   = GetSwamp.getSwamp();
		DefReader.readBiomeData(forests, "ForestWarm.cfg");
		if(forests.isEmpty()) {
			forests.addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.warmForest)), 5);
			forests.addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.warmForestHills)), 3);
			forests.addItem(new LeafBiome(Biome.getIdForBiome(ModBiomes.pineWoods)));
		}
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		int role1 = tile.getBiomeSeed() % 5;
		int role2 = tile.getBiomeSeed() % 7;
		int role3 = tile.getBiomeSeed() % 12;
		tile.nextBiomeSeed();
		if((role1) == 0) {
			return alpine.getBiome(tile);
		}
		if((role2) == 0) {
			return swamp.getBiome(tile);
		}
		if((role3) == 0) {
			return plains.getBiome(tile);
		}
		return forests.getBiome(tile);
	}
	
	
	public static GetWarmForest getForest() {
		if(sforest == null) {
			sforest = new GetWarmForest();
		}
		return sforest;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
}
