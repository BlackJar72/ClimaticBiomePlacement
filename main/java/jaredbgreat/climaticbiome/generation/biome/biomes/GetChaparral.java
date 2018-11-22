package jaredbgreat.climaticbiome.generation.chunk.biomes;

import net.minecraft.world.biome.Biome;
import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.biomes.ModBiomes;
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
		dense      = Biome.getIdForBiome(ModBiomes.denseScrub);
		dry        = Biome.getIdForBiome(ModBiomes.dryScrub);
		densehills = Biome.getIdForBiome(ModBiomes.denseScrubHills);
		dryhills   = Biome.getIdForBiome(ModBiomes.dryScrubHills);
	}

}
