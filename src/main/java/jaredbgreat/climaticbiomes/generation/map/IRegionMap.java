package jaredbgreat.climaticbiomes.generation.map;

import jaredbgreat.climaticbiomes.generation.cache.Coords;

public interface IRegionMap {

	/**
	 * This will set the real and pseudo-biomes to the same value 
	 * while assuming no data has been stored (i.e., that the array 
	 * is freshly initialized so that the value is zero).
	 * 
	 * @param biome
	 * @param x relative chunk x within region
	 * @param z relative chunk x within region
	 */
	public abstract void setBiomeExpress(long biome, int i);

	public abstract int hashCode();

	public abstract boolean equals(Object other);

	/**
	 * Returns a hash based on the data array; purely for
	 * debugging.
	 * 
	 * @return
	 */
	public abstract int otherHash();

	public abstract Coords getCoords();

}