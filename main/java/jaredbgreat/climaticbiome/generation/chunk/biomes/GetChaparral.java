package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.ClimaticBiomePlacement;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetChaparral implements IBiomeSpecifier {

	@Override
	public int getBiome(ChunkTile tile) {
		int pick = tile.getBiomeSeed() % 2;
		if(pick == 0) {
			if((tile.getBiomeSeed() % 3 == 0)) {
				return ClimaticBiomePlacement.configHandler.getBiomeID("dense scrub hills");
			} else {
				return ClimaticBiomePlacement.configHandler.getBiomeID("dense scrub");
			}
		} else {
			if((tile.getBiomeSeed() % 3 == 0)) {
				return ClimaticBiomePlacement.configHandler.getBiomeID("dry scrub hills");
			} else {
				return ClimaticBiomePlacement.configHandler.getBiomeID("dry scrub");
			}
		}
	}

}
