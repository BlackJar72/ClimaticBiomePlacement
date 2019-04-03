package jaredbgreat.climaticbiome.compat.userdef;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import biomesoplenty.common.world.WorldTypeBOP;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class DefReader {
	private static DefReader vanilla;
	private static DefReader BoP;
	private static DefReader traverse;
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
		if(ConfigHandler.useCfg) {
			custom.parser.makeBiomeList(list, filename);
		}
	}
	
	
	public static void init(IForgeRegistry reg, File dir) {
		vanilla = new DefReader(reg, dir, "Minecraft");
		BoP = new DefReader(reg, dir, "BiomeOPlenty");
		traverse = new DefReader(reg, dir, "Traverse");
		custom = new DefReader(reg, dir, "custom");
	}
	
	
	public static void writeList(File confdir) {
		File file = new File(confdir.toString() + File.separator + "BiomeList.txt");
		List<Biome> biomes = ForgeRegistries.BIOMES.getValues();
		try {
			BufferedWriter fs = new BufferedWriter(new FileWriter(file));
			for(Biome biome : biomes) {
				fs.write(biome.getRegistryName().toString());
				fs.newLine();
			}
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		file = new File(confdir.toString() + File.separator + "WorldTypeList.txt");
		try {
			BufferedWriter fs = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < WorldType.WORLD_TYPES.length; i++) {
				if((WorldType.WORLD_TYPES[i] != null) 
						&& WorldType.WORLD_TYPES[i].canBeCreated()) {
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
