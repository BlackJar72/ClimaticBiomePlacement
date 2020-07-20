package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.cache.AbstractWeaklyCacheable;
import jaredbgreat.climaticbiome.generation.cache.WeakCache;
import jaredbgreat.climaticbiome.util.HeightNoiseMap;
import jaredbgreat.climaticbiome.util.ModMath;
import jaredbgreat.climaticbiome.util.SpatialHash;

public class HeightMapArea extends AbstractWeaklyCacheable {
	public static final int CSIZE = 8;          // Width and depth in chunks
	public static final int BSIZE = CSIZE * 16; // Width and depth in blocks
	public static final int SCALE = 160;         // Multiplier for height
	
	private final float[][] heightMap;
	private final float[][] heightMap2;
	private final float[][] min;
	private final float[][] max;
	
	
	public HeightMapArea(int x, int z, SpatialHash rand, SpatialHash rand2, 
			SpatialHash rand3, SpatialHash rand4, HeightMapManager manager) {
		super(x, z, manager.getCache());
		//System.out.println("x = " + x + ", z = " + z);
    	HeightNoiseMap noise = new HeightNoiseMap(BSIZE, BSIZE, BSIZE, SCALE);
    	HeightNoiseMap noise4 = new HeightNoiseMap(BSIZE, BSIZE, 32, 16);
    	HeightNoiseMap noise2 = new HeightNoiseMap(BSIZE, BSIZE, 32, 48);
    	HeightNoiseMap noise3 = new HeightNoiseMap(BSIZE, BSIZE, 16, 1);
    	heightMap = noise.process(rand, x * BSIZE, z * BSIZE);
    	min = noise4.process(rand2, x * BSIZE, z * BSIZE);
    	max = noise2.process(rand3, x * BSIZE, z * BSIZE);
    	heightMap2 = noise3.process(rand4, x * BSIZE, z * BSIZE);
	}
	
	
	public int[][] getChunkHieghts(int x, int z, float[] biomeData) {
    	int[][] out = new int[4][256];
    	int startx = ModMath.modRight(x, CSIZE) * 16;
    	int startz = ModMath.modRight(z, CSIZE) * 16;
    	int endx   = startx + 16;
    	int endz   = startz + 16;
    	int ix = 0, jz = 0;
    	int index;
    	{/*
    		//statArray(heightMap, "Map One (#1)");
    		statArray(heightMap2, "Map Two (#2)");
    	*/}
    	for(int i = startx; i < endx; i++, ix++) {
    		jz = 0;
    		for(int j = startz; j < endz; j++, jz++) {
    			index = (ix * 16) + jz;
    			out[0][index] = (int)((heightMap[i][j] * biomeData[index + 256]) 
    					+ (biomeData[index] * 20) + 68);
    			out[1][index] = (int)((min[i][j] * biomeData[index + 256]) 
    					+ (biomeData[index] * 20) + 68);
    			out[2][index] = (int)((max[i][j] * biomeData[index + 256]) 
    					+ (biomeData[index] * 20) + 68);
    			out[3][index] = (int)heightMap2[i][j];
    		}
    	}
    	return out;
	}
	
	
	private void statArray(float[][] in, String name) {
		float min = Float.NEGATIVE_INFINITY;
		float max = Float.POSITIVE_INFINITY;
		float sum = 0;
		float avg = 0;
		float std = 0;
		int   num = 0;
		float tmp;
		for(int i = 0; i < in.length; i++)
			for(int j = 0; j < in[i].length; j++) {
				num++;
				if(in[i][j] > min) min = in[i][j];
				if(in[i][j] < max) max = in[i][j];
				sum += in[i][j];
			}
		avg = sum / num;
		for(int i = 0; i < in.length; i++)
			for(int j = 0; j < in[i].length; j++) {
				tmp = in[i][j] - avg;
				std += (tmp * tmp);
			}
		std /= num;
		std = (float)Math.sqrt(std);
		System.out.println();
		System.out.println("****************");
		System.out.println("Array Name: " + name );
		System.out.println("N = " + num);
		System.out.println("Minimum = " + min);
		System.out.println("Maximum = " + max);
		System.out.println("Total = " + sum);
		System.out.println("Mean = " + avg);
		System.out.println("Standard Deviation = " + std);
		System.out.println("****************");
		System.out.println();
	}
	

}
