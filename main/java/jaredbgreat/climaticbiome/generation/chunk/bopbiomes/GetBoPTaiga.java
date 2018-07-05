package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPTaiga implements IBiomeSpecifier {
	private static int boreal, maple, snowy;

	@Override
	public int getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		int temp = tile.getTemp() + (seed & 1) - ((seed & 2) >> 1);
		if(temp > 5) {
			switch(seed % 3) {
				case 0:
					return maple;
				case 1:
				case 2:
				default:
					return boreal;
			}			
		} else {
			return snowy;
		}
	}
	
	
	public static void init() {
		boreal = Biome.getIdForBiome(BOPBiomes.boreal_forest.get());
		maple  = Biome.getIdForBiome(BOPBiomes.maple_woods.get());
		snowy  = Biome.getIdForBiome(BOPBiomes.snowy_coniferous_forest.get());
	}

}
