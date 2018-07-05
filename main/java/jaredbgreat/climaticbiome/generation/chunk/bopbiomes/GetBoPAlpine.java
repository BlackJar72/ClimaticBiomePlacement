package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPAlpine implements IBiomeSpecifier {
	private static int crag, highlands, mountain, overgrown, glacier, /*No, SILLY!*/ alps;
	private static int desert, icem;

	@Override
	public int getBiome(ChunkTile tile) {
		int temp = tile.getTemp();
		if(temp <  7) return getColdM(tile);
		if(temp < 16) return getMidM(tile);
		if(temp < 21) return getWarmM(tile);
		return getHotM(tile);
	}
	
	
	private static int getColdM(ChunkTile tile) {
		if((tile.getBiomeSeed() & 1) == 0) {
			return glacier;
		}
		return icem;
	}
	
	
	private static int getMidM(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 6) {
			case 0:
			case 1:
			case 2:
				return highlands;
			case 3:
			case 4:
				return mountain;
			case 5:
				return crag;
			default:
				return mountain;
		}
	}
	
	
	private static int getWarmM(ChunkTile tile) {
		if(tile.getWet() < 3) {
			return desert;
		}
		return getMidM(tile);
	}
	
	
	private static int getHotM(ChunkTile tile) {
		if(tile.getWet() < 5) {
			return EnumBiomeType.boptable[(tile.getTemp() * 10) + tile.getWet()]
					.specifier.getBiome(tile);
		}
		return overgrown;
	}
	
	
	public static void init() {
		desert    = 17;
		icem      = 13;
		crag      = Biome.getIdForBiome(BOPBiomes.crag.get());
		highlands = Biome.getIdForBiome(BOPBiomes.highland.get());
		mountain  = Biome.getIdForBiome(BOPBiomes.mountain.get());
		overgrown = Biome.getIdForBiome(BOPBiomes.overgrown_cliffs.get());
		glacier   = Biome.getIdForBiome(BOPBiomes.glacier.get());
		alps      = Biome.getIdForBiome(BOPBiomes.alps.get()); // = dumb!
	}

}
