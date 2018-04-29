package jaredbgreat.climaticbiome.blocks.items;

import jaredbgreat.climaticbiome.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IPlantable;

public class ItemSapling extends RegisteringItemBlock {
    
    
    public ItemSapling(Block block) {
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
			IBlockState plant = ModBlocks.pineSapling.getDefaultState();
			world.setBlockState(pos.up(), plant);
			if(!player.isCreative()) {
				ItemStack stack = player.getActiveItemStack(); 
				stack.setCount(stack.getCount() - 1);
			}
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

}
