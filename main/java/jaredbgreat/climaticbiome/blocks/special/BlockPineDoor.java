package jaredbgreat.climaticbiome.blocks.special;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPineDoor extends BlockDoorBase {
	ItemBlock item;

	public BlockPineDoor() {
		super(Material.WOOD);
		setHardness(3.0F);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(Info.ID + ".pine_door");
		setRegistryName(Info.ID, "pine_door");
		disableStats();
        setCreativeTab(CreativeTabs.REDSTONE);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)
				.withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT)
				.withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
		item = (ItemBlock) new ItemBlock(this).setRegistryName(getRegistryName());
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(item);
		
	}
	

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : this.getItem();
	}

	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this.getItem());
	}

	
	private Item getItem() {
		return this.item;
	}

	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(BlockDoor.POWERED).build());

		ModelLoader.setCustomModelResourceLocation(this.getItem(), 0,
				new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}
