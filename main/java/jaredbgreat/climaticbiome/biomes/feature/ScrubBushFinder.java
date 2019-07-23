package jaredbgreat.climaticbiome.biomes.feature;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.biomes.basic.Scrub.Type;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

public class ScrubBushFinder {
	public static final ScrubBushFinder finder = new ScrubBushFinder();
	private static final IBlockState SPRUCE_LOG = Blocks.LOG.getDefaultState()
			.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);    
	private static final IBlockState OAK_LOG = Blocks.LOG.getDefaultState()
			.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK);    
	private static final IBlockState SPRUCE_LEAF = Blocks.LEAVES.getDefaultState()
			.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE)
			.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState()
			.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
			.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)); 
	
    public WorldGenAbstractTree getBush(Random random) {
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
