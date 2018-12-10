package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetPlains implements IBiomeSpecifier {
	private static GetPlains grassland;
	private GetPlains() {
		super();
		init();
	}
	private BiomeList plains;
	private GetAlpine alpine;
	
	
	public void init() {
		plains = new BiomeList();
		alpine = GetAlpine.getAlpine();
		if(ConfigHandler.cleanSlate) {
			DefReader.readBiomeData(plains, "Plains.cfg");
			return;
		}
		plains.addItem(new SeedDoubleBiome(129, 7, 1), 6);
		if(ConfigHandler.useBoP) BoP.addPlains(plains);
		if(ConfigHandler.useCfg) {
			DefReader.readBiomeData(plains, "Plains.cfg");
		}
	}
	
	

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		if((seed % 4) == 0) {
			tile.nextBiomeSeed();
			return alpine.getBiome(tile);
		}
		tile.nextBiomeSeed();
		return plains.getBiome(tile);
	}
	
	
	public static GetPlains getPlains() {
		if(grassland == null) {
			grassland = new GetPlains();
		}
		return grassland;
	}



	@Override
	public boolean isEmpty() {
		return false;
	}

}
