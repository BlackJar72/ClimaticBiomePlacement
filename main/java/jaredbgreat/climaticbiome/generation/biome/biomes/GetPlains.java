package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

public class GetPlains implements IBiomeSpecifier {
	private static GetPlains grassland;
	private GetPlains() {
		super();
		init();
	}
	private BiomeList plains;
	
	
	public void init() {
		plains = new BiomeList();
		DefReader.readBiomeData(plains, "Plains.cfg");
		if(plains.isEmpty()) {
			plains.addItem(new SeedDoubleBiome(129, 7, 1));
		}
	}
	
	

	@Override
	public long getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		tile.nextBiomeSeed();
		return plains.getBiome(tile);
	}
	
	
	public static GetPlains getPlains() {
		if(grassland == null) {
			grassland = new GetPlains();
		}
		return grassland;
	}
	
	
	/**
	 * For mixing temperate and cool temperate zones in 
	 * for use in classic temperature zones.
	 */
	public void collapseCool() {
		plains.merge(GetCoolPlains.getPlains().getList());
	}



	@Override
	public boolean isEmpty() {
		return false;
	}

}
