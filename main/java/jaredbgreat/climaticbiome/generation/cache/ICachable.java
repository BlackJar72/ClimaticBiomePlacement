package jaredbgreat.climaticbiome.generation.cache;


public interface ICachable extends IHaveCoords {	
	
	/**
	 * Updates last used time.
	 */
	public void use();
	/**
	 * Is this ready to be removed?
	 * 
	 * @return
	 */
	public boolean isOldData();
	/**
	 * Was this an old item returned from the cache.
	 * 
	 * @return
	 */
}
