package jaredbgreat.climaticbiome.compat.userdef;

import jaredbgreat.climaticbiome.biomes.pseudo.PseudoBiomes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import net.minecraft.util.ResourceLocation;

public class VariantParser {
	private File fileDir;
	
	
	public VariantParser(File configDir) {
		fileDir  = new File(configDir + File.separator + "VariantConfig");
		File fileName = new File(fileDir + File.separator + "BiomeVariants.cfg");
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			while(reader.ready()) {
				String line = reader.readLine().trim();
				if(line.isEmpty() || line.startsWith("#")) continue;
				StringTokenizer tokens = new StringTokenizer(line, ":, ");
				PseudoBiomes.addSubBiome(
						new ResourceLocation(tokens.nextToken(), tokens.nextToken()), 
						Integer.parseInt(tokens.nextToken()), 
						Integer.parseInt(tokens.nextToken()), 
						Integer.parseInt(tokens.nextToken()));								
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
