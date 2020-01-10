package jaredbgreat.climaticbiomes.generation;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;

public class ClimaticWorldType extends WorldType {
	private static BiomeProvider biomeProvider;
    public static ChunkGenerator chunkGenerator;
    private static WorldType chunkGeneratorType;
    private static String chunkGeneratorName;
    private static boolean normalChunks;
    

    public ClimaticWorldType() {
		super("climatic_bp");		
	}

    public ChunkGenerator getChunkGenerator(World world, String generatorOptions) {
    	// TODO: Everything
    	return null;
    }

    
    public static void setChunkGeneratorType(String type) {
    	String theType = type.toLowerCase();
    	normalChunks = (theType.equals("default") 
    			     || theType.equals("climatic_bp")  
    			     || theType.equals("normal")
    			     || type.isEmpty());
    	chunkGeneratorName = type;
    }
    
    
    public static void initChunkGeneratorType() {
    	if(normalChunks) {
    		System.out.println("********************************************************");
    		System.out.println("Climatic Biome will use default ChunkProvider");
    		System.out.println("********************************************************");
    		return;
    	}
    	chunkGeneratorType = findWorldType(chunkGeneratorName);
    	if(chunkGeneratorType == null) {
    		System.out.println("********************************************************");
    		System.out.println("Warning: Ivalid WorldType given for ChunkProvider");
    		System.out.println("********************************************************");
    		normalChunks = true;
    	} else {
    		System.out.println("********************************************************");
    		System.out.println("Climatic Biomes using chunk provider for: " + chunkGeneratorType.getName());
    		System.out.println("********************************************************");
    	}
    }
    
    
    private static WorldType findWorldType(String name) {
		for(int i = 0; i < WorldType.WORLD_TYPES.length; i++) {
			if((WorldType.WORLD_TYPES[i] != null) 
					&& WorldType.WORLD_TYPES[i].getName().equalsIgnoreCase(name)) {
				return WorldType.WORLD_TYPES[i];
			}
		}
		return null;    	
    }


    
    

}
