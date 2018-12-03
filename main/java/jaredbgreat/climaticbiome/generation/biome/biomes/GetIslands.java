package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeClimateTable;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetIslands implements IBiomeSpecifier {
	private static GetIslands islands;
	private GetIslands() {
		super();
		init();
	}
	IBiomeSpecifier frozen;
	IBiomeSpecifier cold;
	IBiomeSpecifier cool;
	IBiomeSpecifier warm;
	IBiomeSpecifier hot;
	IBiomeSpecifier desert;
	IBiomeSpecifier basic; // Main land biomes (fallback)
	private boolean hasfr   = true, hascold = true, hascool   = true, 
			        haswarm = true, hashot  = true, hasdesert = true;
	
	
	public void init() {
		frozen = new BiomeList();
		cold   = new BiomeList();
		cool   = new BiomeList();
		warm   = new BiomeList();
		hot    = new BiomeList();
		desert = new BiomeList();
		basic  = BiomeClimateTable.getLandTable();
		/*
		 * Add Modded islands biomes here!
		 */
		if(ConfigHandler.useBoP) BoP.addIslands((BiomeList)frozen, (BiomeList)cold, 
											    (BiomeList)cool, (BiomeList)warm, 
											    (BiomeList)hot, (BiomeList)desert);
		
		// THIS MUST ALWAYS BE LAST!!!
		fixIslands();
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		int temp = tile.getTemp();
		if(temp < 4) {
			return frozen.getBiome(tile);
		}
		if(temp < 7) {
			return cold.getBiome(tile);
		}
		if(temp < 13) {
			return cool.getBiome(tile);			
		}
		if(temp <19) {
			if(tile.getWet() < 4) {
				return desert.getBiome(tile);
			}
			return warm.getBiome(tile);
		}
		if(tile.getWet() < 2) {
			return desert.getBiome(tile);
		}
		return hot.getBiome(tile);
	}
	
	
	public static GetIslands getIslands() {
		if(islands == null) {
			islands = new GetIslands();
		}
		return islands;
	}
	
	
	private void fixIslands() {
		if(frozen.isEmpty()) {
			frozen = basic;
			hasfr  = false;
		}
		if(cold.isEmpty()) {
			cold = basic;
			hascold = false;
		}
		if(cool.isEmpty()) {
			cool = basic;
			hascool = false;
		}
		if(warm.isEmpty()) {
			warm = basic;
			haswarm = false;
		}
		if(hot.isEmpty()) {
			hot = basic;
			hashot = false;
		}
		if(desert.isEmpty()) {
			desert = basic;
			hasdesert = false;
		}
		
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
