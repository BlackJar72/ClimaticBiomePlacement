package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPChaparral implements IBiomeSpecifier {
	private static int steppe, chaparral, brushland, shrubs;
	

	@Override
	public int getBiome(ChunkTile tile) {
		if(tile.getTemp() < 16) {
			return shrubs;
		}
		switch(tile.getBiomeSeed() % 3) {
			case 0:
				return steppe;
			case 1:
				return brushland;
			case 2:
			default:
				return chaparral;
		}
	}
	
	
	public static void init() {
		chaparral = Biome.getIdForBiome(BOPBiomes.chaparral.get());
		steppe    = Biome.getIdForBiome(BOPBiomes.steppe.get());
		brushland = Biome.getIdForBiome(BOPBiomes.brushland.get());
		shrubs    = Biome.getIdForBiome(BOPBiomes.shrubland.get());
	}

}
