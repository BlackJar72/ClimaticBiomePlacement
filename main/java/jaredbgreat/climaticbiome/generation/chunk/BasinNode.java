/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaredbgreat.climaticbiome.generation.chunk;

/**
 *
 * @author jared
 */
public class BasinNode {
    final int x, z;
    final double strength, value;
    
    
    public BasinNode(int x, int y, double value, double decay) {
        this.x = x;
        this.z = y;
        this.value = value;
        this.strength = decay;
    }
    
    
    public double getWeaknessAt(double atx, double aty) {
        double xdisplace = (((double)x + 1) - atx);
        double ydisplace = (((double)z + 1) - aty);
        return Math.max((xdisplace * xdisplace) + (ydisplace * ydisplace), 1.0);
    }
    
    
    public static double summateEffect(BasinNode[] n, int x, int z) {
        double effect = 0.0;
        double sum    = 0.0;
        double power, weakness;
        for(int i = 0; i < n.length; i++) {
            weakness = n[i].getWeaknessAt(x, z);
            //System.out.println((int)weakness);
            power = n[i].strength / (weakness * weakness);
            sum += power;
            effect += (n[i].value) * power;
        }
        return (effect / sum);
    }
    
    
    public String toString() {
        return "    [x=" + x + ", z=" + z + ", val=" + value + ", strength=" + strength + "] ";
    }
    
    
    public String briefString() {
        return "    [x=" + x + ", z=" + z + "] ";
    }
    
}
