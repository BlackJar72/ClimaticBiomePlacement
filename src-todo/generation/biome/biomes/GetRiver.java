package jaredbgreat.climaticbiomes.generation.biome.biomes;

import net.minecraft.world.biome.Biome;
import jaredbgreat.climaticbiomes.biomes.ModBiomes;
import jaredbgreat.climaticbiomes.biomes.pseudo.PseudoBiomes;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;

public class GetRiver implements IBiomeSpecifier {
	private static GetRiver river;
	private GetRiver() {
		super();
	}
	private static int temperate;
	private static int warm;
	private static int hot;

	
	@Override
	public long getBiome(ChunkTile tile) {
		if(ConfigHandler.includeRivers) {
			int t = tile.getTemp();
			if(t < 5) {
				return PseudoBiomes.deepFrozenRiver.getSubId();
			} else if(t < 10) {
				return PseudoBiomes.deepRiver.getSubId();
			} else if(t < 16) {
				return temperate;
			} else if(t < 20) {
				return warm;
			} else {
				return hot;
			}
				
		} else {
			if((tile.getTemp() + (tile.getBiomeSeed() & 0x1)) < 5) {
				PseudoBiomes.deepFrozenRiver.getSubId();
			}
			return PseudoBiomes.deepRiver.getSubId();
		}
	}
	
	
	public static GetRiver getRiver() {
		if(river == null) {
			river = new GetRiver();
		}
		return river;
	}

	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
	public static void initAdvanced() {
		temperate = Biome.getIdForBiome(ModBiomes.river);
		warm      = Biome.getIdForBiome(ModBiomes.warmRiver);
		hot       = Biome.getIdForBiome(ModBiomes.hotRiver);
	}

}
