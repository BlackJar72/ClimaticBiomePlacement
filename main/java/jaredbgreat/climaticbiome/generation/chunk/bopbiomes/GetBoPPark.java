package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPPark implements IBiomeSpecifier {
	private static int grove, lakes;

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() & 1) {
			case 0: 
				return grove;
			case 1:
				return lakes;
			default:
				return grove; // Error!
		}
	}
	
	
	public static void init() {
		grove = Biome.getIdForBiome(BOPBiomes.grove.get());
		lakes = Biome.getIdForBiome(BOPBiomes.land_of_lakes.get());
	}
	
}
