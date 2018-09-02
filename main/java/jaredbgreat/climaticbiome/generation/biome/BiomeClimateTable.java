package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

public class BiomeClimateTable implements IBiomeSpecifier {	
	private final IBiomeSpecifier[] table;
	
	/**
	 * Create a table for looking up biomes based on temperature 
	 * and wetness.  The there need to be exactly 250 IBiomeSpecifiers
	 * provided (less than 250 will crash, more is harmless but 
	 * wasteful).  These should usually be BiomeLists containing more 
	 * specific specifiers, as this is the master table for finding 
	 * climate types.
	 * 
	 * The data being provided will be treated as a table 10x25 table as 
	 * follows: 10 across representing wetness and 25 down representing 
	 * temperature.  The temperatures are devided into five (vanilla) or 
	 * six (BoP, etc.) climate zones four or five rows each (all five 
	 * for vanilla biomes).  That is arctic, sub-arctic, temperate, 
	 * sub-tropical, and tropical -- with temperate dived into cool 
	 * temperate and warm temperate when advanced modded biomes are 
	 * available.  The interpretation of wetness is relative to what 
	 * is typical for a temperature region (e.g., most deserts are in 
	 * the sub-tropics). 
	 * 
	 * @param table *MUST* be 250 elements!
	 */
	public BiomeClimateTable(IBiomeSpecifier... table) {
		this.table = table;
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		return table[(tile.getTemp() * 10) + tile.getWet()].getBiome(tile);
	}

}
