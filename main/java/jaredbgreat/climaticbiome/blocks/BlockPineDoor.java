package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.ClimaticBiomes;
import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.IHaveModel;
import jaredbgreat.climaticbiome.util.ItemRegistrar;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPineDoor extends BlockDoor implements IHaveModel {
	private Item item;

	public BlockPineDoor(String name) {
		super(Material.WOOD);
		setRegistryName(new ResourceLocation(Info.ID, name));
		setUnlocalizedName(Info.ID + "." + name);
		setCreativeTab(CreativeTabs.REDSTONE);
		setHardness(3F);
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(OPEN, Boolean.valueOf(false))
				.withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT)
				.withProperty(POWERED, Boolean.valueOf(false))
				.withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
		Item item = new ItemDoor(this);
		item.setRegistryName(this.getRegistryName());
		item.setUnlocalizedName(this.getUnlocalizedName());
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(item);		
	}
	

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER 
				? Items.AIR : item;
	}

	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(item);
	}

	
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.getMaterial().getMaterialMapColor();
	}
	

	@Override
	public void registerModel() {
		ClimaticBiomes.proxy.registerDoorRenders(this);
	}

}
