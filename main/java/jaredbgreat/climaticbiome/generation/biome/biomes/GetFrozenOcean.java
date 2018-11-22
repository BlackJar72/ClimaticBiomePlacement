package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetFrozenOcean implements IBiomeSpecifier {
	private static GetFrozenOcean frocean;
	private GetFrozenOcean() {
		super();
	}

	
	@Override
	public int getBiome(ChunkTile tile) {
		return 10;
	}
	
	
	public static GetFrozenOcean getFrozenOcean() {
		if(frocean == null) {
			frocean = new GetFrozenOcean();
		}
		return frocean;
	}

}
