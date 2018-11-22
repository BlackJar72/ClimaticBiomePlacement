package jaredbgreat.climaticbiome.generation.biome;

import net.minecraft.world.biome.Biome;

/**
 * This class is to create and populate the tables used by the generator to assign 
 * actual biomes.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
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
    static BiomeList OCEAN1;
    static BiomeList OCEAN2;   
    static BiomeList OCEAN3a;   
    static BiomeList OCEAN3b;   
    static BiomeList OCEAN4;   
    static BiomeList OCEAN5;       
    // Wetland
    static BiomeList SWAMP2;
    static BiomeList SWAMP3a;
    static BiomeList SWAMP3b;
    static BiomeList SWAMP4;
    static BiomeList SWAMP5;    
    // Tundra
    static BiomeList TUNDRA;    
    // Grassland / "Plains"
    static BiomeList GRASS2;
    static BiomeList GRASS3a;
    static BiomeList GRASS3b;
    static BiomeList GRASS4;
    // Savanna
    static BiomeList SAVANNA;
    // Forest
    static BiomeList TAIGA;
    static BiomeList FOREST3a;
    static BiomeList FOREST3b;
    static BiomeList FOREST4;
    static BiomeList FOREST5;    
    // Parkland
    static BiomeList PARK;
    // Jungle
    static BiomeList JUNGLE;
    // Desert
    static BiomeList DESERT;
    // Scrub
    static BiomeList SCRUB;    
    // Alpine
    static BiomeList ALPINE;
    
    
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
