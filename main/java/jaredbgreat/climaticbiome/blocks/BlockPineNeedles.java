package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineNeedles;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPineNeedles extends BlockLeafBase {

	public BlockPineNeedles() {
		setUnlocalizedName(Info.ID + ".leaves_pine");
		setRegistryName(Info.ID + ":leaves_pine");
		setGraphicsLevel(Minecraft.getMinecraft().isFancyGraphicsEnabled());
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemBlock(this)
			.setRegistryName(getRegistryName()));
		ItemRegistrar.addItem(new ItemPineNeedles(this)
			.setRegistryName(getRegistryName()));
	}
	

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world,
			BlockPos pos, int fortune) {
		return Arrays.asList(new ItemStack(this, 1, 0));
	}

	
	@Override
	public EnumType getWoodType(int meta) {
		return BlockPlanks.EnumType.byMetadata(0);
	}
	
	
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockRegistrar.pineSapling);
    }

	
    public int getMetaFromState(IBlockState state) {
        int out = 0;
        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue()) {
            out |= 4;
        }
        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) {
            out |= 8;
        }
        return out;
    }

    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
        		.withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
        		.withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }
    

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    
    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
    	return true;
    }
}
