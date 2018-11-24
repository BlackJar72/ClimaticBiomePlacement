package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetCoolPlains implements IBiomeSpecifier {
	private static GetCoolPlains plains;
	private GetCoolPlains() {
		super();
	}
	private BiomeList coolPlains;
	
	public void init() {
		coolPlains = new BiomeList();
		coolPlains.addItem(new LeafBiome(1), 2);		
	}

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 3) == 0) {
			return 13;  // TODO: Once GetAlpine is more generic use that
		}
		tile.nextBiomeSeed();
		return coolPlains.getBiome(tile);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
	public static GetCoolPlains getPlains() {
		if(plains == null) {
			plains = new GetCoolPlains();
		}
		return plains;
	}

}
