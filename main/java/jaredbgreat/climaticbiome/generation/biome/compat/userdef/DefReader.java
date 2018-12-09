package jaredbgreat.climaticbiome.generation.biome.compat.userdef;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class DefReader {
	private static BiomeParser parser;
	
	public static void readBiomeData(BiomeList list, String filename) {
		parser.makeBiomeList(list, filename);
	}
	
	
	public static void init(IForgeRegistry reg, File dir) {		
		parser = new BiomeParser(reg, dir);
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
	}
	


}
