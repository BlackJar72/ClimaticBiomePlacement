package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

import java.util.List;

public class GetPark implements IBiomeSpecifier {
	private static GetPark pland;
	private GetPark() {
		super();
		init();
	}	
	private BiomeList parks;
	private GetPlains plains;
	private GetForest woods;
	
	
	public void init() {
		parks = new BiomeList();
		plains = GetPlains.getPlains();
		woods = GetForest.getForest();
		parks.addItem(plains);
		parks.addItem(woods);
		DefReader.readBiomeData(parks, "Parkland.cfg");
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		return parks.getBiome(tile);
	}
	
	
	/**
	 * For mixing temperate and cool temperate zones in 
	 * for use in classic temperature zones.
	 */
	public void collapseCoole() {
		parks.merge(GetCoolPlains.getPlains().getList());
		parks.remove(GetCoolForest.getForest());
		parks.remove(GetCoolPlains.getPlains());
	}
	
	
	public static GetPark getPark() {
		if(pland == null) {
			pland = new GetPark();
		}
		return pland;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}
	
}
