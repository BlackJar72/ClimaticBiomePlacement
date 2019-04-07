package jaredbgreat.climaticbiome;

public class ClimaticWorldSettings {
	
	// Core settings
	// NOTE: Some of these may not actually be used (i.e., but configured per world)!!!
	public boolean useBoP;
	public boolean useTraverse;
	public boolean useVanilla;	
	public boolean useBoPTable;	
	public boolean volcanicBoP;
	public boolean useCfg;
	public boolean rivers;
	public boolean rockyScrub;
	public boolean hasDT;
	public boolean useDT;
	public boolean addIslands;
	public boolean addBeaches;
	public boolean moreMansion;
	public boolean addPines;
	public boolean deepSand;
	
	
	/**
	 * This default constructor will create a version that matches 
	 * set in the global config file (treating them as run-time defaults).
	 */
	public ClimaticWorldSettings() {
		this.useBoP = ConfigHandler.useBoP;
		this.useTraverse = ConfigHandler.useTraverse;
		this.useVanilla = ConfigHandler.useVanilla;	
		this.useBoPTable = ConfigHandler.useBoPTable;	
		this.volcanicBoP = ConfigHandler.volcanicBoP;
		this.useCfg = ConfigHandler.useCfg;
		this.rivers = ConfigHandler.rivers;
		this.rockyScrub = ConfigHandler.rockyScrub;
		this.hasDT = ConfigHandler.hasDT;
		this.useDT = ConfigHandler.useDT;
		this.addIslands = ConfigHandler.addIslands;
		this.addBeaches = ConfigHandler.addBeaches;
		this.moreMansion = ConfigHandler.moreMansion;
		this.deepSand = ConfigHandler.deepSand;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*-*****************************************************-*/
	/*-***************** Factory Inner Class ***************-*/
	/*-*****************************************************-*/
	
	public static class SettingFactory {
		
	}

}
