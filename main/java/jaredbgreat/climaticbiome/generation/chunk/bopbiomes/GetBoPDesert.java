package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.world.biome.Biome;
import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPDesert implements IBiomeSpecifier {
	private static int lush, oasis, outback, wasteland, xeric;

	@Override
	public int getBiome(ChunkTile tile) {
		return reallyGetBiome(tile, tile.getBiomeSeed() % 7);
	}
	
	
	private int reallyGetBiome(ChunkTile tile, int in) {
		switch(in) {
			case 0:
			case 1:
				return outback;
			case 2:
			case 3:
			case 4:
				return xeric;
			case 5:
				if((tile.getBiomeSeed() & 1) == 0) {
					return oasis;
				} else {
					return lush; // Basically also an oasis
				}
			case 6:
				if((in % 5) == 0) {
					return wasteland;
				} else {
					return lush;
				}
			default:
				return xeric;
		}
	}
	
	
	public static void init() {
		lush      = Biome.getIdForBiome(BOPBiomes.lush_desert.get());
		oasis     = Biome.getIdForBiome(BOPBiomes.oasis.get());
		outback   = Biome.getIdForBiome(BOPBiomes.outback.get());
		wasteland = Biome.getIdForBiome(BOPBiomes.wasteland.get());
		xeric     = Biome.getIdForBiome(BOPBiomes.xeric_shrubland.get());
	}
	
	
	

}
