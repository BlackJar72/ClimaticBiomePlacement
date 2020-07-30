package jaredbgreat.climaticbiome.compat.userdef;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.exception.BiomeReadingException;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.IslandBiome;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.MesaDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.TerrainBiome;
import jaredbgreat.climaticbiome.generation.biome.WetDoubleBiome;
import jaredbgreat.climaticbiome.generation.biome.biomes.GetTaiga.TaigaDoubleBiome;
import jaredbgreat.dldungeons.parser.Tokenizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.registries.IForgeRegistry;

import org.apache.logging.log4j.Logger;
import org.jline.utils.Log;

public class BiomeParser {
	private IForgeRegistry biomeReg;
	private String fileDir;
	private interface ICommand {
		IBiomeSpecifier parse(String in);
	}
	private HashMap<String, ICommand> commands;
	private BufferedReader reader1;
	private BufferedReader reader2;
	
	
	public BiomeParser(IForgeRegistry reg, File dir, String sub) {
		biomeReg = reg;
		fileDir = dir.toString() + File.separator + "BiomeConfig" 
								 + File.separator + sub + File.separator;
		File fd = new File(fileDir);
		if(!fd.exists()) {
			fd.mkdirs();
		}
		commands = new HashMap<>();
		commands.put("biome", new LeafParse());
		commands.put("noise", new NoiseParse());
		commands.put("seed", new SeedParse());
		commands.put("temp", new TempParse());
		commands.put("taiga", new TaigaParse());
		commands.put("wetness", new WetParse());
		commands.put("island", new IslandParse());
		commands.put("plateau", new MesaParse());
		commands.put("terrain", new TerrainParse());
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
		String line = null;
		Tokenizer tokens;		
		try {
			reader1 = new BufferedReader(new FileReader(file));
			while(reader1.ready()) {
				line = reader1.readLine().trim();
				if(line.isEmpty() || line.startsWith("#")) continue;
				tokens = new Tokenizer(line, "()");
				String tag = tokens.nextToken().toLowerCase().trim();
				try {
					// First create the specifier, then add it only if its valid
					IBiomeSpecifier biomeSpec = commands.get(tag).parse(tokens.nextToken());
					if(!biomeSpec.isEmpty()) {
						list.addItem(biomeSpec);
					} else {
		            	ClimaticBiomes.logger.error("\nFailed to load biome: \n"
		            			+ " \t Tag: " + tag + "\n"
		            			+ " \t Full String: " + line + "\n"
		            			+ " \t File: " + filename + "\n");	
		            	if(ConfigHandler.failfast) {
		            		throw new BiomeReadingException();
		            	}
					}
	            } catch (Exception e) {
	            	ClimaticBiomes.logger.error("\nFailed to load biome: \n"
	            			+ " \t Tag: " + tag + "\n"
	            			+ " \t Full String: " + line + "\n"
	            			+ " \t File: " + filename + "\n");						
	            	e.printStackTrace();
	                throw e;
	            }
			}
			reader1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			reportError(e, file, line);
		}
	}
	
	
	public void addSpecialBiomes(BiomeList list, String filename) {
		File file = new File(fileDir + filename);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		String line = null;
		Tokenizer tokens;		
		try {
			reader2 = new BufferedReader(new FileReader(file));
			while(reader2.ready()) {
				line = reader2.readLine().trim();
				if(line.isEmpty() || line.startsWith("#")) continue;
				tokens = new Tokenizer(line, "()");
				String tag = tokens.nextToken().toLowerCase().trim();
				try {
					// First create the specifier, then add it only if its valid
					IBiomeSpecifier biomeSpec = commands.get(tag).parse(tokens.nextToken());
					if(!biomeSpec.isEmpty()) {
						list.addItem(biomeSpec);
					// These are quite typically not there, don't be strict
					} else if(ConfigHandler.failfast) {
		            	ClimaticBiomes.logger.error("\nFailed to load biome: \n"
		            			+ " \t Tag: " + tag + "\n"
		            			+ " \t Full String: " + line + "\n"
		            			+ " \t File: " + filename + "\n");
	            		throw new BiomeReadingException();
					}
	            } catch (Exception e) {
	            	ClimaticBiomes.logger.error("\nFailed to load biome: \n"
	            			+ " \t Tag: " + tag + "\n"
	            			+ " \t Full String: " + line + "\n"
	            			+ " \t File: " + filename + "\n");						
	            	e.printStackTrace();
	                throw e;
	            }
			}
			reader2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			reportError(e, file, line);
		}
	}
	
	
	private void reportError(RuntimeException ex, File file, String line) {
		Logger logger = FMLLog.log;
		Log.error("");
		Log.error("*****************************");
		Log.error("   Error in file: " + file);
		Log.error("   " + line);
		Log.error("   Caused excpetion: " + ex);		
		Log.error("*****************************");
		Log.error("");
		throw ex;
	}
	
	
	private final class NoiseParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			Tokenizer tokens = new Tokenizer(in, ", \t");		
			return new NoiseDoubleBiome(tokens.nextToken(), 
					   Integer.parseInt(tokens.nextToken()), 
					   tokens.nextToken(), biomeReg);
		}
	}
	
	
	private final class MesaParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			Tokenizer tokens = new Tokenizer(in, ", \t");		
			return new MesaDoubleBiome(tokens.nextToken(), 
					   Integer.parseInt(tokens.nextToken()), 
					   tokens.nextToken(), biomeReg);
		}
	}
	
	
	
	private final class SeedParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			Tokenizer tokens = new Tokenizer(in, ", \t");		
			return new SeedDoubleBiome(tokens.nextToken(), 
					   Integer.parseInt(tokens.nextToken()), 
					   tokens.nextToken(), biomeReg);
		}
	}

	
	private final class TempParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			Tokenizer tokens = new Tokenizer(in, ", \t");		
			return new TempDoubleBiome(tokens.nextToken(), 
					   Integer.parseInt(tokens.nextToken()), 
					   tokens.nextToken(), biomeReg);
		}
	}
	
	
	private final class TaigaParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			Tokenizer tokens = new Tokenizer(in, ", \t");	
			return new TaigaDoubleBiome(tokens.nextToken(), 
					   tokens.nextToken(), biomeReg);
		}
	}
	
	
	private final class WetParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			Tokenizer tokens = new Tokenizer(in, ", \t");	
			return new WetDoubleBiome(tokens.nextToken(), 
					   Integer.parseInt(tokens.nextToken()), 
					   tokens.nextToken(), biomeReg);
		}		
	}
	
	
	private final class LeafParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			return new LeafBiome(in, biomeReg);
		}
	}
	
	
	private final class TerrainParse implements ICommand {
		public IBiomeSpecifier parse(String in) {
			return new TerrainBiome(in, biomeReg);
		}
	}
	
	
	private final class IslandParse implements ICommand {
		public IBiomeSpecifier parse(String in) {	
			return new IslandBiome(in, biomeReg);
		}
	}
	
}
