package jaredbgreat.climaticbiome.util;

/* 
 * This mod is the creation and copyright (c) 2015 
 * of Jared Blackburn (JaredBGreat).
 * 
 * It is licensed under the creative commons 4.0 attribution license: * 
 * https://creativecommons.org/licenses/by/4.0/legalcode
*/	

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * The purpose of this class is to read internal resource, located inside the jar
 * by URI and write them to external files.  More to the point, this if for 
 * auto-installing themes, and thus focuses on text processing methods.
 * 
 * (Note that themes will still be installed as external files so as to allow 
 * players to easily edit them without needing to edit the jar.)
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class Externalizer {
	private BufferedReader instream;
	private BufferedWriter outstream;
	private static final   String baseLocation = "/assets/climaticbiomesjbg/BiomeLists/";
	private static final   String outDir       = "BiomeConfig";
	private static final   String readMeFile   = "README.txt";
	
	private static final String[] blists = {
		"AplineDry.cfg",
		"AplineWet.cfg",
		"ChaparralScrub.cfg",
		"DeepOceanCold.cfg",
		"DeepOceanCool.cfg",
		"DeepOceanFrozen.cfg",
		"DeepOceanHot.cfg",
		"DeepOceanWarm.cfg",
		"Desert.cfg",
		"Forest.cfg",
		"ForestCool.cfg",
		"ForestTropical.cfg",
		"ForestWarm.cfg",
		"Jungle.cfg",
		"OceanCold.cfg",
		"OceanCool.cfg",
		"OceanFrozen.cfg",
		"OceanHot.cfg",
		"OceanWarm.cfg",
		"Parkland.cfg",
		"ParklandCool.cfg",
		"Plains.cfg",
		"PlainsCold.cfg",
		"PlainsCool.cfg",
		"Savanna.cfg",
		"SpecialIslandCold.cfg",
		"SpecialIslandCool.cfg",
		"SpecialIslandDesert.cfg",
		"SpecialIslandFrozen.cfg",
		"SpecialIslandTemperate.cfg",
		"SpecialIslandTropical.cfg",
		"SpecialIslandWarm.cfg",
		"SwampCold.cfg",
		"SwampCool.cfg",
		"SwampTropical.cfg",
		"SwampWarm.cfg",
		"Taiga.cfg",
		"Tundra.cfg"
	};
	
	
	private static final String[] subdirs = {
		"Minecraft",
		"BiomeOPlenty",
		"Traverse",
		"custom"
	};
	
	
	/**
	 * Exports a theme file by reading it from the jar and 
	 * writing it to the hard drive, but only if a file with 
	 * that path does not already exist on the drive.
	 * 
	 * @param name
	 */
	public void copyOut(File confdir) {
		File outFile;
		try {
			File listDir = new File(confdir + File.separator + outDir);
			if(!listDir.exists()) {
				listDir.mkdirs();
			}
			outFile = new File(confdir + File.separator + outDir + File.separator + readMeFile);
			if(!outFile.exists()) {
				copyReadMe(outFile);
			}
			copyLists(listDir);
		} catch (Exception e) {
			System.err.println("Error! Failed to write theme README file!");
			e.printStackTrace();
		} 
	}
	
	
	public void copyLists(File listDir)  throws Exception {
		for(String sub : subdirs) {
			File dir = new File(listDir + File.separator + sub + File.separator);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			for(String fname : blists) {
				File file = new File(dir + File.separator + fname);
				if(!file.exists()) {
					System.out.println("Creating file " + file);
					copyBiomeList(file, sub, fname);					
				}
			}
		}		
	}
	
	
	public void copyReadMe(File outFile) throws Exception {
		instream = new BufferedReader(new InputStreamReader(getClass()
				.getResourceAsStream(baseLocation + readMeFile)));
		outstream = new BufferedWriter(new FileWriter(outFile));
		String line;
		if((instream != null) && (outstream != null)) 
			while((line = instream.readLine()) != null) {
				outstream.write(line + System.lineSeparator());
			} else {
				System.err.println("Error! Failed to write theme README file!");
			}
		if(instream  != null) instream.close();
		if(outstream != null) outstream.close();		
	}
	
	
	public void copyBiomeList(File outFile, String dir, String name) throws Exception {
		instream = new BufferedReader(new InputStreamReader(getClass()
				.getResourceAsStream(baseLocation + dir + "/" + name)));
		outstream = new BufferedWriter(new FileWriter(outFile));
		String line;
		if((instream != null) && (outstream != null)) 
			while((line = instream.readLine()) != null) {
				outstream.write(line + System.lineSeparator());
			} else {
				System.err.println("Error! Failed to write theme README file!");
			}
		if(instream  != null) instream.close();
		if(outstream != null) outstream.close();		
	}
	

}
