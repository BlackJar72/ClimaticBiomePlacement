package jaredbgreat.climaticbiome.blocks.itemblocks;

import jaredbgreat.climaticbiome.util.BlockRegistrar;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class ItemPineSappling extends ItemBlock {
    
    
    public ItemPineSappling(Block block) {
		super(block);
        setMaxDamage(0);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());	
		setCreativeTab(CreativeTabs.DECORATIONS);
	}
    

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, 
			BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState ground = world.getBlockState(pos);
		if(ground.getBlock().canSustainPlant(ground, world, pos.down(), EnumFacing.UP, 
				(IPlantable) Blocks.SAPLING)) {
			IBlockState plant = BlockRegistrar.blockPineSappling.getDefaultState();
			world.setBlockState(pos.up(), plant);
			if(!player.isCreative()) {
				ItemStack stack = player.getHeldItem(hand); 
				stack.setCount(stack.getCount() - 1);
			}
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
    
    
    @Override
	public int getItemBurnTime(ItemStack stack) {
		return 100;    	
    }

}
