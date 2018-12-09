package jaredbgreat.climaticbiome.generation.biome.compat.userdef;

import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.WetDoubleBiome;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class BiomeParser {
	private IForgeRegistry biomeReg;
	private String fileDir;
	private interface ICommand {
		IBiomeSpecifier parse(String in);
	}
	private HashMap<String, ICommand> commands;
	
	
	public BiomeParser(IForgeRegistry reg, File dir) {
		biomeReg = reg;
		fileDir = dir.toString() + File.separator + "biomes" + File.separator;
		File fd = new File(fileDir);
		if(!fd.exists()) {
			fd.mkdirs();
		}
		commands = new HashMap<>();
		commands.put("biome", new LeafParse());
		commands.put("noise", new NoiseParse());
		commands.put("seed", new SeedParse());
		commands.put("temp", new TempParse());
		commands.put("wetness", new WetParse());
	}
	
	
	public void makeBiomeList(BiomeList list, String filename) {
		File file = new File(fileDir + filename);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		StringTokenizer tokens;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while(reader.ready()) {
				tokens = new StringTokenizer(reader.readLine(), "()");
				String tag =tokens.nextToken().toLowerCase().trim();
				if(tag.equals("") || tag.startsWith("#")) continue;
				list.addItem(commands.get(tag).parse(tokens.nextToken()));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private final class NoiseParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			StringTokenizer tokens = new StringTokenizer(in, ", ");
			Biome a = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));
			int bound = Integer.parseInt(tokens.nextToken());
			Biome b = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));		
			return new NoiseDoubleBiome(a, bound, b);
		}
	}
	
	
	
	private final class SeedParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			StringTokenizer tokens = new StringTokenizer(in, ", ");
			Biome a = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));
			int bound = Integer.parseInt(tokens.nextToken());
			Biome b = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));		
			return new SeedDoubleBiome(a, bound, b);
		}
	}
		
	
	private final class TempParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			StringTokenizer tokens = new StringTokenizer(in, ", ");
			Biome a = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));
			int bound = Integer.parseInt(tokens.nextToken());
			Biome b = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));		
			return new TempDoubleBiome(a, bound, b);
		}
	}
	
	
	private final class WetParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			StringTokenizer tokens = new StringTokenizer(in, ", ");
			Biome a = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));
			int bound = Integer.parseInt(tokens.nextToken());
			Biome b = (Biome)biomeReg.getValue(new ResourceLocation(tokens.nextToken()));		
			return new WetDoubleBiome(a, bound, b);
		}		
	}
	
	
	private final class LeafParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			Biome b = (Biome)biomeReg.getValue(new ResourceLocation(in));		
			return new LeafBiome(b);
		}
	}	
	
}
