package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.IPineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.SpruceFinder;
import jaredbgreat.climaticbiome.biomes.feature.ScrubBushFinder;

import java.util.Random;

import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;

public class Wetland extends BiomeSwamp {	
    protected static final WorldGenBirchTree BIRCH_TREE 
    	= new WorldGenBirchTree(false, false);
    protected final IPineFinder SPRUCE;
	public static enum Type {
		MARSH (0),
		BOG (1),
		CARR (4);
		
		public final int numTrees;
		Type(int numTrees) {
			this.numTrees = numTrees;
		}
	}
	public final Type type;
	
	
	
	protected Wetland(Type type, BiomeProperties properties) {
		super(properties);
		this.type = type;
		SPRUCE = new SpruceFinder();
		decorator.treesPerChunk = type.numTrees;
	}
	
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		switch(type) {
		case BOG:
			return getBogTree(rand);
		case CARR:
			return getCarrTree(rand);
		case MARSH:
			return getMarshTree(rand);
		default:
			// This should never happen
			return TREE_FEATURE;		
		}
	}
	
	
	private WorldGenAbstractTree getCarrTree(Random rand) {
		if(rand.nextBoolean()) {
			return SPRUCE.getTree(rand);
		} else {
			return BIRCH_TREE;
		}
	}
	
	
	private WorldGenAbstractTree getMarshTree(Random rand) {
		return TREE_FEATURE;
	}
	
	
	private WorldGenAbstractTree getBogTree(Random rand) {
		return ScrubBushFinder.finder.getBush(rand);
	}

}
