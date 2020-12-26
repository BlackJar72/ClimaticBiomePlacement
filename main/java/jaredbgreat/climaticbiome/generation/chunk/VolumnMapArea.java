package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.cache.AbstractWeaklyCacheable;
import jaredbgreat.climaticbiome.util.ModMath;
import jaredbgreat.climaticbiome.util.SpatialHash;
import jaredbgreat.climaticbiome.util.VolumnNoiseMap;

public class VolumnMapArea extends AbstractWeaklyCacheable {
	public static final int CSIZE = 8;          // Width and depth in chunks
	public static final int BSIZE = CSIZE * 2; // Width and depth in blocks
	public static final int SCALE = 192;         // Multiplier for height
	
	private final VolumnNoiseMap noiseMap;
	
	
	public VolumnMapArea(int x, int z, SpatialHash rand, VolumnMapManager manager) {
		super(x, z, manager.getCache());
		noiseMap = new VolumnNoiseMap(BSIZE, BSIZE, BSIZE, 8, 32, 8, BSIZE, SCALE)
    						 .process(rand, x * BSIZE, z * BSIZE);
	}
	
	
	public int[] getChunkHieghts(int x, int z, float[] biomeData) {
    	int[] out = new int[65536];
    	int startx = ModMath.modRight(x, CSIZE) * 16;
    	int startz = ModMath.modRight(z, CSIZE) * 16;
    	int endx   = startx + 16;
    	int endz   = startz + 16;
    	int ix = 0, jz = 0;
    	int index1, index2;
    	for(int i = startx; i < endx; i++, ix++) {
    		jz = 0;
    		for(int j = startz; j < endz; j++, jz++) {
    			index1 = (ix * 16) + jz;
        		for(int k = 0; k < 256; k++) {
        			index2 = (index1 * 256) + k;
	    			out[index2] = (int)((noiseMap.get(i,  k,  j) * biomeData[index1 + 256]) 
	    					+ (biomeData[index1] * 20) + 64);
        		}
    		}
    	}
    	return out;
	}
	
	
	public float[] getChunkNoise(int x, int z, float[] biomeData) {
    	float[] out = new float[1024];
    	int startx = ModMath.modRight(x, CSIZE) * 4;
    	int startz = ModMath.modRight(z, CSIZE) * 4;
    	int endx   = startx + 4;
    	int endz   = startz + 4;
    	int ix = 0, jz = 0;
    	int index1, index2;
    	for(int i = startx; i < endx; i++, ix++) {
    		jz = 0;
    		for(int j = startz; j < endz; j++, jz++) {
        		for(int k = 0; k < 64; k++) {
	    			index2 = (((ix * 4) + jz) * 64) + k;
	    			out[index2] = (noiseMap.get(i,  k,  j) / SCALE) * 4;
        		}
    		}
    	}
    	return out;
	}
	
	
	/**
	 * A method to analyze hieghtmaps for debugging. I'm 
	 * not sure how valid it all is (i.e., I don't know 
	 * if these noise functions are even close to normally
	 * distributed, and don't even remember the formulae 
	 * for tests of statistical normality.  However, its 
	 * good for getting a general sense of the spread of 
	 * a 2D array (or the range if nothing else).
	 * 
	 * @param in
	 * @param name
	 */
	public static void statArray(float[][] in, String name) {
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
	
	
	/**
	 * A method to analyze hieghtmaps for debugging. I'm 
	 * not sure how valid it all is (i.e., I don't know 
	 * if these noise functions are even close to normally
	 * distributed, and don't even remember the formulae 
	 * for tests of statistical normality.  However, its 
	 * good for getting a general sense of the spread of 
	 * a 2D array (or the range if nothing else).
	 * 
	 * @param in
	 * @param name
	 */
	public static void statArray(double[][] in, String name) {
		double min = Double.NEGATIVE_INFINITY;
		double max = Double.POSITIVE_INFINITY;
		float sum = 0;
		float avg = 0;
		float std = 0;
		int   num = 0;
		double tmp;
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
