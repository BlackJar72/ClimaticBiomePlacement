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
	BiomeList frozen;
	BiomeList cold;
	BiomeList cool;
	BiomeList warm;
	BiomeList hot;
	BiomeList desert;
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
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();
		int seed = tile.getBiomeSeed();
		int temp = tile.getTemp();
		tile.nextBiomeSeed();
		if(temp < 4) {
			if(seed % 4 < frozen.size()) {
				return frozen.getBiome(tile);
			} else return basic.getBiome(tile);
		}
		if(temp < 7) {
			if(seed % 4 < cold.size()) {
				return cold.getBiome(tile);
			} else return basic.getBiome(tile);
		}
		if(temp < 13) {
			if(seed % 4 < cool.size()) {
				return cool.getBiome(tile);
			} else return basic.getBiome(tile);
		}
		if(temp <19) {
			if(tile.getWet() < 4) {
				if(seed % 4 < desert.size()) {
					return desert.getBiome(tile);
				} else return basic.getBiome(tile);
			}
			if(seed % 4 < warm.size()) {
				return warm.getBiome(tile);
			} else return basic.getBiome(tile);
		
		}
		if(tile.getWet() < 2) {
			if(seed % 4 < desert.size()) {
				return desert.getBiome(tile);
			} else return basic.getBiome(tile);
		}
		if(seed % 4 < hot.size()) {
			return hot.getBiome(tile);
		} else return basic.getBiome(tile);
	
	}
	
	
	public static GetIslands getIslands() {
		if(islands == null) {
			islands = new GetIslands();
		}
		return islands;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
