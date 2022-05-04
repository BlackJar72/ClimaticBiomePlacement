package jaredbgreat.climaticbiome.biomes.basic;

import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.IPineFinder;
import jaredbgreat.climaticbiome.biomes.basic.Pinewoods.SpruceFinder;

import java.util.Random;

import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;

public class CoolPlains extends BiomePlains {
	private static final IBlockState SPRUCE_LOG = Blocks.LOG.getDefaultState()
			.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);    
	private static final IBlockState SPRUCE_LEAF = Blocks.LEAVES.getDefaultState()
			.withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
	private final IPineFinder SPRUCE;
	private final boolean cold;

	public CoolPlains(boolean p_i46699_1_, boolean cold, BiomeProperties properties) {
		super(p_i46699_1_, properties);
		SPRUCE = new SpruceFinder();
		this.cold = cold;
	}
	
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		if(cold) {
			return new WorldGenShrub(SPRUCE_LOG, SPRUCE_LEAF);
		} else if(rand.nextBoolean()) {
			return SPRUCE.getTree(rand);
		} else {
			return super.getRandomTreeFeature(rand);
		}
	}
	

}
