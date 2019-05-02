package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

import java.util.List;

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
		parks.addItem(plains);
		parks.addItem(woods);
		DefReader.readBiomeData(parks, "ParklandCool.cfg");
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		return parks.getBiome(tile);
	}
	
	
	public static GetCoolPark getPark() {
		if(pland == null) {
			pland = new GetCoolPark();
		}
		return pland;
	}
	
	
	BiomeList getList() {
		return parks;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}
	
}
