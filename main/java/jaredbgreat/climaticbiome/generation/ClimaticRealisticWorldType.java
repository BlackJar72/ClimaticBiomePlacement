package jaredbgreat.climaticbiome.generation;

import javax.annotation.Nonnull;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.generation.chunk.ChunkGenClimaticRealistic;
import jaredbgreat.climaticbiome.gui.GuiConfigureWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClimaticRealisticWorldType extends WorldType {
	private static final String GEN_TAG = "ChunkGenerator";
	
	private static BiomeProvider biomeProvider;
    public static IChunkProvider chunkGenerator;
    private static WorldType chunkGeneratorType;
    private static String chunkGeneratorName;
    private static volatile boolean normalChunks;
    private static volatile boolean altChunks;
    

    public ClimaticRealisticWorldType() {
		super("climatic_real");		
	}

    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
		return new ChunkGenClimaticRealistic(world, world.getSeed(), 
        		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
		//return new ChunkGenFakeVanilla(world, world.getSeed(), 
        //		world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
    }

    
    @Override @Nonnull
    public BiomeProvider getBiomeProvider(@Nonnull World world) { 
        return new ClimaticBiomeProvider(world, true);
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
        		ClimaticWorldSettings.getQueued().toJsonString(), false));
    }


    
    

}
