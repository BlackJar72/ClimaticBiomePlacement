package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.IPineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.SpruceFinder;
import jaredbgreat.climaticbiome.biomes.feature.ScrubBushFinder;

import java.util.Random;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;

public class Wetland extends BiomeSwamp {	
    protected static final WorldGenBirchTree BIRCH_TREE 
    	= new WorldGenBirchTree(false, false);
    protected final IPineFinder SPRUCE;
	public static enum Type {
		MARSH (0, 12, 0x25BB3C, 0x38983C),
		BOG (2, 10, 0x4C763C, 0x6A7039),
		CARR (6, 5, 0x4C763C, 0x6A7039);		
		public final int numTrees;		
		public final int numGrass;
		public final int grassA;
		public final int grassB;
		Type(int numTrees, int numGrass, int a, int b) {
			this.numTrees = numTrees;
			this.numGrass = numGrass;
			this.grassA = a;
			this.grassB = b;
		}
	}
	public final Type type;
	
	
	
	public Wetland(Type type, BiomeProperties properties) {
		super(properties);
		this.type = type;
		SPRUCE = new SpruceFinder();
		decorator.treesPerChunk = type.numTrees;
		decorator.grassPerChunk = type.numGrass;
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
		if(rand.nextBoolean()) {
			return SWAMP_FEATURE;
		} else {
			return TREE_FEATURE;
		}
	}
	
	
	private WorldGenAbstractTree getBogTree(Random rand) {
		return ScrubBushFinder.finder.getBush(rand);
	}
	
	
	@Override
	public void decorate(World world, Random rand, BlockPos pos) {
		super.decorate(world, rand, pos);
		switch(type) {
		case BOG:
			decorateBog(world, rand, pos);
			break;
		case CARR:
			decorateCarr(world, rand, pos);
			break;
		case MARSH:
			decorateMarsh(world, rand, pos);
			break;
		default:
			break;
		}
	}
	
	
	private void decorateBog(World world, Random rand, BlockPos pos) {
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);
		int n = rand.nextInt(5) + 8;
		for (int i = 0; i < n; ++i) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y;
            if(rand.nextBoolean()) {
            	y = rand.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
            } else {
            	y = world.getHeight(pos.add(x, 0, z)).getY();
            }
            DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(x, y, z));
        }
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);
		n = rand.nextInt(5) - 3;
		for (int i = 0; i < n; ++i) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y;
            if(rand.nextBoolean()) {
            	y = rand.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
            } else {
            	y = world.getHeight(pos.add(x, 0, z)).getY();
            }
            DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(x, y, z));
        }
	}
	
	
	private void decorateCarr(World world, Random rand, BlockPos pos) {
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);
		int n = rand.nextInt(4) - 1;
		for (int i = 0; i < n; ++i) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y;
            if(rand.nextBoolean()) {
            	y = rand.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
            } else {
            	y = world.getHeight(pos.add(x, 0, z)).getY();
            }
            DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(x, y, z));
        }
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.SYRINGA);
		n = rand.nextInt(4) - 2;
		for (int i = 0; i < n; ++i) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y;
            if(rand.nextBoolean()) {
            	y = rand.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
            } else {
            	y = world.getHeight(pos.add(x, 0, z)).getY();
            }
            DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(x, y, z));
        }
	
	}
	
	
	private void decorateMarsh(World world, Random rand, BlockPos pos) {
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);
		int n = rand.nextInt(5) + 8;
		for (int i = 0; i < n; ++i) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int y;
            if(rand.nextBoolean()) {
            	y = rand.nextInt(world.getHeight(pos.add(x, 0, z)).getY() + 32);
            } else {
            	y = world.getHeight(pos.add(x, 0, z)).getY();
            }
            DOUBLE_PLANT_GENERATOR.generate(world, rand, pos.add(x, y, z));
        }
	}
	
	
    public int getGrassColorAtPos(BlockPos pos) {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225, 
        		(double)pos.getZ() * 0.0225);
        return getModdedBiomeGrassColor(d0 < -0.1 ? type.grassA :type.grassB);
    }
	
}
