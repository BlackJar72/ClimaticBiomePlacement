package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.item.ItemGroup.BUILDING_BLOCKS;

public final class ItemRegistrar {
    private static List<BlockStore> blocks = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();


    private static final class BlockStore {
        public final Block block;
        public final ItemGroup group;
        BlockStore(Block block, ItemGroup group) {
            this.block = block;
            this.group = group;
        }
        public BlockItem makeItem() {
            BlockItem out = new BlockItem(block, new Item.Properties().group(group));
            out.setRegistryName(block.getRegistryName());
            return out;
        }
    }

    // List of Items


    public static void registerItems(final RegistryEvent.Register<Item> event) {
        ClimaticBiomes.getLogger().info("Registering Items for Climatic Biomes");
        for(BlockStore block : blocks) {
            items.add(block.makeItem());
        }
        for(Item item : items) {
            event.getRegistry().register(item);
        }
    }


    static void addItemBlock(Block block, ItemGroup group) {
        blocks.add(new BlockStore(block, group));
    }


    static void addItem(Item item) { items.add(item); }


}
