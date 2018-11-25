/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.generator;

import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;
import jaredbgreat.climaticbiome.generation.cache.Coords;

/**
 *
 * @author Jared Blackburn
 */
public class ChunkTile extends AbstractCachable {
    static final int size = 16;
    final int x, z;
    int val = 0;
    int rlBiome;
    int temp = 0, wet = 0;
    int biomeSeed = 0, biome = 0;
    int noiseVal = 0;
    boolean mountain = false, hilly = false, river = false;
    
    
    public ChunkTile(int x, int z) {
    	super(x, z);
        this.x = x;
        this.z = z;
    }
    
    
    public ChunkTile(Coords coords, ChunkTile other) {
    	super(coords);
        this.x = other.x;
        this.z = other.z;
        this.val = other.val;
        //this.rlBiome = other.rlBiome;
        this.temp = other.temp; 
        this.wet = other.wet;
        this.biomeSeed = other.biomeSeed;
        this.biome = other.biome;
        this.noiseVal = other.noiseVal;
        this.mountain = other.mountain;
        this.hilly = other.hilly;
        this.river = other.river;
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

    public int getRlBiome() {
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

    public boolean isRiver() {
        return river;
    }

    public void beRiver() {
        river = true;
    }
    
    
    public ChunkTile nextBiomeSeed() {
    	biomeSeed ^= biomeSeed << 13;
    	biomeSeed ^= biomeSeed >> 19;
    	biomeSeed ^= biomeSeed << 7;
    	biomeSeed &= 0x7fffffff;
    	return this;
    }
    
}
