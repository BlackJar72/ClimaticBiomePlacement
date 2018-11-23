package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetPark implements IBiomeSpecifier {
	private static GetPark pland;
	private GetPark() {
		super();
	}	
	private BiomeList parks;
	private GetPlains plains;
	private GetTemporateForest woods;
	
	
	public void init() {
		parks = new BiomeList();
		plains = GetPlains.getPlains();
		woods = GetTemporateForest.getForest();
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
