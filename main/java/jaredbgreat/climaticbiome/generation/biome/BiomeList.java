package jaredbgreat.climaticbiome.generation.biome;

import jaredbgreat.climaticbiome.generation.generator.ChunkTile;

import java.util.ArrayList;
import java.util.List;

public class BiomeList implements IBiomeSpecifier {
	private final List<IBiomeSpecifier> list;
	
	
	public BiomeList() {
		list = new ArrayList<>();
	}
	

	@Override
	public int getBiome(ChunkTile tile) {
		tile.nextBiomeSeed();
		return list.get(tile.getBiomeSeed() % list.size())
				.getBiome(tile.nextBiomeSeed());
	}
	
	
	public void addItem(IBiomeSpecifier biome, int n) {
		for(int i = 0; i < n; i++) {
			list.add(biome);
		}
	}
	
	
	public void addItem(IBiomeSpecifier biome) {
		list.add(biome);
	}
	
	
	public void addItems(IBiomeSpecifier... biomes) {
		for(IBiomeSpecifier biome : biomes) {
			list.add(biome);
		}
	}
	
	
	public void addItems(List<IBiomeSpecifier> biomes) {
		list.addAll(biomes);
	}
	
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	
	public int size() {
		return list.size();
	}

}
