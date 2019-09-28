package jaredbgreat.climaticbiome;

import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.biomes.basic.ActiveVolcano;
import jaredbgreat.climaticbiome.biomes.feature.GenPine;
import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.compat.userdef.VariantParser;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.generation.ClimaticWorldType;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetForest;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetPark;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetPlains;
import jaredbgreat.climaticbiome.proxy.IProxy;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.Externalizer;
import jaredbgreat.climaticbiome.util.ItemRegistrar;

import java.io.File;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.WoodlandMansion;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


@Mod(modid=Info.ID, name=Info.NAME, version=Info.VERSION,
acceptableRemoteVersions=Info.VERSION, dependencies=Info.DEPSTR)
public class ClimaticBiomes {
	
	@Instance
	public static ClimaticBiomes instance;
	public static ClimaticWorldType worldType;
	public ConfigHandler configHandler;
	File confdir;

	@SidedProxy(clientSide = "jaredbgreat.climaticbiome.proxy.ClientProxy",
			    serverSide = "jaredbgreat.climaticbiome.proxy.ServerProxy")
	public static IProxy proxy;
	
	public static org.apache.logging.log4j.Logger logger;
	

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	logger = event.getModLog();
    	confdir = new File(event.getModConfigurationDirectory().toPath()
    			+ File.separator + Info.DIR);
    	configHandler = new ConfigHandler(confdir.toString());
    	configHandler.load();
    	BlockRegistrar.initBlocks();
    	ItemRegistrar.initItems();
    	GenPine.init();
    	worldType = new ClimaticWorldType();
    	ModBiomes.createBiomes();
    	if(ConfigHandler.addToVanilla) {
    		ModBiomes.addToVanilla();
    	}
    	proxy.preInit();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init();
    	ActiveVolcano.init();
    	ItemRegistrar.addRecipes();
    	makeFiles();
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	if(ConfigHandler.makeDefault) {
    		moveWorldTypes();
    	}
    	if(ConfigHandler.writeBiomeLists) {
    		DefReader.writeList(confdir);
    	}
    	if(ConfigHandler.writeWTLists) {
    		DefReader.writeWTList(confdir);
    	}
    	DefReader.init(ForgeRegistries.BIOMES, confdir);
    	VariantParser.parse(confdir);
    	ItemRegistrar.oreDict();
    	ClimaticWorldType.initChunkGeneratorType();
    	try {
	    	if(ConfigHandler.moreMansion) {
		    	for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
		    		if(BiomeDictionary.hasType(biome, Type.FOREST) 
		    				&& !WoodlandMansion.ALLOWED_BIOMES.contains(biome)) {
		    		}
		    	}
	    	}
    	} catch(UnsupportedOperationException e) {
    		Logger log = Logger.getLogger("Minecraft");
    		log.warning("[Climatic Biomes] Woodland Mansion genaraion cannot be modified!");
    		log.warning("[Climatic Biomes] You might need a different version of Forge or Java (probably Java)");
    	}
    }
    
    
    private void makeFiles() {
    	Externalizer extern = new Externalizer();
    	extern.copyOut(confdir);    	
    }
    
    
    private void moveWorldTypes() {
    	WorldType.WORLD_TYPES[0] = worldType;
    }
    

}
