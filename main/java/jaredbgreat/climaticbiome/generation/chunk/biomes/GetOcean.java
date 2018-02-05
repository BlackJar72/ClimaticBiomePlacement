package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetOcean implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int seed  = tile.getBiomeSeed();
		int noise = tile.getNoise();		
		if((seed % 5) == 0) {
			if(noise > (3 + (seed % 3))) {	
				tile.nextBiomeSeed();
				EnumBiomeType.findLandBiome(tile);
				return tile.getRlBiome().specifier.getBiome(tile);
			} else {
				return 0;
			}
		} else if(((seed % 71) == 0) && (tile.getTemp() > 9) && (tile.getWet() > 3) && (tile.getVal() <= 3)) {
			if(noise < 5) return 14;
			else if(noise < 7) return 15;
			else return 0;
		} 
		if(tile.getVal() < 3) {
			return 24;
		}
		return 0;
	}

}
