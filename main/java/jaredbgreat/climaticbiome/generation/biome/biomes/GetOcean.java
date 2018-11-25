package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.generation.biome.BiomeClimateTable;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.generator.ChunkTile;


public class GetOcean implements IBiomeSpecifier {
	private static GetOcean oceans;
	private GetOcean() {
		super();
		init();
	}
	BiomeList frozen;
	BiomeList cold;
	BiomeList cool;
	BiomeList warm;
	BiomeList hot;
	BiomeList dfrozen;
	BiomeList dcold;
	BiomeList dcool;
	BiomeList dwarm;
	BiomeList dhot;
	IBiomeSpecifier islands1; // Main land biomes
	IBiomeSpecifier islands2; // Special island-only biomes
	
	
	public void init() {
		// Shallows
		frozen = new BiomeList();
		cold   = new BiomeList();
		cool   = new BiomeList();
		warm   = new BiomeList();
		hot    = new BiomeList();
		//Deeps
		dfrozen = new BiomeList();
		dcold   = new BiomeList();
		dcool   = new BiomeList();
		dwarm   = new BiomeList();
		dhot    = new BiomeList();
		// Islands
		islands1 = BiomeClimateTable.getLandTable();
		islands2 = GetIslands.getIslands();
		// Add biomes
		cool.addItem(new LeafBiome(0));
		dcool.addItem(new LeafBiome(24));
		frozen.addItem(new LeafBiome(10));
		dfrozen.addItem(new LeafBiome(10));
		/*
		 * In this area populate the ocean lists
		 */
		// MUST BE LAST, ALWAYS!!!
		fixOceans();
	}	

	
	@Override
	public int getBiome(ChunkTile tile) {
		int temp = tile.getTemp();
		int seed = tile.getBiomeSeed();
		// FIXME: WRONG NOISE!    Create other noise,
		//        this is not the noise I want!
		//        This means adding ice noise to the main map.
		int iceNoise = tile.getNoise();
		tile.nextBiomeSeed();
		if((seed % 5) == 0) {
			int noise = tile.getNoise();
			if(((seed % 13) == 0) && (tile.getTemp() > 9) && (tile.getTemp() < 19) 
					              && (tile.getWet() > 3)  && (tile.getVal() < 4)) {
				if(noise < 5) {
					return 14;
				}
				if(noise < 7) {
					return 15;
				}
				return 0;				
			}
			if((seed & 1) == 0) {
				if(noise > (4 + (seed % 3))) {
					return islands1.getBiome(tile);
				}
			} else {
				if(noise > (2 + (seed % 3))) {
					return islands2.getBiome(tile);
				}				
			}
	        if(((iceNoise / 2) - temp) > -1) {
	        	return frozen.getBiome(tile);
	        }
        	if(temp < 6) {
        		return cold.getBiome(tile);
        	} 
        	if(temp < 12) {
        		return cool.getBiome(tile);        		
        	} 
        	if(temp < 18) {
        		return warm.getBiome(tile);
        	}
        	return hot.getBiome(tile);
        
		}
        if(((iceNoise / 2) - temp) > -1) {
        	if(tile.getVal() < 2) {
        		return dfrozen.getBiome(tile);        		
        	}
        	return frozen.getBiome(tile);
        }
        if((tile.getVal()) < 3) {
        	if(temp < 7) {
        		return dcold.getBiome(tile);
        	} 
        	if(temp < 13) {
        		return dcool.getBiome(tile);        		
        	} 
        	if(temp < 19) {
        		return dwarm.getBiome(tile);
        	}
        	return dhot.getBiome(tile);
        } else {
        	if(temp < 6) {
        		return cold.getBiome(tile);
        	} 
        	if(temp < 12) {
        		return cool.getBiome(tile);        		
        	} 
        	if(temp < 18) {
        		return warm.getBiome(tile);
        	}
        	return hot.getBiome(tile);
        }
    }
	
	
	public static GetOcean getOcean() {
		if(oceans == null) {
			oceans = new GetOcean();			
		}
		return oceans;
	}
	
	
	/**
	 * This fixes possible problems with ocean,
	 * specifically, makes sure there are no 
	 * oceans types empty.  This way mods can 
	 * add temperature specific oceans, while 
	 * not relying on them.
	 */
	private void fixOceans() {
		if(warm.isEmpty()) {
			warm = cool;
		}
		if(hot.isEmpty()) {
			hot = warm;
		}
		if(cold.isEmpty()) {
			cold = cool;
		}
		if(frozen.isEmpty()) {
			// Should never be true, but just in case.
			frozen = cold; 
		}
		if(dcool.isEmpty()) {
			// Should never be true, but just in case.
			dcool = cool;
		}
		if(dwarm.isEmpty()) {
			dwarm = dcool;
		}
		if(dhot.isEmpty()) {
			dhot = dwarm;
		}
		if(dcold.isEmpty()) {
			dcold = dcool;
		}
		if(dfrozen.isEmpty()) {
			// Should never be true, but just in case.
			dfrozen = dcold; 
		}
	}


	@Override
	public boolean isEmpty() {
		return false;
	}


}
