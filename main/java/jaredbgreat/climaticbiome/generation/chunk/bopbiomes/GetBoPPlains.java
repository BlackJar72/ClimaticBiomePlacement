package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import net.minecraft.world.biome.Biome;
import biomesoplenty.api.biome.BOPBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPPlains implements IBiomeSpecifier {
	private static int flowers, moor, lavender, pasture;
	
	@Override
	public int getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 5) == 0) {
			return pasture;
		}
		switch(tile.getBiomeSeed() % 3) {
			case 0:
				return flowers;
			case 1: 
				return lavender;
			case 3:
			default:
				return moor;
		}
	}
	
	
	public static void init() {
		flowers  = Biome.getIdForBiome(BOPBiomes.flower_field.get());
		moor     = Biome.getIdForBiome(BOPBiomes.moor.get());
		lavender = Biome.getIdForBiome(BOPBiomes.lavender_fields.get());
		pasture  = Biome.getIdForBiome(BOPBiomes.pasture.get());
	}

}
