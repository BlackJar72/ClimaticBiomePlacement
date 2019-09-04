package jaredbgreat.climaticbiome.blocks.itemblocks;

import jaredbgreat.climaticbiome.Info;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPineSlab extends ItemSlab {
	Block doubleSlab;

	public ItemPineSlab(Block block, BlockSlab singleSlab, BlockSlab doubleSlab) {
		super(block, singleSlab, doubleSlab);
		this.doubleSlab = doubleSlab;
        setUnlocalizedName(Info.ID + ".pine_slab");
        setRegistryName(block.getRegistryName());
	}
    
    
    @Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;    	
    }


	private boolean func_180615_a(ItemStack p_180615_1_, World worldIn, BlockPos p_180615_3_) {
		IBlockState iblockstate = worldIn.getBlockState(p_180615_3_);

		if (iblockstate.getBlock() == getBlock()) {
			IBlockState iblockstate1 = this.doubleSlab.getDefaultState();

			if (worldIn.checkNoEntityCollision(this.doubleSlab.getBoundingBox(iblockstate1, worldIn, p_180615_3_))
					&& worldIn.setBlockState(p_180615_3_, iblockstate1, 3)) {
				worldIn.playSound(p_180615_3_.getX() + 0.5F, p_180615_3_.getY() + 0.5F, p_180615_3_.getZ() + 0.5F,
						doubleSlab.getSoundType().getPlaceSound(), SoundCategory.BLOCKS,
						(doubleSlab.getSoundType().getVolume() + 1.0F) / 2.0F,
						doubleSlab.getSoundType().getPitch() * 0.8F, true);
				p_180615_1_.shrink(1);
			}

			return true;
		}

		return false;
	}


	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (stack.getCount() == 0) {
			return EnumActionResult.FAIL;
		} else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
			return EnumActionResult.FAIL;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);

			if (iblockstate.getBlock() == getBlock()) {
				BlockSlab.EnumBlockHalf enumblockhalf = iblockstate.getValue(BlockSlab.HALF);

				if ((side == EnumFacing.UP && enumblockhalf == BlockSlab.EnumBlockHalf.BOTTOM
						|| side == EnumFacing.DOWN && enumblockhalf == BlockSlab.EnumBlockHalf.TOP)) {
					IBlockState iblockstate1 = this.doubleSlab.getDefaultState();

					if (worldIn.checkNoEntityCollision(this.doubleSlab.getBoundingBox(iblockstate1, worldIn, pos))
							&& worldIn.setBlockState(pos, iblockstate1, 3)) {
						worldIn.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F,
								this.doubleSlab.getSoundType().getPlaceSound(), SoundCategory.BLOCKS,
								(this.doubleSlab.getSoundType().getVolume() + 1.0F) / 2.0F,
								this.doubleSlab.getSoundType().getPitch() * 0.8F, true);
						stack.shrink(1);
					}

					return EnumActionResult.SUCCESS;
				}
			}

			return (this.func_180615_a(stack, worldIn, pos.offset(side)) || (super.onItemUse(playerIn, worldIn, pos,
					hand, side, hitX, hitY, hitZ) == EnumActionResult.SUCCESS ? true : false))
							? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
		}
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing dir,
			EntityPlayer player, ItemStack stack) {
		BlockPos blockpos1 = pos;
		IBlockState iblockstate = world.getBlockState(pos);

		if (iblockstate.getBlock() == getBlock()) {
			boolean flag = iblockstate.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;

			if ((dir == EnumFacing.UP && !flag || dir == EnumFacing.DOWN && flag)) {
				return true;
			}
		}

		pos = pos.offset(dir);
		IBlockState iblockstate1 = world.getBlockState(pos);
		return iblockstate1.getBlock() == getBlock()
				|| super.canPlaceBlockOnSide(world, blockpos1, dir, player, stack);
	}

    
    
    

}
