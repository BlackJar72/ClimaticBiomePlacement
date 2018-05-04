package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;

import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;


public class Sapling extends BlockBush implements IGrowable {
	private final WorldGenAbstractTree tree;
	
	
	public Sapling(String name, WorldGenAbstractTree generator) {
		super();
		setUnlocalizedName(name);
		setRegistryName(Info.ID + ":" + name);
		setSoundType(SoundType.PLANT);
		tree = generator;
	}
	

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state,
			boolean isClient) {
		return true;
	}
    
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    	super.updateTick(world, pos, state, rand);;
    	if((world.getLight(pos) > 8) && (rand.nextInt(9) == 0)) {
    		grow(world, rand, pos, state);
    	}
    }
	

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos,
			IBlockState state) {
		return true;
	}
	

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
		tree.generate(world, rand, pos);
	}
	
	
	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return true;
	}

}
