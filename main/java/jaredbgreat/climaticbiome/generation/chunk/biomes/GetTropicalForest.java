package jaredbgreat.climaticbiome.generation.chunk.biomes;

import net.minecraft.world.biome.Biome;
import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;

public class GetTropicalForest implements IBiomeSpecifier {
	private static int forest;
	private static int hills;

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		switch(seed % 5) {
		case 4:
			return 23;
		case 3: return hills;
		default:
			return forest;
		}
	}
	
	
	public static void init() {
		forest    = Biome.getIdForBiome(ModBiomes.tropicalForest);
		hills     = Biome.getIdForBiome(ModBiomes.tropicalForestHills);
	}

}
