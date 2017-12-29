package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.BiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetTropicalForest implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		switch(seed % 5) {
		case 4:
			return 23;
		case 3: return 70;
		default:
			return 67;
		}
	}

}
