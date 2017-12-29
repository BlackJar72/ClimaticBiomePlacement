/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.chunk;

import net.minecraft.world.biome.Biome;

/**
 *
 * @author Jared Blackburn
 */
public class ChunkTile {
    static final int size = 16;
    final int x, z;
    int val = 0;
    BiomeType rlBiome;
    int temp = 0, wet = 0;
    int biomeSeed = 0, biome = 0;
    int noiseVal = 0;
    boolean mountain = false, hilly = false, land = false;
    
    
    public ChunkTile(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public static int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public int getVal() {
        return val;
    }

    public BiomeType getRlBiome() {
        return rlBiome;
    }

    public int getTemp() {
        return temp;
    }

    public int getWet() {
        return wet;
    }

    public int getBiomeSeed() {
        return biomeSeed;
    }

    public int getBiome() {
        return biome;
    }

    public int getNoise() {
        return noiseVal;
    }

    public boolean isIsMountain() {
        return mountain;
    }

    public boolean isIsHilly() {
        return hilly;
    }
    
    
    public void nextBiomeSeed() {
    	biomeSeed ^= biomeSeed << 13;
    	biomeSeed ^= biomeSeed >> 19;
    	biomeSeed ^= biomeSeed << 7;
    	biomeSeed &= 0x7fffffff;
    }
    
}
