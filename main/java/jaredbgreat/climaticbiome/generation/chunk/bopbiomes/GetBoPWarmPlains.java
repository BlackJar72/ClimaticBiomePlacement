package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPWarmPlains implements IBiomeSpecifier {
	private static int prairie, pasture, grassland;
	
	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 5) {
			case 0:
			case 1:
				return prairie;
			case 2:
			case 3:
				return grassland;
			case 5:
			default:
				return pasture;
		}
	}
	
	
	public static void init() {
		grassland = Biome.getIdForBiome(BOPBiomes.grassland.get());
		prairie   = Biome.getIdForBiome(BOPBiomes.prairie.get());
		pasture   = Biome.getIdForBiome(BOPBiomes.pasture.get());
	}

}
