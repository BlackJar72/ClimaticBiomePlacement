package jaredbgreat.climaticbiome.generation.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jaredbgreat.climaticbiome.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.Logging;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;

public abstract class AbstractMapRegistry implements IMapRegistry {
	static final String SETTINGS = "settings";
	ClimaticWorldSettings settings;
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
        findSaveDir();
        readSettings();
	}
	
	
	final boolean areFakesInvalid() {
		return ModList.get().isLoaded("lostcities") 
        		&& ConfigHandler.chunkProvider.equalsIgnoreCase("lostcities");
	}
	
	
	/* (non-Javadoc)
	 * @see jaredbgreat.climaticbiome.generation.map.IMapRegistry#findSaveDir()
	 */
	@Override
	public void findSaveDir() {
		if(world == null || world.getServer() == null) {
			cansave = false;
			return;
		}
		// FIXME?: This may or may not work!
		if(true/*world.getServer().isDedicatedServer()*/) {
			savedir      = world.getServer().getFile("world" + File.separator + "ClimaticMaps" 
								   + File.separator + "Dim" + world.getDimension());
			settingsDir  = world.getServer().getFile("world" + File.separator + "ClimaticMaps" 
					   			   + File.separator + SETTINGS);
			settingsFile = world.getServer().getFile("world" + File.separator + "ClimaticMaps" 
		   			   			   + File.separator + SETTINGS + File.separator 
		   			   			   + "dim" + world.getDimension() + ".json");
		} else {/*
			savedir = new File(world.getServer().getSaveHandler().getWorldDirectory().toString() 
							+ File.separator + "ClimaticMaps" 
							+ File.separator + "Dim" 
							+ world.getDimension());
			settingsDir = new File(world.getSaveHandler().getWorldDirectory().toString() 
							+ File.separator + "ClimaticMaps" 
							+ File.separator + SETTINGS);
			settingsFile = new File(world.getSaveHandler().getWorldDirectory().toString() 
							+ File.separator + "ClimaticMaps" 
							+ File.separator + SETTINGS
							+ File.separator + "dim" 
							+ world.getDimension() + ".json");
		*/}
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
	
	
	/**
	 * Find the actual save file to use for saving continental maps.
	 * 
	 * @param x
	 * @param z
	 * @return
	 */
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
	
	
	/**
	 * Read in per-world settings if they exist; if not, create 
	 * some per-world settings. 
	 */
	void readSettings() {
		settings = ClimaticWorldSettings.getQueued();
		if(settingsFile == null) {
			return;
		}
		if(settingsFile.exists()) {
			if(settingsFile.isFile()) {
				// Read settings
				try {				
					BufferedReader fs = new BufferedReader(new FileReader(settingsFile));
					String json = fs.readLine();
					if(json != null) {
						settings = settings.fromJsonString(json);
					}
					fs.close();
					pwtodo = false;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} else {
				// FIXME: I should be logging with a logger!!!
				Logging.logError("Settings file \"" + settingsFile + "\" is not a valid file!");
			}
		} else if(!world.isRemote) {
			// If there is not a settings file to read, write a new one!
			try {				
				BufferedWriter fs = new BufferedWriter(new FileWriter(settingsFile));
				fs.append(settings.toJsonString());
				fs.close();
				pwtodo = false;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		settings.applySettings();
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
