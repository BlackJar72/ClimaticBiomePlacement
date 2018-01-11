/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.chunk;

import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;


/**
 *
 * @author jared
 */
public final class Region extends AbstractCachable {
    BasinNode[] basins;
    ClimateNode[] temp;
    ClimateNode[] wet;
    // FIXME: These should not really be duplicated
    private static final int w = BiomeFinder.RSIZE, h = BiomeFinder.RSIZE;
    final int cx, cz;
    
    
    
    public Region(int x, int z, SpatialNoise random) {
    	super(x, z);
        cx = (x * BiomeFinder.RSIZE) - BiomeFinder.RADIUS;
        cz = (z * BiomeFinder.RSIZE) - BiomeFinder.RADIUS;
        makeBasins(5, 10, 15, random.getRandomAt(x, z, 0));
        makeTempBasins(10, random.getRandomAt(x, z, 1));
        makeRainBasins(12, random.getRandomAt(x, z, 2));
    }
    
    
    private BasinNode makeBasin(int value, double decay, SpatialNoise.RandomAt random) {
        int x = cx + random.nextInt(w);
        int z = cz + random.nextInt(h);
        return new BasinNode(x, z, value, 
                decay * 1.5);
    }
    
    
    private BasinNode makeCentralBasin1(int value, double decay, SpatialNoise.RandomAt random) {
        int x = cx + random.nextInt(w / 2) + (w / 4);
        int z = cz + random.nextInt(h / 2) + (h / 4);
        return new BasinNode(x, z, value, decay * 1.5);
    }
    
    
    private BasinNode makeCentralBasin2(int value, double decay, SpatialNoise.RandomAt random) {
        int x = cx + random.nextInt((w * 8) / 10) + (w / 10);
        int y = cz + random.nextInt((h * 8) / 10) + (h / 10);
        return new BasinNode(x, y, value, decay * 1.5);
    }
    
    
    public void makeBasins(int main, int pos, int neg, SpatialNoise.RandomAt random) {
        basins = new BasinNode[main + pos + neg];
        for(int i = 0; i < main; i++) {
            basins[i] = makeCentralBasin1(10, 
                    BasinNode.getLogScaled(-14) / 10, random);
        }
        for(int i = main; i < (pos + main); i++) {
            basins[i] = makeCentralBasin2(9, 
                    BasinNode.getLogScaled(random.nextInt(5) - 13) / 10, random);
        }
        for(int i = (pos + main); i < basins.length; i++) {
            basins[i] = makeBasin(0, 
                    BasinNode.getLogScaled(random.nextInt(10) - 15) / 10, random);
        }
    }
    
    
    private void makePoles(ClimateNode[] nodes, SpatialNoise.RandomAt random) {
        int dist = (BiomeFinder.RSIZE / 6) 
                + random.nextInt(BiomeFinder.RSIZE / 3);
        double angle = random.nextDouble() * 2 * Math.PI;
        int x = cx + BiomeFinder.RADIUS + (int)(dist * Math.cos(angle));
        int y = cz + BiomeFinder.RADIUS + (int)(dist * Math.sin(angle));
        nodes[0] = new ClimateNode(x, y, 0, 
                (BasinNode.getLogScaled(-14) / 40) * 1.5, 0);
        dist = (BiomeFinder.RSIZE / 6) + random.nextInt(BiomeFinder.RSIZE / 3);
//        angle = angle + (random.nextDouble() * (Math.PI / 2)) 
//        		+ ((Math.PI * 3) / 4);
        angle = angle + (random.nextDouble() * Math.PI) 
        		+ (Math.PI / 2);
        x = cx + (BiomeFinder.RADIUS) + (int)(dist * Math.cos(angle));
        y = cz + (BiomeFinder.RADIUS) + (int)(dist * Math.sin(angle));
        nodes[1] = new ClimateNode(x, y, 25, 
                (BasinNode.getLogScaled(-15) / 45) * 1.5, 0);        
    }
    
    
    public void makeTempBasins(int n, SpatialNoise.RandomAt random) {
        temp = new ClimateNode[n + 2];
        makePoles(temp, random);
        for(int i = 2; i < temp.length; i++) {
            temp[i] = new ClimateNode(
                cx + random.nextInt(BiomeFinder.RSIZE), 
                cz + random.nextInt(BiomeFinder.RSIZE), 
                random.nextInt(26), 
                (BasinNode.getLogScaled(random.nextInt(5) - 12) / 30) * 1.5, 
                random.nextInt(5) + 5);
        }
    }
    
    
    public void makeRainBasins(int n, SpatialNoise.RandomAt random) {
        wet = new ClimateNode[n];
        for(int i = 0; i < wet.length; i++) {
            int cycle = i % 3;
            switch(cycle) {
                case 0:
                    wet[i] = new ClimateNode(
                        cx + random.nextInt(BiomeFinder.RSIZE), 
                        cz + random.nextInt(BiomeFinder.RSIZE), 
                        9, 
                        (BasinNode.getLogScaled(random.nextInt(5) - 15) / 30) * 1.5, 
                        random.nextInt(5));
                    break;
                case 1:
                    wet[i] = new ClimateNode(
                        cx + random.nextInt(BiomeFinder.RSIZE), 
                        cz + random.nextInt(BiomeFinder.RSIZE), 
                        0, 
                        (BasinNode.getLogScaled(random.nextInt(5) - 15) / 30) * 1.5, 
                        random.nextInt(5));
                    break;
                case 2:
                    wet[i] = new ClimateNode(
                        cx + random.nextInt(BiomeFinder.RSIZE), 
                        cz + random.nextInt(BiomeFinder.RSIZE), 
                        random.nextInt(10), 
                        (BasinNode.getLogScaled(random.nextInt(5) - 15) / 10) * 1.5, 
                        random.nextInt(5));
                    break;
            }
        }
    }
    
}
