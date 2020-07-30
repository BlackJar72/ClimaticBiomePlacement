package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

// TODO / FIXME: Fix for use with mods!
public class GetSwamp implements IBiomeSpecifier {
	private static GetSwamp swamp;
	private GetSwamp() {
		super();
		init();
	}
	BiomeList cold;
	BiomeList cool;
	BiomeList warm;
	BiomeList hot;
	
	
	public void init() {
		cold = new BiomeList();
		cool = new BiomeList();
		warm = new BiomeList();
		hot  = new BiomeList();
		DefReader.readBiomeData(cold, "SwampCold.cfg");
		DefReader.readBiomeData(cool, "SwampCool.cfg");
		DefReader.readBiomeData(warm, "SwampWarm.cfg");
		DefReader.readBiomeData(hot,  "SwampTropical.cfg");
		if(warm.isEmpty()) {
			warm.addItem(new LeafBiome(6), 3);
			warm.addItem(new LeafBiome(134), 1);
			if(cool.isEmpty()) {
				cool.addItem(new SeedDoubleBiome(134, 3, 6));
			}
		}
		// THIS MUST RUN LAST!!!
		fixSwamps();
	}
	

	@Override
	public long getBiome(ChunkTile tile) {
		tile.setSwamp();
		int temp = tile.getTemp();
    	if(temp < 12) {
    		return cold.getBiome(tile);
    	} 
    	if(temp < 16) {
    		return cool.getBiome(tile);        		
    	} 
    	if(temp < 21) {
    		return warm.getBiome(tile);
    	}
    	return hot.getBiome(tile);
	}
	
	
	public static GetSwamp getSwamp() {
		if(swamp == null) {
			swamp = new GetSwamp();
		}
		return swamp;
	}
	
	
	/**
	 * This fixes possible problems with ocean,
	 * specifically, makes sure there are no 
	 * oceans types empty.  This way mods can 
	 * add temperature specific oceans, while 
	 * not relying on them.
	 */
	private void fixSwamps() {
		if(cool.isEmpty()) {
			cool = warm;
		}
		if(cold.isEmpty()) {
			cold = cool;
		}
		if(hot.isEmpty()) {
			hot = warm;
		}
	}


	@Override
	public boolean isEmpty() {
		return false;
	}

}
