package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetCoolPark implements IBiomeSpecifier {
	private static GetCoolPark pland;
	private GetCoolPark() {
		super();
		init();
	}	
	private BiomeList parks;
	private GetCoolPlains plains;
	private GetCoolForest woods;
	
	
	public void init() {
		parks = new BiomeList();
		plains = GetCoolPlains.getPlains();
		woods = GetCoolForest.getForest();
		if(ConfigHandler.cleanSlate) {
			DefReader.readBiomeData(parks, "ParklandCool.cfg");
			return;
		}
		if(ConfigHandler.useBoP) {
			BoP.addCoolPark(parks);
		}
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(parks, "ParklandCool.cfg");			
		}
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		if(parks.isEmpty() || ((seed & 5) == 0)) {
			if((seed & 1) == 0) {
				tile.nextBiomeSeed();
				return woods.getBiome(tile);
			} else {
				tile.nextBiomeSeed();
				return plains.getBiome(tile);				
			}
		}
		tile.nextBiomeSeed();
		return parks.getBiome(tile);
	}
	
	
	public static GetCoolPark getPark() {
		if(pland == null) {
			pland = new GetCoolPark();
		}
		return pland;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}
	
}
