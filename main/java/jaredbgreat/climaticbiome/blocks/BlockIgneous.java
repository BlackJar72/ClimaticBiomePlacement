package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemMultiblock;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockIgneous extends ModBlockBase {
	private final static String NAME = "igneous";
    private final String[] names;
    public static final PropertyInteger KIND = PropertyInteger.create("type", 0, 15);
    public final ItemNamer itemNamer;
    
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {KIND});        
    }
    
    public static class ItemNamer implements ItemMultiTexture.Mapper {    	
		@Override
		public String apply(ItemStack var1) {
			return NAME + var1.getItemDamage();
		}    	
    }

	public BlockIgneous() {
		super(Material.ROCK);
        setDefaultState(this.blockState.getBaseState().withProperty(KIND, 0));
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setRegistryName(Info.ID, NAME);
        setUnlocalizedName(Info.ID + "." + NAME);
        names = new String[16];
        for(int i = 0; i < 16; i++) {
        	names[i] = Info.ID + "." + NAME + i;
        }
        itemNamer = new ItemNamer();
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemMultiblock(this, 16, itemNamer));
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(KIND, meta);
	}

	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(KIND).intValue();
	}
	

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(KIND).intValue();
    }

    
	@Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i = 0; i < 16; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }
	
	
	
	
}
