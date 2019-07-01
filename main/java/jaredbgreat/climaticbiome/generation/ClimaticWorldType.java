package jaredbgreat.climaticbiome.generation;

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
    public static IChunkProvider chunkProvider;
    private static WorldType chunkProviderType;
    private static String chunkProviderName;
    private static boolean normalChunks;
    

    public ClimaticWorldType() {
		super("climatic_bp");		
	}

    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
    	if(normalChunks) {
    		return new ChunkGeneratorOverworld(world, world.getSeed(), 
        		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    	} else {
    		return chunkProviderType.getChunkGenerator(world, generatorOptions);
    	}
    }

    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world) { 
        return new ClimaticBiomeProvider(world);
    }
    
    
    public static void setChunkProviderType(String type) {
    	String theType = type.toLowerCase();
    	normalChunks = (theType.equals("default") 
    			     || theType.equals("climatic_bp")  
    			     || theType.equals("normal")
    			     || type.isEmpty());
    	chunkProviderName = type;
    }
    
    
    public static void initChunkProviderType() {
    	if(normalChunks) {
    		System.out.println("********************************************************");
    		System.out.println("Climatic Biome will use default ChunkProvider");
    		System.out.println("********************************************************");
    		return;
    	}
    	chunkProviderType = findWorldType(chunkProviderName);
    	if(chunkProviderType == null) {
    		System.out.println("********************************************************");
    		System.out.println("Warning: Ivalid WorldType given for ChunkProvider");
    		System.out.println("********************************************************");
    		normalChunks = true;
    	} else {
    		System.out.println("********************************************************");
    		System.out.println("Climatic Biomes using chunk provider for: " + chunkProviderType.getName());
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
