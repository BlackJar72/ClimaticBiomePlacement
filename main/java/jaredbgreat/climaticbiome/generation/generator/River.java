package jaredbgreat.climaticbiome.generation.generator;

import java.util.Random;

/**
 * @author Jared Blackburn
 */
public class River {
    final int MAX;
    MapMaker map;
    BasinNode begin, end;
    double dx, dy;
    double angle, da;
    double cx, cy, rx, ry;
    AS s;
    final ChangeQueue Q;
    private int oc;
    private ChunkTile last;
    private boolean wasRiver;
    
    private enum AS {
        P2 (2,   0),
        P1 (3,  -1),
        Z0 (0, 0),
        N1 (-3,  1),
        N2 (-2,  0);        
        public double a, b;
        private static final AS[] vals = values();
        AS(double a, double b) {
            this.a = a;
            this.b = b;
        }
        public static AS getRandom(Random r) {
            return vals[r.nextInt(vals.length)];
        }
        public AS shift(Random r) {
            int v = this.ordinal() + (r.nextInt(3)) - 1;
            if(v < 0) {
                v = 1;
            } else if(v >= vals.length) {
                v = vals.length - 1;
            }
            return vals[v];
        }
    }      
    
    private class ChangeQueue {
        private final ChunkTile[] data = new ChunkTile[16];
        private int head = 0, tail = 0;
        public ChunkTile push(ChunkTile in) {
            data[head] = in;
            head = (head + 1) % data.length;
            if(head == tail) {
                tail = (tail + 1) % data.length;
                return data[head];
            } else {
                return null;
            }
        }
        public ChunkTile pop() {
            if(head == tail) {
                return null;
            } else {
                ChunkTile out = data[tail];
                tail = (tail + 1) % data.length;
                return out;
            }
        }
        public boolean contains(ChunkTile t) {
        	for(int i = 0; (i < data.length) && (data[i] != null); i++) {
        		if(data[i] == t) return true;
        	}
        	return false;
        }
    }  
    
    
    public River(BasinNode high, BasinNode low, MapMaker mapIn, int x, int z) {
        last = null;
        MAX = MapMaker.RSIZE - 2;
        Q = new ChangeQueue();
        wasRiver = false;
        map = mapIn;
        begin = high;
        end   = low;
        oc = 0;
        da = angle = 0;
        cx = begin.x - x * MapMaker.RSIZE + MapMaker.RADIUS;
        cy = begin.z - z * MapMaker.RSIZE + MapMaker.RADIUS;
        //System.err.println(cx + ", " + cy + " -> " + (cx - map.getXoff()) + ", " + (cy - map.getZoff()) 
        //		+ "  (XOff = " + map.getXoff() + ", ZOff = " + map.getZoff() + ") ");
        double length = findLength(cx, cy, end.x, end.z);
        dx = (end.x - begin.x) / length;
        dy = (end.z - begin.z) / length;
        s = AS.Z0;
    }
    
    private double findLength(double x1, double y1, double x2, double y2) {
        double difX = x1 - x2;
        double difY = y1 - y2;
        return Math.sqrt((difX * difX) + (difY * difY));
    }
    
    public void build(Random r) {
        s = AS.getRandom(r);
        da = (r.nextDouble() * 9) - 4;
        double l, das, dac, f, p;
        do {
            // TODO: Optimize: Keep angle in Radians, don't convert 
            das = Math.sin(Math.toRadians(angle)); 
            l = Math.sqrt((das * das) + 1);
            f = 1 / l;
            p = das / l;
            incrementAngle(r);
            cx += (f * dx) + (p * dy);
            cy += (f * dy) + (p * dx);
            ChunkTile toChange = Q.push(map.getTile((int)cx, (int)cy));
            if(toChange != null) {
                makeRiver(toChange);
            }
        } while(!shouldEnd((int)cx, (int)cy));
        ChunkTile toChange;
        while((toChange = Q.pop()) != null) {
            makeRiver(toChange);
        }
    }
    
    private boolean shouldEnd(int x, int y) {
        ChunkTile t = map.getTile(x, y);
        // This will be true if the biome is water
        if(t.rlBiome == 0) {
            oc++;
        }
        if(t == last) {
            return false;
        }
        if(wasRiver) {
        	return true;
        }
        last = t;
        wasRiver = t.isRiver() && !Q.contains(t);
        return ((t.rlBiome == 0) && ((t.val < 3) || (oc > 8))) 
                || outOfBounds(x, y);
    }
    
    
    private boolean outOfBounds(int x, int z) {        
        return (x <= 1) || (z <= 1) 
                ||(x >= MAX) || (z >= MAX);
    }
    
    
    private void incrementAngle(Random r) {        
        angle += da;
        // Do I need to worry about fixing the angle over 
        // the rivers relatively short run?  Possibly not.
        /*if(angle > 360) {
            angle -= 360;
        } else if(angle < 0) {
            angle += 360;
        }*/
        da += (s.a * r.nextDouble()) + s.b;
        if(r.nextBoolean()) {
            if(r.nextBoolean()) {
                s = AS.getRandom(r);
            } else {
                s = s.shift(r);
            }
        }        
    }
    
    private void makeRiver(ChunkTile t) {
        t.beRiver();
        map.getTile(t.x + 1, t.z).beRiver();
        map.getTile(t.x + 1, t.z + 1).beRiver();
        map.getTile(t.x, t.z + 1).beRiver();
    }
    
}
