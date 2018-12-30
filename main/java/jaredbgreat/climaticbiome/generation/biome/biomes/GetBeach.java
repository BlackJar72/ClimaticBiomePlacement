package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetBeach implements IBiomeSpecifier {
	private static GetBeach beaches;
	private GetBeach() {
		super();
		init();
	}
	private BiomeList cold;
	private BiomeList rock;
	private BiomeList warm;
	private static int tbound;
	
	
	public void init() {
		if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
			tbound = 6;
		} else {
			tbound = 7;
		}
		cold = new BiomeList();
		rock = new BiomeList();
		warm = new BiomeList();
		// TODO: Make configurable
		cold.addItem(new LeafBiome(Biome.getBiome(26)));
		rock.addItem(new LeafBiome(Biome.getBiome(25)));
		warm.addItem(new LeafBiome(Biome.getBiome(16)));
	}
	
	
	@Override
	public int getBiome(ChunkTile tile) {
		if(tile.getTemp() < tbound) {
			return cold.getBiome(tile);
		}
		return warm.getBiome(tile);
	}
	
	
	public int getHighBiome(ChunkTile tile) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public boolean isEmpty() {
		return false;
	}


	public static GetBeach getBeach() {
		if(beaches == null) {
			beaches = new GetBeach();
		}
		return beaches;
	}

}
