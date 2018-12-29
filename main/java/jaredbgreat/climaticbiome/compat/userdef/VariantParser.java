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
	
	public static void parse(File configDir) {
		File fileDir  = new File(configDir + File.separator + "BiomeConfig" 
	                                       + File.separator + "BiomeVariants");
		File fileName = new File(fileDir + File.separator + "variants.cfg");
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			while(reader.ready()) {
				String line = reader.readLine().trim();
				//System.err.println(line);
				if(line.isEmpty() || line.startsWith("#")) continue;
				StringTokenizer tokens = new StringTokenizer(line, ":, ");
				PseudoBiomes.addSubBiome(
						new ResourceLocation(tokens.nextToken(), tokens.nextToken()), 
						Integer.parseInt(tokens.nextToken()), 
						Float.parseFloat(tokens.nextToken()), 
						Float.parseFloat(tokens.nextToken()));								
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
