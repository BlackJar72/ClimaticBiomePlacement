package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

public class GetWarmPlains implements IBiomeSpecifier {
	private static GetWarmPlains plains;
	private GetWarmPlains() {
		super();
		init();
	}
	private GetPlains cool;	
	private GetSavanna hot;
	private int tbound = 15;
	
	
	public void init() {
		cool = GetPlains.getPlains();
		hot  = GetSavanna.getSavanna();
		if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
			tbound = 17;
		} else {
			tbound = 15;
		}
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		int t = tile.getTemp() - tbound;
		if((tile.getBiomeSeed() % 5) < t) {
			tile.nextBiomeSeed();
			return hot.getBiome(tile);
		}
		tile.nextBiomeSeed();
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
