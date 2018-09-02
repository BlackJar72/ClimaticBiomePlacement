package jaredbgreat.climaticbiome.generation.map;

import jaredbgreat.climaticbiome.generation.cache.AbstractCachable;
import jaredbgreat.climaticbiome.generation.cache.Coords;

public class ArrayHolder<T> extends AbstractCachable {
	private final T[] data;
	
	public ArrayHolder(Coords coords, T[] data) {
		super(coords);
		this.data = data;
	}
	
	
	public ArrayHolder(int x, int z, T[] data) {
		super(x, z);
		this.data = data;
	}
	
	
	public T[] getData() {
		return data;
	}

}
