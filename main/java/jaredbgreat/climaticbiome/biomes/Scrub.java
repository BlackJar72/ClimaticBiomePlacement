package jaredbgreat.climaticbiome.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.biome.BiomeTaiga;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenShrub;

public class Scrub extends Biome {
	private static final WorldGenBlockBlob ROCK_PILES = new WorldGenBlockBlob(Blocks.COBBLESTONE, 0);
	private static final IBlockState SPRUCE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, 
			BlockPlanks.EnumType.SPRUCE);    
	private static final IBlockState OAK_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, 
			BlockPlanks.EnumType.OAK);    
	private static final IBlockState SPRUCE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, 
			BlockPlanks.EnumType.SPRUCE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, 
			BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));    
	private final Type type;
	
	public static enum Type {
		DENSE (Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, 
				BlockDirt.DirtType.COARSE_DIRT)),
		DRY (Blocks.SAND.getDefaultState());
		public final IBlockState altTop;
		Type(IBlockState block) {
			altTop = block;
		}
	}

	public Scrub(Type type, BiomeProperties properties) {
		super(properties);
		this.type = type;
		theBiomeDecorator.deadBushPerChunk = 5;
		spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
		if(type == Type.DRY) {
			setupDry();
		} else {
			setupDense();
		}
	}
	
	
	private void setupDense() {
		theBiomeDecorator.treesPerChunk = 3;
        theBiomeDecorator.grassPerChunk = 6;
	}
	
	
	private void setupDry() {
        theBiomeDecorator.cactiPerChunk = 15;
		theBiomeDecorator.treesPerChunk = 1;
        theBiomeDecorator.grassPerChunk = 2;
	}	


    public WorldGenAbstractTree genBigTreeChance(Random random) {
    	if((type != Type.DRY) && random.nextInt(5) == 0) {
            return TREE_FEATURE;
    	}
    	else {
    		IBlockState a, b;
    		if(random.nextBoolean()) {
    			a = OAK_LOG;
    		} else {
    			a = SPRUCE_LOG;
    		}
    		if(random.nextBoolean()) {
    			b = OAK_LEAF;
    		} else {
    			b = SPRUCE_LEAF;
    		}
    		return new WorldGenShrub(a, b);
    	}
    }
	

    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, 
    		int x, int z, double noise) {
        fillerBlock = Blocks.DIRT.getDefaultState();
        if (noise > 1.25) {
        	this.topBlock = type.altTop;
        } else {
        	topBlock = Blocks.GRASS.getDefaultState();
        }
        generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noise);
    }

    public void decorate(World worldIn, Random random, BlockPos pos) {
        int n;
    	if(type == Type.DRY) {
    		n = random.nextInt(4);
    	} else {
    		n = random.nextInt(2);
    	}
    	for (int i = 0; i < n; i++) {
    		BlockPos blockpos = worldIn.getHeight(pos.add(random.nextInt(16) + 8, 0, random.nextInt(16) + 8));
            ROCK_PILES.generate(worldIn, random, blockpos);
        }

        super.decorate(worldIn, random, pos);
    }

}
