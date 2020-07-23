package jaredbgreat.climaticbiome.generation;

import jaredbgreat.climaticbiome.generation.chunk.ChunkGenClimaticRealistic;
import jaredbgreat.climaticbiome.gui.GuiConfigureWorld;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClimaticWorldType extends WorldType {
	private static BiomeProvider biomeProvider;
    public static IChunkProvider chunkGenerator;
    private static WorldType chunkGeneratorType;
    private static String chunkGeneratorName;
    private static volatile boolean normalChunks;
    private static volatile boolean altChunks;
    

    public ClimaticWorldType() {
		super("climatic_bp");		
	}

    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
    	if(normalChunks) {
    		return new ChunkGeneratorOverworld(world, world.getSeed(), 
        		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    	} else if(altChunks) {
    		return new ChunkGenClimaticRealistic(world, world.getSeed(), 
            		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    	} else {
    		System.out.println("\nFUCK!!!\nFUCK!!! \nFUCK!!! \nFUCK!!! \nFUCK!!! \nFUCK!!! \nFUCK!!! \nFUCK!!! \n");
    		return chunkGeneratorType.getChunkGenerator(world, generatorOptions);
    	}
    }

    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world) { 
        return new ClimaticBiomeProvider(world, altChunks);
    }
    
    
    public static void setChunkGeneratorType(String type) {
    	System.out.println();
    	System.out.println("Chunk Privder = " + type);
    	String theType = type.toLowerCase();
    	normalChunks = (theType.equals("default")  
    			     || theType.equals("normal") 
    			     || theType.equals("vanilla") 
    			     || theType.equals("minecraft")
    			     || type.isEmpty());
    	altChunks    = (theType.equals("climatic")  
		         	 || theType.equals("climatic_bp")  
			         || theType.equals("climatic_realistic"));
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
    	if((chunkGeneratorType == null)) {
    		if(!altChunks) {
	    		System.out.println("********************************************************");
	    		System.out.println("Warning: Ivalid WorldType given for ChunkProvider");
	    		System.out.println("********************************************************");
	    	}
    		normalChunks = !altChunks;
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
    
    
    @Override
    public boolean isCustomizable() {
        return true;
    }
    
    
    @Override
    @SideOnly(Side.CLIENT)
    public void onCustomizeButton(Minecraft mc, GuiCreateWorld guiCreateWorld) {
        mc.displayGuiScreen(new GuiConfigureWorld(guiCreateWorld, guiCreateWorld.chunkProviderSettingsJson));
    }


    
    

}
