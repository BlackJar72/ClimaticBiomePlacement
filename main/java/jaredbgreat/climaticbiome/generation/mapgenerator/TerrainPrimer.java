package jaredbgreat.climaticbiome.generation.mapgenerator;

import static jaredbgreat.climaticbiome.generation.mapgenerator.MapMaker.RSIZE;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.generation.map.IRegionMap;
import jaredbgreat.climaticbiome.util.NoiseMap2D;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;


public class TerrainPrimer {
	
	public void processTerrain(ChunkTile[] tiles, IRegionMap datamap, NoiseMap2D noise, 
				SizeScale scale, ClimaticWorldSettings settings) {
		int[] out = new int[tiles.length];
		double[][] scaleNoise  = noise.process(1001);
		double[][] heightNoise = noise.process(8675);
		int D = RSIZE * scale.whole;
		for(int i = 0; i < tiles.length; i++) {
			int x = i / D;
			int z = i % D;
			if(tiles[i].isRiver()) tiles[i].setSteep();
			tiles[i].height = Math.max((heightNoise[x][z] + 1), 0) * (tiles[i].height - 0.6);
			tiles[i].scale = (float)Math.min(((Math.max((scaleNoise[x][z] * 2
					+ 0.4d + (tiles[i].height) / 2d) / 5d, 0d))), tiles[i].height);
			lowerRiver(tiles, x, z, scale.whole);
			tiles[i].terrainType.heightAdjuster.processTile(tiles[i], settings);
			
			if((x > 1) && (x < (D - 2)) && (z > 1) && (z < (D - 2)) 
					&& ((tiles[i].isMountain())) &&  !(scale == SizeScale.X1)) {
				smooth(tiles, x, z, D, scale);
			}
			
			if(tiles[i].height > 3) tiles[i].height = 4 - (1 / (tiles[i].height - 1));
			datamap.setTerrainExpress(Math.max(Math.min((int)((averageHeight(tiles, x, z, scale.whole) 
							 * 32d) + 128d), 255), 0) +
					 (Math.max(Math.min((int)((averageScale(tiles, x, z, scale.whole)  
							 * 32d) + 128d), 255), 0) << 8), i);
		}
	}
	
	
	private void smooth(ChunkTile[] tiles, int x, int z, int D, SizeScale scale) {
		if(scale == SizeScale.X2) { 
			ChunkTile tile = tiles[(x * D) + z];
			//System.out.print(tile.height + " -> ");
			for(int i = x - 1; i < (x + 2); i++)
				for(int j = z - 1; j < (z + 2); j++) {
					tile.height += tiles[(i * D) + j].height;
					tile.scale  += tiles[(i * D) + j].scale;
				}
			//System.out.print(tile.height + " -> ");
			tile.height /= 10.0;
			tile.scale  /= 10.0;
			//System.out.println(tile.height);*/
		} else {
			ChunkTile tile = tiles[(x * D) + z];
			double h1 = tile.height, s1 = tile.scale;
			for(int i = x - 1; i < (x + 2); i++)
				for(int j = z - 1; j < (z + 2); j++) {
					h1 += tiles[(i * D) + j].height;
					s1 += tiles[(i * D) + j].scale;
				}
			h1 /= 10.0;
			s1 /= 10.0;
			double h2 = 0, s2 = 0;
			for(int i = x - 2; i < (x + 3); i++)
				for(int j = z - 2; j < (z + 3); j++) {
					h2 += tiles[(i * D) + j].height;
					s2 += tiles[(i * D) + j].scale;
				}
			h2 /= 25.0;
			s2 /= 25.0;
			tile.height = (h1 + h2) / 2.0;
			//tile.scale  = (float)s1;//(float)((s1 + s2) / 2.0);
		}
	}
	
	
	private ChunkTile getTileFromCoords(ChunkTile[] tiles, int x, int z, int size) {
		return tiles[(x * RSIZE * size) + z];
	}
    
   
    private double averageHeight(ChunkTile[] tiles, int x, int z, int size) {
    	ChunkTile center = getTileFromCoords(tiles, x, z, size);
    	double out = center.height;
    	if((x > 0) && (x < RSIZE - 1) && (z > 0) && (z < RSIZE - 1) && !center.isSteep()) {
    		out  = (getTileFromCoords(tiles, x - 1, z - 1, size).height
				 +  getTileFromCoords(tiles, x - 1, z, size).height
				 +  getTileFromCoords(tiles, x - 1, z + 1, size).height
				 +  getTileFromCoords(tiles, x, z - 1, size).height
				 +  getTileFromCoords(tiles, x - 1, z + 1, size).height
				 +  getTileFromCoords(tiles, x + 1, z - 1, size).height
				 +  getTileFromCoords(tiles, x + 1, z, size).height
				 +  getTileFromCoords(tiles, x - 1, z + 1, size).height
				 +  (center.height * 2f)) / 9f;
    	}
    	return out;
    }
    
   
    private float averageScale(ChunkTile[] tiles, int x, int z, int size) {
    	ChunkTile center = getTileFromCoords(tiles, x, z, size);
    	float out = center.scale;
    	if((x > 0) && (x < RSIZE - 1) && (z > 0) && (z < RSIZE - 1) && !center.isSteep()) {
			out  = (getTileFromCoords(tiles, x - 1, z - 1, size).scale
				 +  getTileFromCoords(tiles, x - 1, z, size).scale
				 +  getTileFromCoords(tiles, x - 1, z + 1, size).scale
				 +  getTileFromCoords(tiles, x, z - 1, size).scale
				 +  getTileFromCoords(tiles, x - 1, z + 1, size).scale
				 +  getTileFromCoords(tiles, x + 1, z - 1, size).scale
				 +  getTileFromCoords(tiles, x + 1, z, size).scale
				 +  getTileFromCoords(tiles, x - 1, z + 1, size).scale
				 +  center.scale) / 9f;
    	}
    	return out;
    }
    
    
    private void lowerRiver(ChunkTile[] tiles, int x, int z, int size) {
    	ChunkTile center = getTileFromCoords(tiles, x, z, size);   
    	if((x > 0) && (x < RSIZE - 1) && (z > 0) && (z < RSIZE - 1) && !center.isSteep()) {
    		if(getTileFromCoords(tiles, x - 1, z - 1, size).isRiver()
				 || getTileFromCoords(tiles, x - 1, z, size).isRiver()
				 || getTileFromCoords(tiles, x - 1, z + 1, size).isRiver()
				 || getTileFromCoords(tiles, x, z - 1, size).isRiver()
				 || getTileFromCoords(tiles, x - 1, z + 1, size).isRiver()
				 || getTileFromCoords(tiles, x + 1, z - 1, size).isRiver()
				 || getTileFromCoords(tiles, x + 1, z, size).isRiver()
				 || getTileFromCoords(tiles, x - 1, z + 1, size).isRiver()) {
    			center.height *= 0.5d;
    		}
    	}
    }
    
    
    public static void makeFromVanilla(int[] data) {
    	for(int i = 0; i < data.length; i++) {
    		Biome biome = Biome.getBiome(data[i] & 0xff, Biomes.DEFAULT);
    		data[i] |= (Math.max(Math.min((int)((biome.getBaseHeight() 
								 * 32d) + 128d), 255), 0) << 16) +
						 (Math.max(Math.min((int)((biome.getHeightVariation()  
								 * 32d) + 128d), 255), 0) << 24);
    	}
    }
    
    
    public static void makeFromVanilla(long[] data) {
    	for(int i = 0; i < data.length; i++) {
    		Biome biome = Biome.getBiome((int)(data[i] & 0xffffffff), Biomes.DEFAULT);
    		data[i] |= (Math.max(Math.min((long)((biome.getBaseHeight() 
								 * 32d) + 128d), 255), 0) << 40) +
						 (Math.max(Math.min((long)((biome.getHeightVariation()  
								 * 32d) + 128d), 255), 0) << 48);
    	}
    }
    
    
}
