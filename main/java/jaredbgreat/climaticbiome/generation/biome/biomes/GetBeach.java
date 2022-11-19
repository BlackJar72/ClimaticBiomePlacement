package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;
import net.minecraft.world.biome.Biome;

public class GetBeach implements IBiomeSpecifier {
	private static GetBeach beaches;
	private GetBeach() {
		super();
		init();
	}
	private BiomeList cold;
	private BiomeList cool;
	private BiomeList rock;
	private BiomeList temp;
	private BiomeList warm;	
	private static int tbound;
	private BiomeList hot;
	
	
	public void init() {
		if(ConfigHandler.useBoP || ConfigHandler.useBoPTable) {
			tbound = 6;
		} else {
			tbound = 7;
		}
		cold = new BiomeList();
		cool = new BiomeList();
		rock = new BiomeList();
		temp = new BiomeList();
		warm = new BiomeList();
		hot  = new BiomeList();
		DefReader.readBiomeData(cold, "BeachCold.cfg");
		DefReader.readBiomeData(rock, "BeachRocky.cfg");
		DefReader.readBiomeData(cool, "BeachCool.cfg");
		DefReader.readBiomeData(temp,  "BeachTemporate.cfg");
		DefReader.readBiomeData(warm,  "BeachWarm.cfg");
		DefReader.readBiomeData(hot,  "BeachHot.cfg");
		if(cold.isEmpty()) cold.addItem(new LeafBiome(Biome.getBiome(26)));
		if(rock.isEmpty()) rock.addItem(new LeafBiome(Biome.getBiome(25)));
		if(warm.isEmpty()) warm.addItem(new LeafBiome(Biome.getBiome(16)));
		if(temp.isEmpty()) temp = warm;
		if(cool.isEmpty()) cool = temp;
		if(hot.isEmpty())  hot  = warm;
	}
	
	
	@Override
	public long getBiome(ChunkTile tile) {
		tile.setVanilla();
		if(tile.getTemp() < tbound) {
			return cold.getBiome(tile);
		}
		int t = tile.getTemp();
		if(t < 12) {
			return cool.getBiome(tile);
		} else if(t < 16) {
			return temp.getBiome(tile);
		} else if(t < 21) {
			return warm.getBiome(tile);
		} else {
			return hot.getBiome(tile);
		}
	}
	
	
	public long getHighBiome(ChunkTile tile) {
		if(tile.getTemp() < tbound) {
			return rock.getBiome(tile);
		} else return warm.getBiome(tile);//return 0;
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
