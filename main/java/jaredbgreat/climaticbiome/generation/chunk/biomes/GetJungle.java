package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetJungle implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 5) {
			case 0:
			case 1:
			case 2:
				return 21;
			case 3:
			case 4:
				return 22;
			default:
				return 21;
		}
	}

}
