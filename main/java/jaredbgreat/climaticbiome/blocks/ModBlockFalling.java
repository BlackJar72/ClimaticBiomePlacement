package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineLog;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.IHaveModel;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlockFalling extends BlockFalling /*implements IHaveModel*/ {
	public final String name;
	
	
	public ModBlockFalling(String name, SoundType sound) {
		this.name = name;
		setSoundType(sound);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setRegistryName(Info.ID, name);
        setUnlocalizedName(Info.ID + "." + name);
        setHardness(0.5f);
        setHarvestLevel("shovel", 0);
        setResistance(5f);
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.STONE;
    }

    
    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state) {
        return -8356741;
    }

}
