package jaredbgreat.climaticbiome.generation.chunk;

public enum TerrainType {
	VARIABLE,    // This would be the norm if using non-biome height/scale data
	VANILLA,     // This would force the used of biome height/scale data
	MOUNTIANOUS, // For mountains, this adds variable and vanilla height, uses the higher scale
	STEEP;       // Like vanilla but reduces some averaging; for plateaus and technical biomes
	
	private static final TerrainType[] types 
		= new TerrainType[]{VARIABLE, VANILLA, MOUNTIANOUS, STEEP}; 
	    
	    
    /**
     * A convenience method, but one probably better coded locally in 
     * most situations for efficiency (at least in intended uses).  In 
     * some ways this is a reminder, but could be handy in non-performance 
     * critical code.
     * 
     * n is the number being converted to an asymptopic form.
     * start is the place where the output should start to curve.
     * rate is the reciprical of the value it should approach minus the start.
     * 
     * @param n
     * @param start
     * @param rate 
     * @return
     */
    public static float asymptote(float n, float start, float rate) {
    	if(n > start)
    	return start + (rate / (n - start + 1));
    	return n;
    }

    
    /**
     * Inverse of the ordinal() method, for use in decoding from serialized 
     * or otherwise stored ordinal values.  This does not bounds check.
     * 
     * @param number
     * @return The TerrainType corresponding the ordinal. 
     */
	public TerrainType fromOrdinal(int number) {
		return types[number];
	}
}
