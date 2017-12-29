package jaredbgreat.climaticbiome.generation.cache;

import net.minecraft.server.MinecraftServer;

public interface ICachable {	
	
	/**
	 * Updates last used time.
	 */
	public void use();
	/**
	 * Is this ready to be removed?
	 * 
	 * @return
	 */
	public boolean isOld();
	/**
	 * Was this an old item returned from the cache.
	 * 
	 * @return
	 */
	public boolean isCached();
        /**
         * Return the MutableCoords for this object.
         */
        public MutableCoords getCoords();
	/**
	 * Mark whether this is in the cache.
	 */
	public void setCached(Boolean wasCached);
	/**
	 * Return the Coords object for this object.
	 * 
	 * @return
	 */
        public ICachable getNextCacheBucket();
	/**
	 * Sets the next item in the cache bucket.
	 * 
	 * @return
	 */
        public void setNextCacheBucket(ICachable item);
}
