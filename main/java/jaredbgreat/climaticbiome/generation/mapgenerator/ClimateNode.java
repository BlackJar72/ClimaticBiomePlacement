/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.mapgenerator;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jared
 */
public class ClimateNode extends BasinNode {


    final double faintness;

    public ClimateNode(int x, int z, int value, double decay, int faintness) {
        super(x, z, value, decay);
        this.faintness = faintness * decay;
    }
    
    
    public double getRelativeWeakness(int range) {
        double effect = ((range + faintness) * decay);
        return range * range;
    }
    
    
    public double getWeaknessAt(double atx, double aty) {
        double xdisplace = ((double)(x - atx) * decay);
        double ydisplace = ((double)(z - aty) * decay);
        return Math.min((xdisplace * xdisplace) + (ydisplace * ydisplace), 1.0)
                + faintness;
    }
    
    
    public static double summateEffect(ClimateNode[] n, ChunkTile t, double noise, double scale) {
        double effect = 0.0;
        double sum    = 0.0;
        double power, weakness;
        for(int i = 0; i < n.length; i++) {
            double x = t.tx * scale;
            double z = t.tz * scale;
            if((n[i].x == (int)x) && (n[i].z == (int)z) && (n[i].faintness == 0)) {
                return (int)n[i].value;
            }
            weakness = n[i].getWeaknessAt(x, z);
            power = 1.0 / weakness;
            sum += power;
            effect += ((double)n[i].value) * power;
        }
//        double result = (effect / sum) + noise;
//        if(result > 25.0) {
//        	result = 50.0 - result;
//        }
//        return Math.max(result, 0);
        return Math.max((effect / sum) + noise, 0);
    }
    
    
    public String toString() {
        return "    [x=" + x + ", z=" + z + ", val=" + value + ", decay=" + decay + ", faint=" + "] ";
    }
    
    
    
    //**************************************************************************************************//
    //                                Group Processing Below                                            //
    //**************************************************************************************************//
    
    
    public static class MutableClimateNode {
        public final int x, z;
        public double value;
        public double decay;
        public double faintness;
        public MutableClimateNode(ClimateNode original) {
        	this.x = original.x;
        	this.z = original.z;
        	this.value = original.value;
        	this.decay = original.decay;
        	this.faintness = original.faintness;
        }
        public ClimateNode getClimateNode() {
        	return new ClimateNode(x, z, (int)Math.round(value), decay, (int)faintness);
        }
    }
    
    
    public static List<ClimateNode> normalizeListSimple(List<ClimateNode> input, double max) {
    	ArrayList<MutableClimateNode> tmpList = new ArrayList<>();
    	for(ClimateNode node : input) tmpList.add(new MutableClimateNode(node));
    	double mean = 0, variance = 0, std = 0;
    	int n = 0;
    	for(BasinNode node : input) {
    		mean += node.value;
    		n++;
    	}
    	mean /= (double)n;
    	for(BasinNode node : input) {
    		variance += ((double)node.value - mean) * ((double)node.decay - mean);
    	}
    	variance /= (double)n;
    	std = Math.sqrt(variance); // get standard deviation
    	double halfmax = max / 2;
    	for(MutableClimateNode node : tmpList) {
    		node.value = (mean - node.decay) / std; // get Z score
    		node.value = Math.max(Math.min((node.value + 1.0) * halfmax, max), 0); // Convert to scale    		
    	}
    	input.clear();
    	for(MutableClimateNode node : tmpList) {
    		input.add(node.getClimateNode());
    	}
    	return input;
    }
    
    
    public static List<ClimateNode> normalizeListWeighted(List<ClimateNode> input, double max) {
    	ArrayList<MutableClimateNode> tmpList = new ArrayList<>();
    	for(ClimateNode node : input) tmpList.add(new MutableClimateNode(node));
    	double mean = 0, variance = 0, std = 0;
    	double n = 0;
    	for(MutableClimateNode node : tmpList) {
    		mean += node.value / (node.decay * node.faintness);
    		n += 1 / (node.decay * node.faintness);
    	}
    	mean /= n;
    	for(ClimateNode node : input) {
    		variance += (((double)node.value - mean) * ((double)node.decay - mean) / (node.decay * node.faintness));
    	}
    	variance /= n;
    	std = Math.sqrt(variance); // get standard deviation
    	double halfmax = max / 2;
    	for(MutableClimateNode node : tmpList) {
    		node.value = (mean - node.decay) / std; // get Z score
    		node.value = Math.max(Math.min((node.value + 1.0) * halfmax, max), 0); // Convert to scale    		
    	}
    	input.clear();
    	for(MutableClimateNode node : tmpList) {
    		input.add(node.getClimateNode());
    	}
    	return input;
    }
    
}
