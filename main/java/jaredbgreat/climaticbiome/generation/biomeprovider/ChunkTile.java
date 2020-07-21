/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.biomeprovider;

import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;

/**
 *
 * @author Jared Blackburn
 */
public class ChunkTile extends AbstractCachable {
    static final int size = 16;
    final int x, z, tx, tz;
    int val = 0;
    int rlBiome;
    int temp = 0, wet = 0;
    int biomeSeed = 0;
    int noiseVal = 0;
    int river;
    double height;
    float scale;
    boolean beach;
    TerrainType terrainType = TerrainType.VARIABLE;
    
    
    public ChunkTile(int x, int z, int xoff, int zoff) {
    	super(x, z);
        this.x = x;
        this.z = z;
        tx = x + xoff;
        tz = z + zoff;
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


    public int getTX() {
        return tx;
    }

    public int getTZ() {
        return tz;
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
    
    public double getHeight() {
    	return height;
    }

    public int getBiomeSeed() {
        return biomeSeed;
    }

    public int getNoise() {
        return noiseVal;
    }

    public boolean isBeach() {
        return beach;
    }

    public boolean isMountain() {
        return terrainType == TerrainType.MOUNTIANOUS;
    }

    public boolean isVanilla() {
        return terrainType == TerrainType.VANILLA;
    }

    public boolean isSteep() {
        return terrainType == TerrainType.STEEP;
    }

    public boolean isNoramlTerrain() {
        return terrainType == TerrainType.VARIABLE;
    }

    public boolean isAveragedTerrain() {
        return terrainType == TerrainType.AVERAGED;
    }
    
    public TerrainType getTerrainType() {
    	return terrainType;
    }

    public boolean isRiver() {
        return river > 0;
    }

    public void beRiver(int id) {
        river = id;
    }      

    public void setTerrainType(TerrainType type) {
        terrainType = type;
    }
    
    public void setMountainous() {
    	if(terrainType == TerrainType.VARIABLE) 
    		terrainType = TerrainType.MOUNTIANOUS;
    }
    
    public void setVanilla() {
    	if(terrainType == TerrainType.VARIABLE) 
    		terrainType = TerrainType.VANILLA;
    }
    
    public void setSteep() { 
    		terrainType = TerrainType.STEEP;
    }
    
    public void setPlateau() { 
    		terrainType = TerrainType.PLATEAU;
    }
    
    public void setNormalTerrain() {
    	if(terrainType != TerrainType.STEEP) 
    		terrainType = TerrainType.VANILLA;
    }
    
    public void setAveraged() {
    	if(terrainType == TerrainType.VARIABLE) 
    		terrainType = TerrainType.AVERAGED;
    }
    
    
    public ChunkTile nextBiomeSeed() {
    	biomeSeed ^= biomeSeed << 13;
    	biomeSeed ^= biomeSeed >> 19;
    	biomeSeed ^= biomeSeed << 7;
    	biomeSeed &= 0x7fffffff;
    	return this;
    }
    
}
