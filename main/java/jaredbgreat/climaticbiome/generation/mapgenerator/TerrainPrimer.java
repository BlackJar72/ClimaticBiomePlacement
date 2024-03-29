package jaredbgreat.climaticbiome.generation.mapgenerator;

import static jaredbgreat.climaticbiome.generation.mapgenerator.MapMaker.RSIZE;

import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.generation.map.IRegionMap;
import jaredbgreat.climaticbiome.util.NoiseMap2D;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;


public class TerrainPrimer {
	
	public void processTerrain(ChunkTile[] tiles, IRegionMap datamap, NoiseMap2D noise, 
				SizeScale scale, ClimaticWorldSettings settings) {
		int[] out = new int[tiles.length];
		double[][] scaleNoise  = noise.process(1001);
		double[][] heightNoise = noise.process(8675);
		double[][] scratch = new double[tiles.length][2];
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
		}
		for(int i = 0; i < tiles.length; i++) {
			int x = i / D;
			int z = i % D;
			int max = scale.width - 2;
			if((tiles[i].shouldSmooth())) {
				smooth(tiles, x, z, scale, scratch);
			} else {
				scratch[i][0] = tiles[i].height;
				scratch[i][1] = tiles[i].scale;				
			}
		}
		for(int i = 0; i < tiles.length; i++) {
			int x = i / D;
			int z = i % D;
			tiles[i].height = scratch[i][0];
			tiles[i].scale = (float) scratch[i][1];
			if(tiles[i].height > 3) tiles[i].height = 4 - (1 / (tiles[i].height - 2));
			datamap.setTerrainExpress(Math.max(Math.min((int)((averageHeight(tiles, x, z, scale.whole) 
							 * 32d) + 128d), 255), 0) +
					 (Math.max(Math.min((int)((averageScale(tiles, x, z, scale.whole)  
							 * 32d) + 128d), 255), 0) << 8), i);
		}
	}
	
	
	private void smooth(ChunkTile[] tiles, int x, int z, SizeScale size, double[][] scratch) {
		int loc = x + (z * size.width);
		double bh = 0, bs = 0, ch = 0, cs = 0;
		Biome biome = Biome.getBiome(tiles[loc].rlBiome, Biomes.DEFAULT);
		if(BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER)) {
			scratch[loc][0] = tiles[loc].height;
			scratch[loc][1] = tiles[loc].scale;	
			return;
		}
		int i2, j2, index, max;
		max = size.width - 1;
		for(int i = -2; i < 3; i++)
			for(int j = -2; j < 3; j++) {
				 i2 = Math.max(Math.min(x + i, max), 0);
				 j2 = Math.max(Math.min(z + j, max), 0);
				 index = (j2 * size.width) + i2;
				 ch += tiles[index].height;
				 cs += tiles[index].scale;
		}
		for(int i = -1; i < 2; i++)
			for(int j = -1; j < 2; j++) {
				 i2 = Math.max(Math.min(x + i, max), 0);
				 j2 = Math.max(Math.min(z + j, max), 0);
				 index = (j2 * size.width) + i2;
				 bh += tiles[index].height;
				 bs += tiles[index].scale;
		}
		//Divisions and completion
		bh /=9;
		bs /=9;
		ch /=25;
		cs /= 25;
		//Apply
		if(BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN)) {
			scratch[loc][0] = Math.min(((tiles[loc].height + bh + ch) / 3), Math.max(-0.2, biome.getBaseHeight()));
			scratch[loc][1] = ((tiles[loc].scale + bs + cs) / 3);
		} else if(biome == ModBiomes.activeVolcano) {
			scratch[loc][0] = Math.max(tiles[loc].height,Math.max(((tiles[loc].height + bh + ch) / 3), 
					Math.min(0, biome.getBaseHeight())));
			scratch[loc][1] = ((tiles[loc].scale + bs + cs) / 3);
		} else {
			scratch[loc][0] = ch;
			scratch[loc][1] = cs;
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
