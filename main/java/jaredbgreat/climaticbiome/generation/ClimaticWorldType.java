package jaredbgreat.climaticbiome.generation;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.generation.chunk.ChunkGenClimaticRealistic;
import jaredbgreat.climaticbiome.gui.GuiConfigureWorld;
import jaredbgreat.climaticbiome.util.Debug;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClimaticWorldType extends WorldType {
	private static final String GEN_TAG = "ChunkGenerator";
	
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
		return new ChunkGeneratorOverworld(world, world.getSeed(), 
    		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }

    
    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world) { 
        return new ClimaticBiomeProvider(world, false);
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
        mc.displayGuiScreen(new GuiConfigureWorld(guiCreateWorld, 
        		ClimaticWorldSettings.getQueued().toJsonString(), true));
    }


    
    

}
