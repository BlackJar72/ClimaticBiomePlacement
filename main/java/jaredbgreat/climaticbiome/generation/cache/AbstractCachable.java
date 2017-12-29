package jaredbgreat.climaticbiome.generation.cache;

public abstract class AbstractCachable implements ICachable { 
    private MutableCoords coords;
    private long timestamp;
    private boolean cached;
    private ICachable nextInCacheBucket;
    
    
    public AbstractCachable() {
    	cached = false;
        coords = new MutableCoords();
        timestamp = System.currentTimeMillis();
    }
    
    
    public AbstractCachable(int x, int z) {
    	cached = false;
        coords = new MutableCoords().init(x, z);
        timestamp = System.currentTimeMillis();
    }
    
    
    @Override
    public void use() {
            timestamp = System.currentTimeMillis();		
    }
    
    
    @Override
    public boolean isOld() {
            long t = System.currentTimeMillis() - timestamp;
            return ((t > 30000) || (t < 0));	
    }
    
    
    @Override
    public boolean isCached() {
            return cached;
    }
    
    
    @Override
    public MutableCoords getCoords() {
        return coords;
    }
        
        
    @Override
    public void setCached(Boolean wasCached) {
            cached = wasCached;
    }
    

    @Override
    public ICachable getNextCacheBucket() {
        return nextInCacheBucket;
    }
    

    @Override
    public void setNextCacheBucket(ICachable item) {
        nextInCacheBucket = item;
    }
    
}
