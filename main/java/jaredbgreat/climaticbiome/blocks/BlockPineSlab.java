package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineLog;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;

import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPineSlab extends BlockSlabBase {
    public static final PropertyEnum<EnumWoodType> TYPE 
    	= PropertyEnum.<EnumWoodType>create("variant", EnumWoodType.class);
    private final boolean beDouble;

	public BlockPineSlab(String name, boolean beDouble) {
		super(Material.WOOD);
		this.beDouble = beDouble;
		setUnlocalizedName(Info.ID + "." + name);
		setRegistryName(Info.ID + ":" + name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0f);
		setHarvestLevel("axe", 0);
		setResistance(1.0f);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        IBlockState iblockstate = this.blockState.getBaseState();
        if(beDouble) {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }
        setDefaultState(iblockstate);
		BlockRegistrar.addBlock(this);
    }
	
	
	@Override
	public boolean getUseNeighborBrightness(IBlockState state) {
		return beDouble;
	}
	
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return beDouble;
	}
	
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return beDouble;
	}

	
	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}
	

	@Override
	public IProperty<?> getVariantProperty() {
		return HALF;
	}

	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return EnumBlockHalf.BOTTOM;
	}
	
	
	@Override
	public boolean isDouble() {
		return beDouble;
	}


    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.WOOD;
        //return ((EnumWoodType)state.getValue(TYPE)).getMapColor();
    }


    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this, 1, state.getValue(TYPE).getMetadata());
    }


    public int damageDropped(IBlockState state) {
        return 0;
    }


    protected BlockStateContainer createBlockState() {
    	return new BlockStateContainer(this, new IProperty[] {HALF});
    }


    public int getMetaFromState(IBlockState state) {
        if (beDouble) {
            return 0;
        }
        return ((EnumBlockHalf)state.getValue(HALF)).ordinal() & 0x1;
    }


    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();
        if(!beDouble) {
            iblockstate = iblockstate.withProperty(HALF, ((meta + 1) & 0x1) == 0 
            		? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }
        return iblockstate;
    }
    

    @SideOnly(Side.CLIENT)
    protected static boolean isHalfSlab(IBlockState state) {
        return state.getBlock() == BlockRegistrar.pineHalfSlab;
    }

}
