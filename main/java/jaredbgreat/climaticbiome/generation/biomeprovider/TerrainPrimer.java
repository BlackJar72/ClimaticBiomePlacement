package jaredbgreat.climaticbiome.generation.biomeprovider;

import jaredbgreat.climaticbiome.util.NoiseMap;
import static jaredbgreat.climaticbiome.generation.biomeprovider.MapMaker.*;

public class TerrainPrimer {
	
	public int[] processTerrain(ChunkTile[] tiles, NoiseMap noise) {
		int[] out = new int[tiles.length];
		double[][] scaleNoise = noise.process(1001);
		for(int i = 0; i < tiles.length; i++) {
			int x = i / RSIZE;
			int z = i % RSIZE;
			if(tiles[i].isRiver()) tiles[i].setSteep();
			tiles[i].height = (tiles[i].height - 0.6);
			tiles[i].scale = (float)Math.min(((Math.max((scaleNoise[x][z] * 2
					+ 0.4d + (tiles[i].height) / 2d) / 5d, 0d))), tiles[i].height);
			lowerRiver(tiles, x, z);
			tiles[i].terrainType.heightAdjuster.processTile(tiles[i]);
			if(tiles[i].height > 2) tiles[i].height = 3 - (1 / (tiles[i].height - 1));
			out[i] =  Math.max(Math.min((int)((averageHeight(tiles, x, z) * 32d) + 128d), 255), 0) +
					 (Math.max(Math.min((int)((averageScale(tiles, x, z)  * 32d) + 128d), 255), 0) << 8) +
					 (tiles[i].terrainType.ordinal() << 16);
		}
		return out;
	}
	
	
	private ChunkTile getTileFromCoords(ChunkTile[] tiles, int x, int z) {
		return tiles[(x * RSIZE) + z];
	}
    
   
    private double averageHeight(ChunkTile[] tiles, int x, int z) {
    	ChunkTile center = getTileFromCoords(tiles, x, z);
    	double out = center.height;
    	if((x > 0) && (x < RSIZE - 1) && (z > 0) && (z < RSIZE - 1) && !center.isSteep()) {
    		out  = (getTileFromCoords(tiles, x - 1, z - 1).height
				 +  getTileFromCoords(tiles, x - 1, z).height
				 +  getTileFromCoords(tiles, x - 1, z + 1).height
				 +  getTileFromCoords(tiles, x, z - 1).height
				 +  getTileFromCoords(tiles, x - 1, z + 1).height
				 +  getTileFromCoords(tiles, x + 1, z - 1).height
				 +  getTileFromCoords(tiles, x + 1, z).height
				 +  getTileFromCoords(tiles, x - 1, z + 1).height
				 +  (center.height * 2f)) / 9f;
    	}
    	return out;
    }
    
   
    private float averageScale(ChunkTile[] tiles, int x, int z) {
    	ChunkTile center = getTileFromCoords(tiles, x, z);
    	float out = center.scale;
    	if((x > 0) && (x < RSIZE - 1) && (z > 0) && (z < RSIZE - 1) && !center.isSteep()) {
			out  = (getTileFromCoords(tiles, x - 1, z - 1).scale
				 +  getTileFromCoords(tiles, x - 1, z).scale
				 +  getTileFromCoords(tiles, x - 1, z + 1).scale
				 +  getTileFromCoords(tiles, x, z - 1).scale
				 +  getTileFromCoords(tiles, x - 1, z + 1).scale
				 +  getTileFromCoords(tiles, x + 1, z - 1).scale
				 +  getTileFromCoords(tiles, x + 1, z).scale
				 +  getTileFromCoords(tiles, x - 1, z + 1).scale
				 +  center.scale) / 9f;
    	}
    	return out;
    }
    
    
    private void lowerRiver(ChunkTile[] tiles, int x, int z) {
    	ChunkTile center = getTileFromCoords(tiles, x, z);   
    	if((x > 0) && (x < RSIZE - 1) && (z > 0) && (z < RSIZE - 1) && !center.isSteep()) {
    		if(getTileFromCoords(tiles, x - 1, z - 1).isRiver()
				 || getTileFromCoords(tiles, x - 1, z).isRiver()
				 || getTileFromCoords(tiles, x - 1, z + 1).isRiver()
				 || getTileFromCoords(tiles, x, z - 1).isRiver()
				 || getTileFromCoords(tiles, x - 1, z + 1).isRiver()
				 || getTileFromCoords(tiles, x + 1, z - 1).isRiver()
				 || getTileFromCoords(tiles, x + 1, z).isRiver()
				 || getTileFromCoords(tiles, x - 1, z + 1).isRiver()) {
    			center.height *= 0.5d;
    		}
    	}
    }
    
    
}
