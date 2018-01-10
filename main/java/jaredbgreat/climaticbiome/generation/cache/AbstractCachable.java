package jaredbgreat.climaticbiome.generation.cache;

public abstract class AbstractCachable implements ICachable { 
    private Coords coords;
    private long timestamp;
    private boolean cached;
    
    
    public AbstractCachable(int x, int z) {
    	cached = false;
        coords = new Coords(x, z);
        timestamp = System.currentTimeMillis();
    }
    
    
    @Override
    public void use() {
    	timestamp = System.currentTimeMillis();		
    }
    
    
    @Override
    public boolean isOldData() {
    	long t = System.currentTimeMillis() - timestamp;
    	return ((t > 30000) || (t < 0));	
    }
    
    
    @Override
    public Coords getCoords() {
        return coords;
    }
    
}
