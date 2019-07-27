package jaredbgreat.climaticbiome.compat.userdef;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IslandBiome;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class DefReader {
	private static DefReader vanilla;
	private static DefReader BoP;
	private static DefReader traverse;
	private static DefReader nt;
	private static DefReader abyssal;
	private static DefReader auxiliary;
	private static DefReader environs;
	private static DefReader pvj;
	private static DefReader byg;
	private static DefReader defiled;
	private static DefReader redwoods;
	private static DefReader zoestria;
	private static DefReader special;
	private static DefReader custom;
	private BiomeParser parser;
	
	
	public DefReader(IForgeRegistry reg, File dir, String sub) {		
		parser = new BiomeParser(reg, dir, sub);
	}
	
	
	public void readBiomeDataList(BiomeList list, String filename) {
		parser.makeBiomeList(list, filename);
	}
	
	
	public static void readBiomeData(BiomeList list, String filename) {
		if(ConfigHandler.useVanilla) {
			vanilla.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useBoP) {
			BoP.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useTraverse) {
			traverse.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useNT) {
			nt.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useAby) {
			abyssal.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useAux) {
			auxiliary.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useEnvirons) {
			environs.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.usePVJ) {
			pvj.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useBYG) {
			byg.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useDefiled) {
			defiled.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useRWmod) {
			redwoods.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useZoe) {
			zoestria.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useCfg) {
			custom.parser.makeBiomeList(list, filename);
		}
		if(ConfigHandler.useSpecial) {
			special.parser.addSpecialBiomes(list, filename);
		}
	}

	
	public static void init(IForgeRegistry reg, File dir) {
		vanilla = new DefReader(reg, dir, "Minecraft");
		BoP = new DefReader(reg, dir, "BiomeOPlenty");
		traverse = new DefReader(reg, dir, "Traverse");
		nt = new DefReader(reg, dir, "NovamTerram");
		abyssal = new DefReader(reg, dir, "AbyssalCraft");
		auxiliary = new DefReader(reg, dir, "AuxiliaryBiomes");
		environs = new DefReader(reg, dir, "Environs");
		pvj = new DefReader(reg, dir, "PVJ");
		byg = new DefReader(reg, dir, "BYG");
		defiled = new DefReader(reg, dir, "DefiledLands");
		redwoods = new DefReader(reg, dir, "Redwoods");
		zoestria = new DefReader(reg, dir, "Zoesteria");
		special = new DefReader(reg, dir, "special");
		custom = new DefReader(reg, dir, "custom");
	}
	
	
	public static void writeList(File confdir) {
		File file = new File(confdir.toString() + File.separator + "BiomeList.txt");
		List<Biome> biomes = ForgeRegistries.BIOMES.getValues();
		try {
			BufferedWriter fs = new BufferedWriter(new FileWriter(file));
			for(Biome biome : biomes) {
				fs.write(biome.getRegistryName().toString() 
						/*+ " (" + Biome.getIdForBiome(biome) + ")"*/);
				fs.newLine();
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void writeWTList(File confdir) {
		File file = new File(confdir.toString() + File.separator + "WorldTypeList.txt");
		try {
			BufferedWriter fs = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < WorldType.WORLD_TYPES.length; i++) {
				if((WorldType.WORLD_TYPES[i] != null)) {
					fs.write(WorldType.WORLD_TYPES[i].getName());
					fs.newLine();
				}
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}
