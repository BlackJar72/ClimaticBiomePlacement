package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetForest implements IBiomeSpecifier {
	private static GetForest tforest;
	private GetForest() {
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
		plains  = GetPlains.getPlains();
		swamp   = GetSwamp.getSwamp();
		forests.addItem(new SeedDoubleBiome(18, 3, 4), 3);
		forests.addItem(new LeafBiome(132), 1);
		forests.addItem(new SeedDoubleBiome(27, 4, 28), 1);
		forests.addItem(new SeedDoubleBiome(155, 5, 27), 1);
		forests.addItem(new SeedDoubleBiome(157, 7, 29), 2);
		if(ConfigHandler.useBoP) BoP.addForest(forests);
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(forests, "Forest.cfg");
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
	
	
	public static GetForest getForest() {
		if(tforest == null) {
			tforest = new GetForest();
		}
		return tforest;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
