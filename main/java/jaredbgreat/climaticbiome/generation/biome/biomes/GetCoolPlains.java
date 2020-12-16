package jaredbgreat.climaticbiome.generation.biome.biomes;

import jaredbgreat.climaticbiome.compat.userdef.DefReader;
import jaredbgreat.climaticbiome.generation.biome.BiomeList;
import jaredbgreat.climaticbiome.generation.biome.IBiomeSpecifier;
import jaredbgreat.climaticbiome.generation.biome.LeafBiome;
import jaredbgreat.climaticbiome.generation.mapgenerator.ChunkTile;

/**
 * This specifer is specifically for words using biome mods that 
 * imply a cool temperate band between the taiga zone and the 
 * basic temperate zone.
 * 
 * @author JaredBGreat (Jared Blackburn)
 */
public class GetCoolPlains implements IBiomeSpecifier  {
	private static GetCoolPlains grassland;
	private GetCoolPlains() {
		super();
		init();
	}
	private BiomeList plains;
	
	
	public void init() {
		plains = new BiomeList();
		DefReader.readBiomeData(plains, "PlainsCool.cfg");
		if(plains.isEmpty()) {
			plains.addItem(new LeafBiome(1));
		}
	}
	
	
	@Override
	public long getBiome(ChunkTile tile) {
		int seed = tile.getBiomeSeed();
		tile.nextBiomeSeed();
		return plains.getBiome(tile);
	}
	
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	BiomeList getList() {
		return plains;
	}
	
	
	public static GetCoolPlains getPlains() {
		if(grassland == null) {
			grassland = new GetCoolPlains();
		}
		return grassland;
	}

}
