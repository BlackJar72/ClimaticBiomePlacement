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
public class BasinNode {
    final int x, z, value;
    final double decay;
    private static final double[] LOGTABLE = makeLogTable();
    private static final double[] logtable = makeLogTable();
    
    
    public BasinNode(int x, int y, int value, double decay) {
        this.x = x;
        this.z = y;
        this.value = value;
        this.decay = decay;
    }
    
    
    public double getRelativeWeakness(int range) {
        double effect = ((range) * decay);
        return range * range;
    }
    
    
    public double getWeaknessAt(double atx, double aty) {
        double xdisplace = ((((double)x) - atx) * decay);
        double ydisplace = ((((double)z) - aty) * decay);
        return Math.min((xdisplace * xdisplace) + (ydisplace * ydisplace), 1.0);
    }
    
    
    public static int summateEffect(BasinNode[] n, ChunkTile t) {
        double effect = 0.0;
        double sum    = 0.0;
        double power, weakness;
        for(int i = 0; i < n.length; i++) {
            if((n[i].x == t.tx) && (n[i].z == t.tz)) {
                return (int)n[i].value;
            }
            weakness = n[i].getWeaknessAt(t.tx, t.tz);
            power = 1.0 / (weakness * weakness);
            sum += power;
            effect += Math.max(((double)n[i].value) * power, 0);
        }
        return (int)(effect / sum);
    }
    
    
    public static double summateEffect(BasinNode[] n, ChunkTile t, double scale) {
        double effect = 0.0;
        double sum    = 0.0;
        double power, weakness;
        for(int i = 0; i < n.length; i++) {
            double x = ((double)t.tx) * scale;
            double z = ((double)t.tz) * scale;
            if((n[i].x == (int)x) && (n[i].z == (int)z)) {
                return (int)n[i].value;
            }
            weakness = n[i].getWeaknessAt(x, z);
            power = 1.0 / (weakness * weakness);
            sum += power;
            effect += Math.max(((double)n[i].value) * power, 0);
        }
        return (effect / sum);
    }
    
    
    private static double[] makeLogTable() {
        double[] out = new double[31];
            for(int i = 0; i < out.length; i++) {
                out[i] = Math.pow(10, ((double)(i - 15)) / 10);
            }
        return out;
    }
    
    
    public static double getLogScaled(int in) {
        return logtable[in + 15];
    }
    
    
    public String toString() {
        return "    [x=" + x + ", z=" + z + ", val=" + value + ", decay=" + decay + "] ";
    }
    
    
    public String briefString() {
        return "    [x=" + x + ", z=" + z + "] ";
    }
    
}
