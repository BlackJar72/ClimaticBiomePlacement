package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.world.biome.Biome;
import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPSwamp implements IBiomeSpecifier {
	private static int bayou, bog, deadswamp, fen, lushswamp, 
		mangrove, marsh, quagmire, wetlands, swamp, swampm, pinewoods;

	@Override
	public int getBiome(ChunkTile tile) {
		int temp = tile.getTemp();
		if(temp < 12) return getCoolSwamp(tile);
		if(temp < 16) return getSwamp(tile);
		if(temp < 21) return getWarmSwamp(tile);
		return getHotSwamp(tile);
	}
	
	
	private int getCoolSwamp(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 8) {
			case 0:
			case 1:
			case 2:
				return bog;
			case 3:
			case 4:
			case 5:
				return fen;
			case 6:
				return deadswamp;
			case 7:
				return quagmire;
			default:
				return bog;
		}
	}
	
	
	private int getSwamp(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 4) {
			case 0: 
				if((tile.getBiomeSeed() % 5) == 0) {
					return swampm;
				} else {
					return swamp;
				}
			case 1:
				return lushswamp;
			case 2:
				return marsh;
			case 3:
			default:
				return wetlands;
		}
	}
	
	
	private int getWarmSwamp(ChunkTile tile) {
		switch(tile.getBiomeSeed() % 3) {
			case 0: 
				if((tile.getBiomeSeed() % 5) == 0) {
					return swampm;
				} else {
					return swamp;
				}
			case 1:
				return pinewoods;
			case 2:
				return bayou;
			default:
				return swamp;
		}
	}
	
	
	private int getHotSwamp(ChunkTile tile) {
		return mangrove;
	}
	
	
	public static void init() {
		swamp     = 6;
		swampm    = 134;
		pinewoods = Biome.getIdForBiome(ModBiomes.pineWoods);
		bayou     = Biome.getIdForBiome(BOPBiomes.bayou.get());
		bog       = Biome.getIdForBiome(BOPBiomes.bog.get());
		deadswamp = Biome.getIdForBiome(BOPBiomes.dead_swamp.get());
		fen       = Biome.getIdForBiome(BOPBiomes.fen.get());
		lushswamp = Biome.getIdForBiome(BOPBiomes.lush_swamp.get());
		mangrove  = Biome.getIdForBiome(BOPBiomes.mangrove.get());
		marsh     = Biome.getIdForBiome(BOPBiomes.marsh.get());
		quagmire  = Biome.getIdForBiome(BOPBiomes.quagmire.get());
		wetlands  = Biome.getIdForBiome(BOPBiomes.wasteland.get());
	}

}
