package jaredbgreat.climaticbiomes.blocks.ItemBlocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemFuelBlock extends BlockItem {
    private final int burnTime;

    public ItemFuelBlock(Block block, int burnTime) {
        super(block, makeProperties(block));
        this.setRegistryName(block.getRegistryName());
        this.burnTime = burnTime;
    }


    @Override
    public int getBurnTime(ItemStack stack) {
        return burnTime;
    }


    private static Item.Properties makeProperties(Block block) {
        Item.Properties out = new Item.Properties();
        out.group(ItemGroup.BUILDING_BLOCKS);
        return out;
    }
}
