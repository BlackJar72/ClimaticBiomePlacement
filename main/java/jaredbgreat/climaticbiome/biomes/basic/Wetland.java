package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.IPineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.SpruceFinder;
import jaredbgreat.climaticbiome.biomes.feature.ScrubBushFinder;
import jaredbgreat.climaticbiome.configuration.ConfigHandler;
import jaredbgreat.climaticbiome.util.BlockRegistrar;

import java.util.Random;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBirchTree;
import net.minecraftforge.event.terraingen.BiomeEvent.GetWaterColor;

public class Wetland extends BiomeSwamp {
	private int wcolor;
	private static IBlockState PEAT;
	private static final IBlockState GRASS = Blocks.GRASS.getDefaultState();
    protected static final WorldGenBirchTree BIRCH_TREE 
    	= new WorldGenBirchTree(false, false);
    protected final IPineFinder SPRUCE;
	public static enum Type {
		MARSH (0, 12, 0x25BB3C, 0x38983C, 0x6A7039, 0xffffff),
		BOG (3, 10, 0x4C763C, 0x6A7039, 0x4C863C, 0x5d5719),
		CARR (8, 5, 0x4C763C, 0x6A7039, 0x6A8039, 0x938f52);		
		public final int numTrees;		
		public final int numGrass;
		public final int grassA;
		public final int grassB;
		public final int leaves;
		public final int wcolor;
		Type(int numTrees, int numGrass, int a, int b, int l, int w) {
			this.numTrees = numTrees;
			this.numGrass = numGrass;
			this.grassA = a;
			this.grassB = b;
			this.leaves = l;
			this.wcolor = w;
		}
	}
	public final Type type;
	
	
	
	public Wetland(Type type, BiomeProperties properties) {
		super(properties);
		this.type = type;
		SPRUCE = new SpruceFinder();
		decorator.treesPerChunk = type.numTrees;
		decorator.grassPerChunk = type.numGrass;
		if(ConfigHandler.peatBlocks) {
			PEAT = BlockRegistrar.blockPeat.getDefaultState();
		} else {
			PEAT = Blocks.DIRT.getDefaultState();			
		}
		if(ConfigHandler.biomeWater) {
			wcolor = type.wcolor;
		} else {
			wcolor = 0xffffff;
		}
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
		if(rand.nextInt(5) == 0) {
			for(int i = rand.nextInt(32) + rand.nextInt(32) + 32; i > 0; i--) {
				int x = rand.nextInt(16) + 8;
				int z = rand.nextInt(16) + 8;
            	int y = world.getHeight(pos.add(x, 0, z)).getY() - 1;
            	BlockPos tpos = pos.add(x, y, z);
            	if(world.getBlockState(tpos).getMaterial() == Material.GRASS) {
            		world.setBlockState(tpos, PEAT);
            	}
			}
		}
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);
		int n = rand.nextInt(5) + 4;
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
        return getModdedBiomeGrassColor(d0 < -0.1 ? type.grassA : type.grassB);
    }
    
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos) {
    	return getModdedBiomeFoliageColor(type.leaves);
    }
	

    public void genTerrainBlocks(World world, Random rand, ChunkPrimer chunkPrimer, 
    			int x, int z, double noise) {
    	if(type == Type.BOG && ((noise > 1.75) || (noise < -1.75))) {
    		fillerBlock = PEAT;
    	}
    	super.genTerrainBlocks(world, rand, chunkPrimer, x, z, noise);
    }
    
    
    @Override
    public boolean isHighHumidity() {
    	return true;
    }
    
    
    @Override
    public int getWaterColorMultiplier() {
    	return wcolor;
    }
    
	
}
