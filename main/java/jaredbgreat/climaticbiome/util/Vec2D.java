package jaredbgreat.climaticbiome.util;

import java.util.Random;


/**
 * A very simple, scaled down 2D vector class, used by HeightNoise to 
 * represent gradients.
 * 
 * @author Jared Blackburn
 */
public class Vec2D {
    private static final double P2 = Math.PI * 2.0;
    private static final double DIVISOR = 2.0 / (double)0x7fffffff;
    double x, y;
    
    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vec2D(Random rand) {
        x = rand.nextDouble() * 2.0 - 1.0;
        y = rand.nextDouble() * 2.0 - 1.0;
    }
    
    public Vec2D(SpatialHash random, int px, int py, int pz) {
        x = random.doubleFor(px, py, pz) * 2.0 - 1.0;
        y = random.doubleFor(px, py, pz + 1) * 2.0 - 1.0;
        // This would be a bit more efficient, but would break existing worlds with ugly cliffs
    	//long tmp = random.longFor(px, py, pz);
        //x = ((double)(tmp & 0x7fffffff)) * DIVISOR - 1.0;
        //y = ((double)((tmp >> 32) & 0x7fffffff)) * DIVISOR - 1.0;
    }
    
    public static double dot(Vec2D a, Vec2D b) {
        return (a.x * b.x) + (a.y * b.y);
    }
    
}
