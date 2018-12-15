package jaredbgreat.climaticbiome.generation.biome.biomes;

import net.minecraft.world.biome.Biome;
import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.compat.BoP;
import jaredbgreat.climaticbiome.generation.biome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class GetTaiga implements IBiomeSpecifier {
	private static GetTaiga taiga;
	private GetTaiga() {
		super();
		init();
	}
	private BiomeList forest;
	private static int tbound;
	
	
	public static final class TaigaDoubleBiome extends TempDoubleBiome {
		public TaigaDoubleBiome(Biome a, Biome b) {
			super(a, tbound, b);
		}
		public TaigaDoubleBiome(int a, int b) {
			super(a, tbound, b);
		}		
	}
	
	
	public void init() {
		if(ConfigHandler.useBoP) { // FIXME: Use something more generic, not BoP specific
			tbound = 6;
		} else {
			tbound = 7;
		}
		forest = new BiomeList();
		DefReader.readBiomeData(forest, "Taiga.cfg");
		if(isEmpty()){
			forest.addItem(new TempDoubleBiome(30,  tbound, 5),  4);
			forest.addItem(new TempDoubleBiome(32,  tbound, 5),  2);
			forest.addItem(new TempDoubleBiome(30,  tbound, 160));
			forest.addItem(new TempDoubleBiome(158, tbound, 19),  3);
			forest.addItem(new TempDoubleBiome(158, tbound, 133), 2);
		}
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		// Doing this to allow for possible addition of other types (e.g., alpine)
		return forest.getBiome(tile);
	}
	
	
	public static GetTaiga getTaiga() {
		if(taiga == null) {
			taiga = new GetTaiga();
		}
		return taiga;
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
