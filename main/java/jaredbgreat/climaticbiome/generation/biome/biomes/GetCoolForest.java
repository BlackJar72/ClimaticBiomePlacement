package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;


/**
 * This specifer is specifically for words using biome mods that 
 * imply a cool temperate band between the taiga zone and the 
 * basic temperate zone.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class GetCoolForest implements IBiomeSpecifier {
	private static GetCoolForest cforest;
	private GetCoolForest() {
		super();
		init();
	}
	private BiomeList forests;
	private GetAlpine alpine;
	private GetCoolPlains plains;
	private GetSwamp swamp;
	
	
	public void init() {
		forests = new BiomeList();
		alpine  = GetAlpine.getAlpine();
		plains  = GetCoolPlains.getPlains();
		swamp   = GetSwamp.getSwamp();
		if(ConfigHandler.cleanSlate) {
			DefReader.readBiomeData(forests, "ForestCool.cfg");
			return;
		}
		if(ConfigHandler.useBoP) BoP.addCoolForest(forests);
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(forests, "ForestCool.cfg");
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
	
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
	public static GetCoolForest getForest() {
		if(cforest == null) {
			cforest = new GetCoolForest();
		}
		return cforest;
	}

}
