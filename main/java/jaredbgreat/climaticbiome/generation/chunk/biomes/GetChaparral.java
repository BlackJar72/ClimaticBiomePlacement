package jaredbgreat.climaticbiome.generation.chunk.biomes;

import jaredbgreat.climaticbiome.ClimaticBiomePlacement;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetChaparral implements IBiomeSpecifier {
	private static int dense, dry, densehills, dryhills;

	@Override
	public int getBiome(ChunkTile tile) {
		int pick = tile.getBiomeSeed() % 2;
		if(pick == 0) {
			if((tile.getBiomeSeed() % 3 == 0)) {
				return densehills;
			} else {
				return dense;
			}
		} else {
			if((tile.getBiomeSeed() % 3 == 0)) {
				return dryhills;
			} else {
				return dry;
			}
		}
	}
	
	
	public static void init() {
		dense      = ClimaticBiomePlacement.configHandler.getBiomeID("dense scrub");
		dry        = ClimaticBiomePlacement.configHandler.getBiomeID("dry scrub");
		densehills = ClimaticBiomePlacement.configHandler.getBiomeID("dense scrub hills");
		dryhills   = ClimaticBiomePlacement.configHandler.getBiomeID("dry scrub hills");
	}

}
