package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPTundra implements IBiomeSpecifier {
	private static int glacier;
	
	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 3) == 0) {
			return glacier;
		} else {
			tile.nextBiomeSeed();
			return EnumBiomeType.TUNDRA.specifier.getBiome(tile);
		}
	}
	
	
	public static void init() {
		glacier = Biome.getIdForBiome(BOPBiomes.glacier.get());
	}

}
