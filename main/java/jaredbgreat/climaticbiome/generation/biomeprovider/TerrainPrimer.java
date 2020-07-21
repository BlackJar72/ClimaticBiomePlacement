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
			//System.out.println(tiles[i].height);
			tiles[i].scale = (float)Math.min(((Math.max((scaleNoise[x][z] * 2
					+ 0.4d + (tiles[i].height) / 2d) / 5d, 0d))), tiles[i].height);
			//if(tiles[i].height < 0) System.out.println(tiles[i].scale);
			tiles[i].terrainType.heightAdjuster.processTile(tiles[i]);
			if(tiles[i].height > 2) tiles[i].height = 3 - (1 / (tiles[i].height - 1));
			if((x > 0) && (x < RSIZE - 1) && (z > 0) && (z < RSIZE - 1)) {
				averageHeight(tiles, x, z);
				averageScale(tiles, x, z);
			}
			out[i] =  Math.max(Math.min((int)((tiles[i].height * 32d) + 128d), 255), 0) +
					 (Math.max(Math.min((int)((tiles[i].scale  * 32d) + 128d), 255), 0) << 8) +
					 (tiles[i].terrainType.ordinal() << 16);
		}
		return out;
	}
	
	
	private ChunkTile getTileFromCoords(ChunkTile[] tiles, int x, int z) {
		return tiles[(x * 16) + z];
	}
    
   
    private void averageHeight(ChunkTile[] tiles, int x, int z) {
    	ChunkTile center = getTileFromCoords(tiles, x, z);
    	if(!center.isSteep()) {
			double out  = (getTileFromCoords(tiles, x - 1, z - 1).height
						+  getTileFromCoords(tiles, x - 1, z).height
						+  getTileFromCoords(tiles, x - 1, z + 1).height
						+  getTileFromCoords(tiles, x, z - 1).height
						+  getTileFromCoords(tiles, x - 1, z + 1).height
						+  getTileFromCoords(tiles, x + 1, z - 1).height
						+  getTileFromCoords(tiles, x + 1, z).height
						+  getTileFromCoords(tiles, x - 1, z + 1).height) / 8.0;
	    	center.height = (out + center.height) / 2.0d;
    	}
    }
    
   
    private void averageScale(ChunkTile[] tiles, int x, int z) {
    	ChunkTile center = getTileFromCoords(tiles, x, z);
    	if(!center.isSteep()) {
			center.scale  = (getTileFromCoords(tiles, x - 1, z - 1).scale
						  +  getTileFromCoords(tiles, x - 1, z).scale
						  +  getTileFromCoords(tiles, x - 1, z + 1).scale
						  +  getTileFromCoords(tiles, x, z - 1).scale
						  +  getTileFromCoords(tiles, x - 1, z + 1).scale
						  +  getTileFromCoords(tiles, x + 1, z - 1).scale
						  +  getTileFromCoords(tiles, x + 1, z).scale
						  +  getTileFromCoords(tiles, x - 1, z + 1).scale
						  +  getTileFromCoords(tiles, x, z).scale) / 9.0f;
    	}
    }
    
    
}
