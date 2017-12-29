package jaredbgreat.climaticbiome.generation.cache;

import jaredbgreat.climaticbiome.generation.chunk.SpatialNoise;

public final class Coords {
	private static final SpatialNoise nh = new SpatialNoise(0xADD5C0DE);
	public final int x, z;
	
	
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
	
	
	@Override
	public int hashCode() {
		return nh.intFor(x, z, 0);			
	}
}