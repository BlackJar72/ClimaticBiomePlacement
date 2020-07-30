package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

public class GetTaiga implements IBiomeSpecifier {
	private static GetTaiga taiga;
	private GetTaiga() {
		super();
		init();
	}
	private BiomeList forest;
	private IBiomeSpecifier alpine;
	private static int tbound;
	
	
	public static final class TaigaDoubleBiome extends TempDoubleBiome {
		public TaigaDoubleBiome(Biome a, Biome b) {
			super(a, tbound, b);
		}
		public TaigaDoubleBiome(int a, int b) {
			super(a, tbound, b);
		}
		public TaigaDoubleBiome(String a, String b, IForgeRegistry biomeReg) {
			super(a, tbound, b, biomeReg);
		}	
	}
	
	
	public void init() {
		if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
			tbound = 6;
		} else {
			tbound = 7;
		}
		forest = new BiomeList();
		alpine = GetAlpine.getAlpine();
		DefReader.readBiomeData(forest, "Taiga.cfg");
		if(forest.isEmpty()){
			forest.addItem(new TempDoubleBiome(30,  tbound, 5),  4);
			forest.addItem(new TempDoubleBiome(32,  tbound, 5),  2);
			forest.addItem(new TempDoubleBiome(30,  tbound, 160));
			forest.addItem(new TempDoubleBiome(158, tbound, 19),  3);
			forest.addItem(new TempDoubleBiome(158, tbound, 133), 2);
		}
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		if((tile.getBiomeSeed() % 5) == 0) return alpine.getBiome(tile);
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
