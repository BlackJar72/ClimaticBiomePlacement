package jaredbgreat.climaticbiome.util;


/**
 * A simple 2-D gradient noise generation class.  Technically, this is misnamed 
 * as it does not have all of Ken Perlin's specific innovations and deviates 
 * from true perlin noise in various way -- but its pretty close, being based 
 * on the same underlying principles.
 * 
 * Also not, this implementation sacrifices optimization for clarity; usually 
 * a good thing in code, for the likely use cases a more optimized 
 * implementation that this would likely be desired in production code.
 * 
 * @author jared
 */
public class VolumnNoiseMap {
    int xOff, yOff, zOff, sizex, sizey, sizez, interval, currentInterval;
    float[][][] field;
    float scale, divisor;
    
    private static final class PDat {
        public final float val;
        public final float weight;
        public static final PDat Z0 = new PDat(0.0f, 0.0f);
        public PDat(float v, float w) {
            val = v;
            weight = w;
        }
    }
    
    public VolumnNoiseMap(int sizex, int sizey, int interval, float scale) {
        this.sizex = sizex; 
        this.sizez = sizey; 
        this.interval = interval; 
        this.scale = scale;
    }

    
    /**
     * Generate a noise map for map coordinates xOff,zOff.
     * 
     * @param rand
     * @param x
     * @param z
     * @return 
     */
    public float[][][] process(SpatialHash rand, int x, int z)  {
        xOff = x;
        zOff = z; 
        field = new float[sizex][sizey][sizez];
        currentInterval = interval;
        divisor = 1.0f;
        while(currentInterval > 2) {
            processOne(rand);            
            divisor *=2;
            currentInterval /= 2;
        }
        return field;
    }
    
    

    
    /**
     * Generate a noise map for map coordinates 0,0.
     * 
     * @param rand
     * @return 
     */
    @Deprecated
    public float[][][] process(SpatialHash rand)  {
        int x = zOff = 0;
        field = new float[sizex][sizey][sizez];
        currentInterval = interval;
        divisor = 1.0f;
        while(currentInterval > 2) {
            processOne(rand);            
            divisor *=2;
            currentInterval /= 2;
        }
        return field;
    }
    
    
    private void processOne(SpatialHash rand) {
        int nodesX = sizex / currentInterval + 1;
        int nodesY = sizey / currentInterval + 1;
        int nodesZ = sizez / currentInterval + 1;
        Vec3D[][][] nodes = new Vec3D[nodesX][nodesY][nodesZ];
        for(int i = 0; i < nodesX; i++)
            for(int j = 0; j < nodesY; j++)
                for(int k = 0; k < nodesZ; k++) {
	                nodes[i][j][k] = new Vec3D(rand, i + xOff / currentInterval, 
	                                              j + yOff / currentInterval, 
	                                              k + zOff / currentInterval, (int)divisor);
        }
        for(int i = 0; i < sizex; i++)
            for(int j = 0; j < sizey; j++)
                for(int k = 0; k < sizez; k++) {
                field[i][j][k] += processPoint(nodes, i, j, k) * scale;// / divisor;
        }
    }
    
    
    public float processPoint(Vec3D[][][] nodes, int x, int y, int z) {
        float out = 0.0f;
        
        float ci = (float)currentInterval;        
        float dx = fullFade(x % currentInterval);
        float dy = fullFade(y % currentInterval);
        float dz = fullFade(z % currentInterval);
        int    px = (int)(x / currentInterval);
        int    py = (int)(y / currentInterval);
        int    pz = (int)(z / currentInterval);        
        
        out += calcLoc(nodes[px][py][pz], 
                    new Vec3D(dx, dy, dz), ci);
        out += calcLoc(nodes[px + 1][py][pz], 
                    new Vec3D((ci - dx), dy, dz), ci);
        out += calcLoc(nodes[px + 1][py + 1][pz], 
                    new Vec3D((ci - dx), (ci - dy), dz), ci);
        out += calcLoc(nodes[px][py + 1][pz], 
                    new Vec3D(dx, (ci - dy), dz), ci);             
        
        out += calcLoc(nodes[px][py][pz + 1], 
                    new Vec3D(dx, dy, (ci - dz)), ci);
        out += calcLoc(nodes[px + 1][py][pz + 1], 
                    new Vec3D((ci - dx), dy, (ci - dz)), ci);
        out += calcLoc(nodes[px + 1][py + 1][pz + 1], 
                    new Vec3D((ci - dx), (ci - dy), (ci - dz)), ci);
        out += calcLoc(nodes[px][py + 1][pz + 1], 
                    new Vec3D(dx, (ci - dy), (ci - dz)), ci);        
        
        out /= interval;
        out /= 2.0;
        
        if((out >= 0.99) || (out <= -0.99)) {
            System.out.println(out);
            out = 0.0f;
        }
        return out;
    }
    
    
    private float calcLoc(Vec3D from, Vec3D at, float ci) {
        double dx = at.x / ci;
        double dy = at.y / ci;
        double dz = at.z / ci;
        double l = (1 - ((dx * dx) + (dy * dy) + (dz * dz)));
        if(l > 0) {
            return (float) (Vec3D.dot(from, at) * l);            
        }        
        return 0.0f;
    }
    
    
    private float fade(float in) {
        return in * in * in * (in * (in * 6 - 15) + 10);  
    }
    
    
    private float fullFade(float in) {
        return fade(in / currentInterval) * currentInterval;
    }
    
    
}
