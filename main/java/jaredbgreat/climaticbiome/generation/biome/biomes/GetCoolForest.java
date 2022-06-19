package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.biome.SeedDoubleBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;


/**
 * This specifer is specifically for words using biome mods that 
 * imply a cool temperate band between the taiga zone and the 
 * basic temperate zone.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class GetCoolForest implements IBiomeSpecifier {
	private static GetCoolForest cforest;
	private GetCoolForest() {
		super();
		init();
	}
	private BiomeList forests;
	private GetCoolPlains plains;
	private GetSwamp swamp;
	
	
	public void init() {
		forests = new BiomeList();
		plains  = GetCoolPlains.getPlains();
		swamp   = GetSwamp.getSwamp();		
		DefReader.readBiomeData(forests, "ForestCool.cfg");
		if(forests.isEmpty()) {
			forests.addItem(new SeedDoubleBiome(18, 3, 4), 3);
			forests.addItem(new LeafBiome(132), 1);
			forests.addItem(new SeedDoubleBiome(27, 4, 28), 1);
			forests.addItem(new SeedDoubleBiome(155, 5, 27), 1);
			forests.addItem(new SeedDoubleBiome(157, 7, 29), 2);	
		}
	}
	
	
	@Override
	public long getBiome(ChunkTile tile) {
		int role2 = tile.getBiomeSeed() % 7;
		int role3 = tile.getBiomeSeed() % 12;
		tile.nextBiomeSeed();
		if((role2) == 0) {
			return swamp.getBiome(tile);
		}
		if((role3) == 0) {
			return plains.getBiome(tile);
		}
		return forests.getBiome(tile);
	}
	
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	
	BiomeList getList() {
		return forests;
	}
	
	
	public static GetCoolForest getForest() {
		if(cforest == null) {
			cforest = new GetCoolForest();
		}
		return cforest;
	}

}
