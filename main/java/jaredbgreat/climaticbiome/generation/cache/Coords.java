package jaredbgreat.climaticbiome.generation.cache;

import jaredbgreat.climaticbiome.util.SpatialHash;

public final class Coords {
	private static final SpatialHash nh = new SpatialHash(0xADD5C0DE);
	private final int x, z;
	
	
	public Coords(int x, int z) {
            this.x = x;
            this.z = z;
	}
	
	
	@Override
	public boolean equals(Object other) {
            if(other instanceof Coords) {
                    Coords o = (Coords)other;
                    return ((o.x == x) && (o.z == z));
            }
            return false;
	}
	
	
	public boolean equals(int x, int z) {
            return (this.x == x) && (this.z == z);
	}
	
	
	@Override
	public int hashCode() {
            return nh.intFor(x, z, 0);			
	}
        
        
    public static int hashCoords(int x, int z) {
    		return nh.intFor(x, z, 0);			
    }
        
        
    public int getX() {
            return x;
    }
        
        
    public int getZ() {
            return z;
    }
    
    
    public String toString() {
    	return "(" + x + ", " + z + ")";
    }
    
    
    public MutableCoords toMutableCoors() {
    	return new MutableCoords().init(x, z);
    }
        
        
}

