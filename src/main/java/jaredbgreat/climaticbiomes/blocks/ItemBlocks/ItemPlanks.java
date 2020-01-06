package jaredbgreat.climaticbiomes.blocks.ItemBlocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemPlanks extends BlockItem {

    public ItemPlanks(Block block) {
        super(block, makeProperties(block));
        this.setRegistryName(block.getRegistryName());
    }


    @Override
    public int getBurnTime(ItemStack stack) {
        return 300;
    }


    private static Item.Properties makeProperties(Block block) {
        Item.Properties out = new Item.Properties();
        out.group(ItemGroup.BUILDING_BLOCKS);
        return out;
    }
}
