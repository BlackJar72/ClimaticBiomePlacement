/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiomes.generation.generator;

/**
 *
 * @author jared
 */
public class BiomeBasin {
    final int x, z, value;
    final double strength;
    ChunkTile center;

    public BiomeBasin(int x, int y, int value, double strength) {
        this.x = x;
        this.z = y;
        this.value = value;
        this.strength = strength;
        center = null;
    }

    public BiomeBasin(int x, int y, int value, double strength, MapMaker map) {
        this.x = x;
        this.z = y;
        this.value = value;
        this.strength = strength;
        center = map.getTile(x, y);
        center.biomeSeed = value;
    }
    
    
    public double getWeaknessAt(int atx, int aty) {
        double xdisplace = ((double)(x - atx));
        double ydisplace = ((double)(z - aty));
        return (xdisplace * xdisplace) + (ydisplace * ydisplace);
    }
    
    
    public static int summateEffect(BiomeBasin[][] n, ChunkTile t) {
    	return summateEffect(n, t.x, t.z);
    }
    
    
    public static int summateEffect(BiomeBasin[][] n, int x, int z) {
        double effect = 0.0;
        int indexx = 0;
        int indexy = 0;
        double power;
        for(int i = 0; i < n.length; i++) 
            for(int j = 0; j < n[i].length; j++) {
                power = n[i][j].strength / n[i][j].getWeaknessAt(x, z);
                if(effect < power) {
                    effect = power;
                    indexx = i;
                    indexy = j;
            }
        }
        return n[indexx][indexy].value;
    }
    
    
    public static ChunkTile summateForCenter(BiomeBasin[][] n, ChunkTile t) {
    	return summateForCenter(n, t.x, t.z);
    }
    
    
    public static ChunkTile summateForCenter(BiomeBasin[][] n, int x, int z) {
        double effect = 0.0;
        int indexx = 0;
        int indexy = 0;
        double power;
        for(int i = 0; i < n.length; i++) 
            for(int j = 0; j < n[i].length; j++) {
                power = n[i][j].strength / n[i][j].getWeaknessAt(x, z);
                if(effect < power) {
                    effect = power;
                    indexx = i;
                    indexy = j;
            }
        }
        return n[indexx][indexy].center;
    }
    
}
