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
public class HeightNoiseMap {
    int xOff, zOff, sizex, sizez, interval, cutoff, currentInterval;
    float[][] field;
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
    
    public HeightNoiseMap(int sizex, int sizey, int interval, int cutoff, float scale) {
        this.sizex = sizex; 
        this.sizez = sizey; 
        this.interval = interval; 
        this.cutoff = cutoff;
        this.scale = scale;
    }
    
    public HeightNoiseMap(int sizex, int sizey, int interval, float scale) {
        this.sizex = sizex; 
        this.sizez = sizey; 
        this.interval = interval; 
        this.cutoff = 2;
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
    public float[][] process(SpatialHash rand, int x, int z)  {
        xOff = x;
        zOff = z; 
        field = new float[sizex][sizez];
        currentInterval = interval;
        divisor = 1.0f;
        while(currentInterval > cutoff) {
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
    public float[][] process(SpatialHash rand)  {
        int x = zOff = 0;
        field = new float[sizex][sizez];
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
        int nodesY = sizez / currentInterval + 1;
        Vec2D[][] nodes = new Vec2D[nodesX][nodesY];
        for(int i = 0; i < nodesX; i++)
            for(int j = 0; j < nodesY; j++) {
                nodes[i][j] = new Vec2D(rand, i + xOff / currentInterval, 
                        j + zOff / currentInterval, (int)divisor);
        }
        for(int i = 0; i < sizex; i++)
            for(int j = 0; j < sizez; j++) {
                field[i][j] += processPoint(nodes, i, j) * scale;// / divisor;
        }
    }
    
    
    public float processPoint(Vec2D[][] nodes, int x, int y) {
        float out = 0.0f;
        
        float ci = (float)currentInterval;        
        float dx = fullFade(x % currentInterval);
        float dy = fullFade(y % currentInterval);
        int    px = (int)(x / currentInterval);
        int    py = (int)(y / currentInterval);        
        
        out += calcLoc(nodes[px][py], 
                    new Vec2D(dx, dy), ci);
        out += calcLoc(nodes[px + 1][py], 
                    new Vec2D((ci - dx), dy), ci);
        out += calcLoc(nodes[px + 1][py + 1], 
                    new Vec2D((ci - dx), (ci - dy)), ci);
        out += calcLoc(nodes[px][py + 1], 
                    new Vec2D(dx, (ci - dy)), ci);        
        
        out /= interval;
        out /= 2.0;
        
        if((out >= 0.99) || (out <= -0.99)) {
            System.out.println(out);
            out = 0.0f;
        }
        return out;
    }
    
    
    private float calcLoc(Vec2D from, Vec2D at, float ci) {
        double dx = at.x / ci;
        double dy = at.y / ci;
        double l = (1 - ((dx * dx) + (dy * dy)));
        if(l > 0) {
            return (float) (Vec2D.dot(from, at) * l);            
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
