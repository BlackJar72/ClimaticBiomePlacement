package jaredbgreat.climaticbiome.generation.map;

import static jaredbgreat.climaticbiome.util.ModMath.modRight;
import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.SubBiomeRegistry;
import jaredbgreat.climaticbiome.generation.cache.Cache;
import jaredbgreat.climaticbiome.generation.cache.Coords;
import jaredbgreat.climaticbiome.generation.generator.BiomeBasin;
import jaredbgreat.climaticbiome.generation.generator.MapMaker;
import jaredbgreat.climaticbiome.util.Debug;
import jaredbgreat.climaticbiome.util.SpatialNoise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;

public abstract class AbstractMapRegistry implements IMapRegistry {
	static final String SETTINGS = "settings";
    World world;
	
    File savedir =  null;
    boolean cansave = true;
    
    File settingsDir  = null;    
    File settingsFile = null;
    boolean perworld  = true;
    boolean pwtodo    = true;
    
    boolean noFakes;
	
	
	public AbstractMapRegistry(World w) {
        world = w;
	}
	
	
	final boolean areFakesInvalid() {
		return net.minecraftforge.fml.common.Loader.isModLoaded("lostcities") 
        		&& ConfigHandler.chunkProvider.equalsIgnoreCase("lostcities");
	}
	
	
	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#findSaveDir()
	 */
	@Override
	public void findSaveDir() {
		if(world == null || world.getMinecraftServer() == null) {
			cansave = false;
			return;
		}
		if(world.getMinecraftServer().isDedicatedServer()) {
			savedir      = world.getMinecraftServer().getFile("world" + File.separator + "ClimaticMaps" 
								   + File.separator + "Dim" + world.provider.getDimension());
			settingsDir  = world.getMinecraftServer().getFile("world" + File.separator + "ClimaticMaps" 
					   			   + File.separator + SETTINGS);
			settingsFile = world.getMinecraftServer().getFile("world" + File.separator + "ClimaticMaps" 
		   			   			   + File.separator + SETTINGS + File.separator 
		   			   			   + "dim" + world.provider.getDimension() + ".json");
		} else {
			savedir = new File(DimensionManager.getCurrentSaveRootDirectory().toString() 
							+ File.separator + "ClimaticMaps" 
							+ File.separator + "Dim" 
							+ world.provider.getDimension());
			settingsDir = new File(DimensionManager.getCurrentSaveRootDirectory().toString() 
							+ File.separator + "ClimaticMaps" 
							+ File.separator + SETTINGS);
			settingsFile = new File(DimensionManager.getCurrentSaveRootDirectory().toString() 
							+ File.separator + "ClimaticMaps" 
							+ File.separator + SETTINGS
							+ File.separator + "dim" 
							+ world.provider.getDimension() + ".json");
		}
		cansave  = savedir != null;
		perworld = (settingsDir != null) && (settingsFile != null);
		if(cansave && (!savedir.exists())) {
			savedir.mkdirs();
			cansave = savedir.exists() && savedir.isDirectory();
		}
		if(perworld && (!settingsDir.exists())) {
			settingsDir.mkdirs();
			perworld = settingsDir.exists() && settingsDir.isDirectory();
		}
	}
	
	
	File getSaveFile(int x, int z) {
		if(savedir == null) {
			findSaveDir();
		}
		if(savedir == null) {
			cansave = false;
			return null;
		}
		return new File(savedir.toString() + File.separator + "X" + x + "Z" + z + ".cbmap");
	}
	
	
	void readSettings() {
		if(settingsFile == null) {
			return;
		}
		if(settingsFile.exists()) {
			if(settingsFile.isFile()) {
				// Read settings
				try {				
					BufferedReader fs = new BufferedReader(new FileReader(settingsFile));
					Debug.bigSysout(fs.readLine());
					fs.close();
					pwtodo = false;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} else {
				System.err.println("Settings file \"" + settingsFile + "\" is not a valid file!");
			}
		} else {
			try {				
				BufferedWriter fs = new BufferedWriter(new FileWriter(settingsFile));
				fs.append("Writing to the Settings file!");
				fs.close();
				pwtodo = false;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	
	/**
	 * A class for hashing data that is streamed through, 
	 * purely for debugging.
	 */
	class Hasher {
    	int hash = 0;
    	int count = 0;
    	public void next(int b) {
	    	hash ^= (b & 0xff) << (8 * count);
	    	hash ^= hash << 13;
	    	hash ^= hash >> 5;
	    	hash ^= hash << 17;
	    	count = (++count) % 4;
		}
    	public int getHash() {
    		return hash;
    	}
	}
	

}
