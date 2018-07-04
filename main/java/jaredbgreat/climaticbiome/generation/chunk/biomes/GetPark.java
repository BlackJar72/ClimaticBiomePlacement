package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;

public class GetPark implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() & 1) == 0) {
			tile.nextBiomeSeed();
			return EnumBiomeType.FOREST.specifier.getBiome(tile);
		} else {
			tile.nextBiomeSeed();
			return EnumBiomeType.GRASS.specifier.getBiome(tile);			
		}
	}

}
