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
    int xOff, yOff, zOff, 
    	sizex, sizey, sizez, 
    	fsizex, fsizey, fsizez,
    	scalex, scaley, scalez,
    	interval, cutoff, 
    	currentInterval1, currentInterval2;
    float[][][] field;
    float scale, divisor;
    
    
    public VolumnNoiseMap(int size, 
			  	          int scalex, int scaley, int scalez,
    					  int interval, float scale) {
        sizex = sizey = sizez = size; 
        fsizex = fsizey = fsizez = size + 1;
        this.scalex = scalex;
        this.scaley = scaley;
        this.scalez = scalez;
        this.interval = interval;
        this.cutoff = 4;
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
    public VolumnNoiseMap process(SpatialHash rand, int x, int z)  {
        xOff = x;
        zOff = z; 
        field = new float[fsizex][fsizey][fsizez];
        currentInterval1 = interval;
        divisor = 1.0f;
        while(currentInterval1 > cutoff) {
            currentInterval2 = currentInterval1 + 1;
            //Debug.bigSysout("Interval1 = " + currentInterval1 + System.lineSeparator() + "Interval2 = " + currentInterval2);
            processOne(rand);            
            divisor *=2;
            currentInterval1 /= 2;
            
        }
        return this;
    }
    
    
    private void processOne(SpatialHash rand) {
        int nodesX = (fsizex / currentInterval1) + 2;
        int nodesY = (fsizey / currentInterval1) + 2;
        int nodesZ = (fsizez / currentInterval1) + 2;
        Vec3D[][][] nodes = new Vec3D[nodesX][nodesY][nodesZ];
        for(int i = 0; i < nodesX; i++)
            for(int j = 0; j < nodesY; j++)
                for(int k = 0; k < nodesZ; k++) {
	                nodes[i][j][k] = new Vec3D(rand, 
	                		                   i + xOff / currentInterval1, 
	                                           j + yOff / currentInterval1, 
	                                           k + zOff / currentInterval1, 
	                                           (int)divisor);
        }
        for(int i = 0; i < fsizex; i++)
            for(int j = 0; j < fsizey; j++)
                for(int k = 0; k < fsizez; k++) {
                field[i][j][k] += processPoint(nodes, i, j, k) * scale;
        }
    }
    
    
    public float processPoint(Vec3D[][][] nodes, int x, int y, int z) {
        float out = 0.0f;
        
        float ci = (float)currentInterval2;        
        float dx = fullFade(x % currentInterval1);
        float dy = fullFade(y % currentInterval1);
        float dz = fullFade(z % currentInterval1);
        int    px = (int)(x / currentInterval1);
        int    py = (int)(y / currentInterval1);
        int    pz = (int)(z / currentInterval1);
        
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
        
        //if((out >= 0.99) || (out <= -0.99)) {
        //    System.out.println(out);
        //    out = 0.0f;
        //}
        
        return out;
    }
    
    
    public float get(int x, int y, int z) {
        float out = 0.0f;
        
        //StringBuilder b = new StringBuilder();
        //b.append("x =" + x + " y = " + y + " z = " + z + System.lineSeparator());
        
        float cix = (float)scalex;
        float ciy = (float)scaley;
        float ciz = (float)scalez;
        int    px = (int)(x / scalex);
        int    py = (int)(y / scaley);
        int    pz = (int)(z / scalez);
        //b.append("px =" + px + " py = " + py + " pz = " + pz + System.lineSeparator());
        float dx = (x - (px * scalex)) / cix;//fullFade(x % scalex, cix);
        float dy = (y - (py * scaley)) / ciy;//fullFade(y % scaley, ciy);
        float dz = (z - (pz * scalez)) / ciz;//fullFade(z % scalez, ciz);
        //b.append("dx =" + dx + " dy = " + dy + " dz = " + dz + System.lineSeparator());
        
        /*out += calcOut(field[px][py][pz], 
                    dx / cix, dy / ciy, dz / ciz);
        out += calcOut(field[px + 1][py][pz], 
                    (cix - dx) / cix, dy / ciy, dz / ciz);
        out += calcOut(field[px + 1][py + 1][pz], 
                    (cix - dx) /  cix, (ciy - dy) / ciy, dz / ciz);
        out += calcOut(field[px][py + 1][pz], 
                    dx / cix, (ciy - dy) / ciy, dz / ciz);
        
        out += calcOut(field[px][py][pz + 1], 
                    dx / cix, dy / ciy, (ciz - dz) / ciz);
        out += calcOut(field[px + 1][py][pz +1], 
                    (cix - dx) / cix, dy / ciy, (ciz - dz) / ciz);
        out += calcOut(field[px + 1][py + 1][pz + 1], 
                    (cix - dx) /  cix, (ciy - dy) / ciy, (ciz - dz) / ciz);
        out += calcOut(field[px][py + 1][pz + 1], 
                    dx / cix, (ciy - dy) / ciy, (ciz - dz) / ciz);        
        
        out /= 2.0;*/
        
        float a1 = lerp(dx, field[px][py][pz], field[px+1][py][pz]);
        float a2 = lerp(dx, field[px][py+1][pz], field[px+1][py+1][pz]);
        float a3 = lerp(dx, field[px][py+1][pz+1], field[px+1][py+1][pz+1]);
        float a4 = lerp(dx, field[px][py][pz+1], field[px+1][py][pz+1]);
        
        float b1 = lerp(dz, a1, a4);
        float b2 = lerp(dz, a2, a3);
        
        out = lerp(dy, b1, b2) / 2.0f;
        
        //if((out >= 0.99) || (out <= -0.99)) {
        //    System.out.println(out);
        //    out = 0.0f;
        //}        

        //b.append("out = " + out);
        //Debug.bigSysout(b.toString());
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
    
    
    private float calcOut(float from, float dx, float dy, float dz) {
        float l = (1 - ((dx * dx) + (dy * dy) + (dz * dz)));
        if(l > 0) {
            return from * l;            
        }        
        return 0.0f;
    }
    
    
    private float fade(float in) {
        return in * in * in * (in * (in * 6 - 15) + 10);  
    }
    
    
    private float fullFade(float in) {
        return fade(in / currentInterval1) * currentInterval1;
    }
    
    
    private float fullFade(float in, float scale) {
        return fade(in / scale) * scale;
    }
    
    
    static float lerp(float t, float a, float b) {
    	return a + t * (b - a); 
    }
    
    
}
