package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPCoolPlains implements IBiomeSpecifier {
	private static int meadow, moor;

	@Override
	public int getBiome(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 3) {
			case 0:
			case 1:
				return meadow;
			case 2:
			default:
				return moor;
		}
	}

	
	public static void init() {
		meadow = Biome.getIdForBiome(BOPBiomes.meadow.get());
		moor   = Biome.getIdForBiome(BOPBiomes.moor.get());
	}
	
}
