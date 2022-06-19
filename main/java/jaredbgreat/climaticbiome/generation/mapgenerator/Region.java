/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.mapgenerator;

import static jaredbgreat.climaticbiome.generation.mapgenerator.MapMaker.RADIUS;
import static jaredbgreat.climaticbiome.generation.mapgenerator.MapMaker.RSIZE;
import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;
import jaredbgreat.climaticbiome.generation.cache.Coords;
import jaredbgreat.climaticbiome.util.SpatialHash;

/**
 *
 * @author jared
 */
public final class Region extends AbstractCachable {
    BasinNode[] basins;
    ClimateNode[] temp;
    ClimateNode[] wet;
    int cx, cz;
    
    static int n = 0;
    
     
    public Region(int x, int z, SpatialHash random, ClimaticWorldSettings settings) {
        super(x, z);
        
        cx = (x * RSIZE) - RADIUS;
        cz = (z * RSIZE) - RADIUS;
        switch(settings.mode) {
	        case 1:
	        	makeBasins(5, 10, 15, random.getRandomAt(x, z, 0));
	        	break;
	        case 2:
	        case 3:
	        	int num = random.absModulus(random.intFor(x, z, -1), 5) + 5;;
	        	makeBasins(0, num, 15, random.getRandomAt(x, z, 0));
	        	break;
	        default: 
	        	makeBasins(5, 10, 15, random.getRandomAt(x, z, 0));
	        	break;
        }
        makeTempBasins(10, random.getRandomAt(x, z, 1), settings);
        makeRainBasins(12, random.getRandomAt(x, z, 2));
        n++;
    }
    
    
    public void finalize() throws Throwable {
    	n--;
    	super.finalize();
    }
    
    
    
    
    public Region init(int x, int z, SpatialHash random, ClimaticWorldSettings settings) {
        cx = (x * RSIZE) - RADIUS;
        cz = (z * RSIZE) - RADIUS;
        makeBasins(5, 10, 15, random.getRandomAt(x, z, 0));
        makeTempBasins(10, random.getRandomAt(x, z, 1), settings);
        makeRainBasins(12, random.getRandomAt(x, z, 2));
        return this;
    }
    
    
    private BasinNode makeBasin(int value, double decay, SpatialHash.RandomAt random) {
        int x = cx + random.nextInt(RSIZE);
        int z = cz + random.nextInt(RSIZE);
        return new BasinNode(x, z, value, 
                decay * 1.5);
    }
    
    
    private BasinNode makeCentralBasin1(int value, double decay, SpatialHash.RandomAt random) {
        int x = cx + random.nextInt(RSIZE / 2) + (RSIZE / 4);
        int z = cz + random.nextInt(RSIZE / 2) + (RSIZE / 4);
        return new BasinNode(x, z, value, decay * 1.5);
    }
    
    
    private BasinNode makeCentralBasin2(int value, double decay, SpatialHash.RandomAt random) {
        int x = cx + random.nextInt((RSIZE * 8) / 10) + (RSIZE / 10);
        int y = cz + random.nextInt((RSIZE * 8) / 10) + (RSIZE / 10);
        return new BasinNode(x, y, value, decay * 1.5);
    }
    
    
    public void makeBasins(int main, int pos, int neg, SpatialHash.RandomAt random) {
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
    
    
    private void makePoles(ClimateNode[] nodes, SpatialHash.RandomAt random, 
    		ClimaticWorldSettings settings) {
    	int movex = 0, movez = 0;
    	if(settings.mode == 4) {
    		movex = random.nextInt(512) - 256;
    		movez = random.nextInt(512) - 256;
    	}
        int dist = (RSIZE / 6) 
                + random.nextInt(RSIZE / 4);
        double angle = random.nextDouble() * 2 * Math.PI;
        int x = cx + RADIUS + (int)(dist * Math.cos(angle)) + movex;
        int y = cz + RADIUS + (int)(dist * Math.sin(angle)) + movez;
        nodes[0] = new ClimateNode(x, y, 0, 
                (BasinNode.getLogScaled(-14) / 40) * 1.5, 0);
        dist = (RSIZE / 6) + random.nextInt(RSIZE / 4);
        angle = angle + (random.nextDouble() 
                * (Math.PI / 2)) + ((Math.PI * 3) / 4);
        x = cx + (RADIUS) + (int)(dist * Math.cos(angle));
        y = cz + (RADIUS) + (int)(dist * Math.sin(angle));
        nodes[1] = new ClimateNode(x, y, 25, 
                (BasinNode.getLogScaled(-15) / 40) * 1.5, 0);        
    }
    
    
    private void makeColdPoles(ClimateNode[] nodes, SpatialHash.RandomAt random, 
    		ClimaticWorldSettings settings) {
    	int movex = 0, movez = 0;
    	if(settings.mode == 4) {
    		movex = random.nextInt(512) - 256;
    		movez = random.nextInt(512) - 256;
    	}
        int dist = (RSIZE / 6) 
                + random.nextInt(RSIZE / 4);
        double angle = random.nextDouble() * 2 * Math.PI;
        int x = cx + RADIUS + (int)(dist * Math.cos(angle)) + movex;
        int y = cz + RADIUS + (int)(dist * Math.sin(angle)) + movez;
        nodes[0] = new ClimateNode(x, y, 0, 
                (BasinNode.getLogScaled(-14) / 40) * 1.5, 0);
        dist = (RSIZE / 6) + random.nextInt(RSIZE / 4);
        angle = angle + (random.nextDouble() 
                * (Math.PI / 2)) + ((Math.PI * 3) / 4);
        x = cx + (RADIUS) + (int)(dist * Math.cos(angle));
        y = cz + (RADIUS) + (int)(dist * Math.sin(angle));
        nodes[1] = new ClimateNode(x, y, 50, 
                (BasinNode.getLogScaled(-15) / 40) * 1.5, 0);        
    }
    
    
    public void makeTempBasins(int n, SpatialHash.RandomAt random, ClimaticWorldSettings settings) {
        /*if(!ConfigHandler.allPolesCold)*/ {
	        temp = new ClimateNode[n + 2];
	        makePoles(temp, random, settings);
	        for(int i = 2; i < temp.length; i++) {
	            temp[i] = new ClimateNode(
	                cx + random.nextInt(RSIZE), 
	                cz + random.nextInt(RSIZE), 
	                random.nextInt(25), 
	                (BasinNode.getLogScaled(random.nextInt(5) - 12) / 30) * 1.5, 
	                random.nextInt(3) + 1);
	        }
        } /*else {
	        temp = new ClimateNode[2];
	        makeColdPoles(temp, random, settings);
        }*/
    }
    
    
    public void makeRainBasins(int n, SpatialHash.RandomAt random) {
        wet = new ClimateNode[n];
        for(int i = 0; i < wet.length; i++) {
            int cycle = i % 3;
            switch(cycle) {
                case 0:
                    wet[i] = new ClimateNode(
                        cx + random.nextInt(RSIZE), 
                        cz + random.nextInt(RSIZE), 
                        9, 
                        (BasinNode.getLogScaled(random.nextInt(5) - 15) / 30) 
                                * 1.5, 
                        0);
                    break;
                case 1:
                    wet[i] = new ClimateNode(
                        cx + random.nextInt(RSIZE), 
                        cz + random.nextInt(RSIZE), 
                        0, 
                        (BasinNode.getLogScaled(random.nextInt(5) - 15) / 30) 
                                * 1.5, 
                        0);
                    break;
                case 2:
                    wet[i] = new ClimateNode(
                        cx + random.nextInt(RSIZE), 
                        cz + random.nextInt(RSIZE), 
                        random.nextInt(10), 
                        (BasinNode.getLogScaled(random.nextInt(5) - 15) / 10) 
                                * 1.5, 
                        random.nextInt(5));
                    break;
            }
        }
    }
    
    
    public BasinNode[] getBasins(int num, boolean beginning) {
        if(num > basins.length) {
            num = basins.length;
        }
        BasinNode[] out = new BasinNode[num];
        if(beginning) {
            System.arraycopy(basins, 0, out, 0, num);
        } else {
            System.arraycopy(basins, basins.length - num, out, 0, num);
        }
        return out;
    }
    
    
    public String toCoords() {
    	return "[" + (cx / RSIZE) + ", " + (cz / RSIZE) + "]";
    }
    
    
    public String toDataString() {
        StringBuilder builder = new StringBuilder();
        Coords coords = this.getCoords();
        builder.append("\n*************\n");
        builder.append("Region:" + coords.getX() + ", " + coords.getZ() + "\n");
        builder.append("cx = " + cx + "; cz = " + cz + "\n");
        builder.append("Land Sequences: \n");
        for(BasinNode basin : basins) {
            builder.append(basin.briefString());
            builder.append('\n');
        }
        builder.append("Temp Sequences: \n");
        for(ClimateNode basin : temp) {
            builder.append(basin.briefString());
            builder.append('\n');
        }
        builder.append("Wetness Sequences: \n");
        for(ClimateNode basin : wet) {
            builder.append(basin.briefString());
            builder.append('\n');
        }
        builder.append("\n*************\n");
        return builder.toString();
    }
    
    
}
