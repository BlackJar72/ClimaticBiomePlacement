package jaredbgreat.climaticbiome.generation.biomeprovider;

import jaredbgreat.climaticbiome.util.NoiseMap;
import static jaredbgreat.climaticbiome.generation.biomeprovider.MapMaker.*;

public class TerrainPrimer {
	
	public int[] processTerrain(ChunkTile[] tiles, NoiseMap noise) {
		int[] out = new int[tiles.length];
		double[][] scaleNoise = noise.process(1001);
		for(int i = 0; i < tiles.length; i++) {
			if(tiles[i].isRiver()) tiles[i].setSteep();
			tiles[i].height = (tiles[i].height - 0.6);
			//System.out.println(tiles[i].height);
			tiles[i].scale = (float)Math.min(((Math.max((scaleNoise[i / RSIZE][i % RSIZE] * 2
					+ 0.4d + (tiles[i].height) / 2d) / 5d, 0d))), tiles[i].height);
			//if(tiles[i].height < 0) System.out.println(tiles[i].scale);
			tiles[i].terrainType.heightAdjuster.processTile(tiles[i]);
			if(tiles[i].height > 2) tiles[i].height = 3 - (1 / (tiles[i].height - 1));
			out[i] =  Math.max(Math.min((int)((tiles[i].height * 32d) + 128d), 255), 0) +
					 (Math.max(Math.min((int)((tiles[i].scale  * 32d) + 128d), 255), 0) << 8) +
					 (tiles[i].terrainType.ordinal() << 16);
		}
		return out;
	}

}
