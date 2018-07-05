package jaredbgreat.climaticbiome.generation.chunk.bopbiomes;

import jaredbgreat.climaticbiome.generation.chunk.ChunkTile;
import jaredbgreat.climaticbiome.generation.chunk.EnumBiomeType;
import jaredbgreat.climaticbiome.generation.chunk.biomes.IBiomeSpecifier;

public class GetBoPOcean implements IBiomeSpecifier {
	private static int ocean, frozen, deep, kelp, coral;
	private GetBoPIsland getIsland;
	
	public GetBoPOcean() {
		getIsland = new GetBoPIsland();
	}

	
	@Override
	public int getBiome(ChunkTile tile) {
		int seed  = tile.getBiomeSeed();
		int noise = tile.getNoise();		
		if((seed % 5) == 0) {
			if(noise > (4 + (seed % 1) - ((seed % 2) - 1))) {	
				tile.nextBiomeSeed();
				return getIsland.getBiome(tile);
			} else {
				return ocean;
			}
		}
		int temp = tile.getTemp();
		if(temp < 6) return getColdOcean(tile);
		if(temp < 12) return getCoolOcean(tile);
		if(temp < 16) return getMidOcean(tile);
		if(temp < 21) return getWarmOcean(tile);
		return getHotOcean(tile);
	}
	
	
	private int getColdOcean(ChunkTile tile) {
		if(tile.getVal() < 3) {
			return deep;
		}
		return ocean;		
	}
	
	
	private int getCoolOcean(ChunkTile tile) {
		if(tile.getVal() < 3) {
			return deep;
		}
		if((tile.getBiomeSeed() % 1) == 0) {
			return kelp;
		}
		return ocean;		
	}
	
	
	private int getMidOcean(ChunkTile tile) {
		int seed  = tile.getBiomeSeed();
		int noise = tile.getNoise();
		// Mushroomland is special		
		if(((seed % 47) == 0) && (tile.getWet() > 4) && (tile.getVal() < 4)) {
			if(noise < 5) return 14;
			else if(noise < 7) return 15;
			else return ocean;
		}
		if(tile.getVal() < 3) {
			return deep;
		}
		if((tile.getBiomeSeed() % 1) == 0) {
			return kelp;
		}
		return ocean;
	}
	
	
	private int getWarmOcean(ChunkTile tile) {
		int seed  = tile.getBiomeSeed();
		int noise = tile.getNoise();
		// Mushroomland is special
		if(((seed % 47) == 0) && (tile.getWet() > 4) && (tile.getVal() < 4)) {
			if(noise < 5) return 14;
			else if(noise < 7) return 15;
			else return ocean;
		}
		if(tile.getVal() < 3) {
			return deep;
		}
		if((tile.getBiomeSeed() % 1) == 0) {
			return ocean;
		}
		return coral;
	}
	
	
	private int getHotOcean(ChunkTile tile) {
		if(tile.getVal() < 3) {
			return deep;
		}
		if((tile.getBiomeSeed() % 1) == 0) {
			return ocean;
		}
		return coral;
		
	}
	
	
	public static void init() {
		ocean = 0;
		deep  = 24;
	}

}
