package jaredbgreat.climaticbiome.util;

/**
 * A 3D gradient noise system that actually works.
 *
 * @author jared
 */
public class HeightNoiseMap3D {
    // These are not a "magic" number, but it does take more math to derive than I want to write here,
    // though only basic calculus and a few step of simple high school algebra.
    int xOff, yOff, zOff, size, interval, cutoff, currentInterval, layer, scalex = 4, scaley = 16, scalez = 4;
    float[][][] field;
    float divisor;


    public HeightNoiseMap3D(int size, int interval, int cutoff) {
        this.size = size;
        this.interval = interval;
        this.cutoff = cutoff;
        this.layer = 0;
    }

    public HeightNoiseMap3D(int size, int interval, int sx, int sy, int sz) {
        this.size = size;
        this.interval = interval;
        this.cutoff = 2;
        this.layer = 0;
        SetCoordScale(sx, sy, sz);
    }


    public void SetCoordScale(int x, int y, int z) {
        scalex = x;
        scaley = y;
        scalez = z;
    }



    /**
     * Generate a noise map for map coordinates xOff,zOff.
     * 
     * @param rand
     * @param x
     * @param z
     * @return 
     */
    public HeightNoiseMap3D process(SpatialHash rand, int x, int y)  {
        xOff = x * size;
        yOff = y * size;
        zOff = 0;
        layer = layer * 2;
        field = new float[size + 1][size + 1][size + 1];
        currentInterval = interval;
        divisor = 1.0f;
        while(currentInterval > cutoff) {
            processLayer(rand);
            divisor *=2;
            currentInterval /= 2;
        }
        for(int i = 0; i < size + 1; i++)
            for(int j = 0; j < size + 1; j++)
                for(int k = 0; k < size + 1; k++) {
                field[i][j][k] = field[i][j][k];
            }
        return this;
    }

    private void processLayer(SpatialHash rand) {
        int nodesX = Math.max(size / currentInterval + 2, 3);
        int nodesY = Math.max(size / currentInterval + 2, 3);
        int nodesZ = Math.max(size / currentInterval + 2, 3);
        Vec3D[][][] nodes = new Vec3D[nodesX][nodesY][nodesZ];
        for(int i = 0; i < nodesX; i++)
            for(int j = 0; j < nodesY; j++)
                for(int k = 0; k < nodesZ; k++) {
                    nodes[i][j][k] = new Vec3D(rand, i + xOff / currentInterval,
                            j + yOff / currentInterval, k + zOff / currentInterval, layer);
                }
        for(int i = 0; i < size + 1; i++)
            for(int j = 0; j < size + 1; j++)
                for(int k = 0; k < size + 1; k++) {
                    field[i][j][k] += processPoint(nodes, i, j, k);
                }
    }


    public float processPoint(Vec3D[][][]nodes, int x, int y, int z) {
        float output = 0.0f;
        
        float ci = (float)currentInterval;

        float dx = FullFade(x % currentInterval);
        float dy = FullFade(y % currentInterval);
        float dz = FullFade(z % currentInterval);

        int    px = x / currentInterval;
        int    py = y / currentInterval;
        int    pz = z / currentInterval;

        output += calcLoc(nodes[px][py][pz],
                new Vec3D(dx, dy, dz), ci);
        output += calcLoc(nodes[px + 1][py][pz],
                new Vec3D((ci - dx), dy, dz), ci);
        output += calcLoc(nodes[px + 1][py + 1][pz],
                new Vec3D((ci - dx), (ci - dy), dz), ci);
        output += calcLoc(nodes[px][py + 1][pz],
                new Vec3D(dx, (ci - dy), dz), ci);

        output += calcLoc(nodes[px][py][pz + 1],
                new Vec3D(dx, dy, (ci - dz)), ci);
        output += calcLoc(nodes[px + 1][py][pz + 1],
                new Vec3D((ci - dx), dy, (ci - dz)), ci);
        output += calcLoc(nodes[px + 1][py + 1][pz + 1],
                new Vec3D((ci - dx), (ci - dy), (ci - dz)), ci);
        output += calcLoc(nodes[px][py + 1][pz + 1],
                new Vec3D(dx, (ci - dy), (ci - dz)), ci);

        output /= interval;
        output /= 2.0f;
        return output;
    }

    
    private float calcLoc(Vec3D from, Vec3D at, float ci) {
        double dx = at.x / ci;
        double dy = at.y / ci;
        double dz = at.z / ci;
        double l = (1 - ((dx * dx) + (dy * dy) + (dz * dz)));
        if(l > 0) {
            return (float)(Vec3D.dot(from, at) * l);
        }        
        return 0.0f;
    }


    private float fade(float val) {
        return val * val * val * (val * (val * 6 - 15) + 10);
    }


    private float FullFade(float val) {
        return fade(val / currentInterval) * currentInterval;
    }


    private float FullFade(float val, float interval) {
        return fade(val / interval) * interval;
    }


    public float getValue(int x, int y, int z) {
        int px = x / scalex;
        int py = y / scaley;
        int pz = z / scalez;

        float dx = FullFade(x % scalex, scalex);
        float dy = FullFade(y % scaley, scaley);
        float dz = FullFade(z % scalez, scalez);

        float s1 = (field[px][py][pz] * (1 - dz)) + (field[px][py][pz + 1] * dz);
        float s2 = (field[px + 1][py][pz] * (1 - dz)) + (field[px + 1][py][pz + 1] * dz);
        float s3 = (field[px + 1][py + 1][pz] * (1 - dz)) + (field[px + 1][py + 1][pz + 1] * dz);
        float s4 = (field[px][py + 1][pz] * (1 - dz)) + (field[px][py + 1][pz + 1] * dz);

        float f1 = (s1 * (1 - dx)) + (s2 * dx);
        float f2 = (s4 * (1 - dx)) + (s3 * dx);

        return (f1 * (1 - dy)) + (f2 * dy);
    }


}

