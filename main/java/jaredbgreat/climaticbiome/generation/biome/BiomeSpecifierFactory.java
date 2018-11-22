package jaredbgreat.climaticbiome.generation.biome;

public class BiomeSpecifierFactory {
	
	/*
	 * Arctic      = 1
	 * Subarctic   = 2
	 * Temperate   = 3a -- cool modded temperate
	 * Temperate   = 3b -- warm modded or vanilla temperate
	 * Subtropical = 4
	 * Tropical    = 5
	 */
	
	// Oceanic
    static IBiomeSpecifier ICE;   
    static IBiomeSpecifier OCEAN1;
    static IBiomeSpecifier OCEAN2;   
    static IBiomeSpecifier OCEAN3a;   
    static IBiomeSpecifier OCEAN3b;   
    static IBiomeSpecifier OCEAN4;   
    static IBiomeSpecifier OCEAN5;       
    // Wetland
    static IBiomeSpecifier SWAMP2;
    static IBiomeSpecifier SWAMP3a;
    static IBiomeSpecifier SWAMP3b;
    static IBiomeSpecifier SWAMP4;
    static IBiomeSpecifier SWAMP5;    
    // Tundra
    static IBiomeSpecifier TUNDRA;    
    // Grassland / "Plains"
    static IBiomeSpecifier GRASS2;
    static IBiomeSpecifier GRASS3a;
    static IBiomeSpecifier GRASS3b;
    static IBiomeSpecifier GRASS4;
    // Savanna
    static IBiomeSpecifier SAVANNA;
    // Forest
    static IBiomeSpecifier TAIGA;
    static IBiomeSpecifier FOREST3a;
    static IBiomeSpecifier FOREST3b;
    static IBiomeSpecifier FOREST4;
    static IBiomeSpecifier FOREST5;    
    // Parkland
    static IBiomeSpecifier PARK;
    // Jungle
    static IBiomeSpecifier JUNGLE;
    // Desert
    static IBiomeSpecifier DESERT;
    // Scrub
    static IBiomeSpecifier SCRUB;    
    // Alpine
    static IBiomeSpecifier ALPINE;
    
    
    public static void setup() {    	
    	// Oceanic
        ICE = new LeafBiome(10);   
        OCEAN1 = new BiomeList();
        OCEAN2 = new BiomeList();   
        OCEAN3a = new BiomeList();   
        OCEAN3b = new BiomeList();   
        OCEAN4 = new BiomeList();   
        OCEAN5 = new BiomeList();       
        // Wetland
        SWAMP2 = new BiomeList();
        SWAMP3a = new BiomeList();
        SWAMP3b = new BiomeList();
        SWAMP4 = new BiomeList();
        SWAMP5 = new BiomeList();    
        // Tundra
        TUNDRA = new BiomeList();    
        // Grassland / "Plains"
        GRASS2 = new BiomeList();
        GRASS3a = new BiomeList();
        GRASS3b = new BiomeList();
        GRASS4 = new BiomeList();
        // Savanna
        SAVANNA = new BiomeList();
        // Forest
        TAIGA = new BiomeList();
        FOREST3a = new BiomeList();
        FOREST3b = new BiomeList();
        FOREST4 = new BiomeList();
        FOREST5 = new BiomeList();    
        // Parkland
        PARK = new BiomeList();
        // Jungle
        JUNGLE = new BiomeList();
        // Desert
        DESERT = new BiomeList();
        // Scrub
        SCRUB = new BiomeList();    
        // Alpine //FIXME: Do I need more variations?
        ALPINE = new BiomeList();
    }
	
    
	public static BiomeClimateTable getVanillaTable() {		
		return null; // NOT!!!
	}
}
