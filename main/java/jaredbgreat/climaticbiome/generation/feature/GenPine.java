package jaredbgreat.climaticbiome.generation.feature;

import static net.minecraft.util.EnumFacing.UP;
import jaredbgreat.climaticbiome.util.BlockRegistrar;

import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

public class GenPine extends WorldGenAbstractTree {
	
    private static final IBlockState TRUNK = BlockRegistrar.blockPineLog.getDefaultState();
    private static final IBlockState LEAF = BlockRegistrar.blockPineNeedles.getDefaultState()
    		.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    

	public GenPine() {
		super(false);
	}
	

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if(world.isRemote) return false;
		int j1 = 4 + rand.nextInt(3) + rand.nextInt(2); // trunk height
		int j2 = Math.min(j1 -1 , 2 + rand.nextInt(1 + (j1 / 2))); // first leaf height
		
		if((pos.getY() < 1) || ((pos.getY() + j1 + 3) > 255)) { 
			return false;
		} else {
			int x = pos.getX();
			int z = pos.getZ();
			IBlockState under = world.getBlockState(pos.down());
			if(!under.getBlock().canSustainPlant(under, world, pos.down(), UP, 
					(IPlantable) Blocks.SAPLING)) {
				return false; 
			} 

			BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
			for(int j3 = pos.getY(); j3 < (pos.getY() + j1 +1); j3++) {
				if(!isReplaceable(world, mutable.setPos(x, j3, z))) {
					return false;
				}
			}
			
			int w = 1;
			int bonus = rand.nextInt(2);
			int j1b = pos.getY() + j1 + 1;
			for(int j4 = pos.getY() + j2; j4 < j1b; j4++) {
				w = 1 + ((j4 + bonus) % 2);
				for(int i2 = x - w; i2 <= (x + w); i2++) 
					for(int k2 = z - w; k2 <= (z + w); k2++) {
						BlockPos place = new BlockPos(i2, j4, k2);
						IBlockState state = world.getBlockState(place);								
						if (state.getBlock().canBeReplacedByLeaves(state, world, place)) {
							//world.setBlockState(place, LEAF);
							setBlockAndNotifyAdequately(world, place, LEAF);
						}								
					}
			}
			
			w = 1 + ((j1b + bonus) % 2);
			for(int j4 = j1b; w > -1; j4++, w--) {
				for(int i2 = x - w; i2 <= (x + w); i2++) 
					for(int k2 = z - w; k2 <= (z + w); k2++) {
						BlockPos place = new BlockPos(i2, j4, k2);
						IBlockState state = world.getBlockState(place);
						if (state.getBlock().canBeReplacedByLeaves(state, world, place)) {
							//world.setBlockState(place, LEAF);
							this.setBlockAndNotifyAdequately(world, place, LEAF);
						}								
					}
			}
			
			for(int j4 = pos.getY(); j4 < j1b; j4++) {
				BlockPos place = new BlockPos(x, j4, z);
				//world.setBlockState(place, TRUNK);
				IBlockState state = world.getBlockState(place);				
				setBlockAndNotifyAdequately(world, place, TRUNK);
			}
				
		}
		return true;
	}
}
