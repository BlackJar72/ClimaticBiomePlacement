package jaredbgreat.climaticbiomes.compat.userdef;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.jline.utils.Log;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.exception.BiomeReadingException;
import jaredbgreat.climaticbiomes.generation.biome.BiomeList;
import jaredbgreat.climaticbiomes.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiomes.generation.biome.IslandBiome;
import jaredbgreat.climaticbiomes.generation.biome.LeafBiome;
import jaredbgreat.climaticbiomes.generation.biome.NoiseDoubleBiome;
import jaredbgreat.climaticbiomes.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiomes.generation.biome.TempDoubleBiome;
import jaredbgreat.climaticbiomes.generation.biome.WetDoubleBiome;
import jaredbgreat.climaticbiomes.generation.biome.biomes.GetTaiga.TaigaDoubleBiome;
import jaredbgreat.climaticbiomes.util.Logging;
import jaredbgreat.dldungeons.parser.Tokenizer;
import net.minecraftforge.registries.IForgeRegistry;

public class BiomeParser {
    private IForgeRegistry biomeReg;
    private String fileDir;
    private interface ICommand {
        IBiomeSpecifier parse(String in);
    }
    private HashMap<String, ICommand> commands;


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
    }


    public void makeBiomeList(BiomeList list, String filename) {
        //Logging.logInfo("Reading biomes for " + fileDir + filename);
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
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                line = reader.readLine().trim();
                //Logging.logInfo(" \t Read line: " + line);
                if(line.isEmpty() || line.startsWith("#")) continue;
                tokens = new Tokenizer(line, "()");
                String tag = tokens.nextToken().toLowerCase().trim();
                try {
                    // First create the specifier, then add it only if its valid
                    IBiomeSpecifier biomeSpec = commands.get(tag).parse(tokens.nextToken());
                    if(!biomeSpec.isEmpty()) {
                        list.addItem(biomeSpec);
                    } else {
                        Logging.logError("\nFailed to load biome: \n"
                                + " \t Tag: " + tag + "\n"
                                + " \t Full String: " + line + "\n"
                                + " \t File: " + filename + "\n");
                        if(ConfigHandler.failfast) {
                            throw new BiomeReadingException();
                        }
                    }
                } catch (Exception e) {
                    Logging.logError("\nFailed to load biome: \n"
                            + " \t Tag: " + tag + "\n"
                            + " \t Full String: " + line + "\n"
                            + " \t File: " + filename + "\n");
                    e.printStackTrace();
                    throw e;
                }
            }
            reader.close();
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
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.ready()) {
                line = reader.readLine().trim();
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
                        Logging.logError("\nFailed to load biome: \n"
                                + " \t Tag: " + tag + "\n"
                                + " \t Full String: " + line + "\n"
                                + " \t File: " + filename + "\n");
                        throw new BiomeReadingException();
                    }
                } catch (Exception e) {
                    Logging.logError("\nFailed to load biome: \n"
                            + " \t Tag: " + tag + "\n"
                            + " \t Full String: " + line + "\n"
                            + " \t File: " + filename + "\n");
                    e.printStackTrace();
                    throw e;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            reportError(e, file, line);
        }
    }


    private void reportError(RuntimeException ex, File file, String line) {
        Logging.logError("\n*****************************"
                + "\n   Error in file: " + file
                + "\n   " + line
                + "\n   Caused excpetion: " + ex
                + "\n*****************************\n");
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


    private final class IslandParse implements ICommand {
        public IBiomeSpecifier parse(String in) {
            return new IslandBiome(in, biomeReg);
        }
    }

}
