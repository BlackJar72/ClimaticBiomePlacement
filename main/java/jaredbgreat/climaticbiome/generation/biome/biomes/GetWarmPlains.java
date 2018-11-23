package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetWarmPlains implements IBiomeSpecifier {
	private static GetWarmPlains plains;
	private GetWarmPlains() {
		super();
	}
	private GetPlains cool;	
	private GetSavanna hot;
	private static final int tbound = 14;
	
	
	public void init() {
		cool = GetPlains.getPlains();
		hot  = GetSavanna.getSavanna();
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		int t = tile.getTemp() - tbound;
		if((tile.getBiomeSeed() % 5) > t) {
			return hot.getBiome(tile);
		}
		return cool.getBiome(tile);
	}
	
	
	public static GetWarmPlains getPlains() {
		if(plains == null) {
			plains = new GetWarmPlains();
		}
		return plains;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
