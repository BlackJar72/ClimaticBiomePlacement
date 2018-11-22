package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetPlains implements IBiomeSpecifier {
	private static GetPlains grassland;
	private GetPlains() {
		super();
	}
	private BiomeList plains;
	private GetAlpine alpine;
	
	
	public void init() {
		plains = new BiomeList();
		alpine = GetAlpine.getAlpine();
		plains.addItem(new SeedDoubleBiome(129, 5, 1), 1);
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

}
