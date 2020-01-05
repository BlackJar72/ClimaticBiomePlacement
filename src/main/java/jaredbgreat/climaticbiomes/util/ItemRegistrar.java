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
    private static Map<String, Item> items = new HashMap<>();


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
            //buttFuck(out.getRegistryName().toString());
            return out;
        }
    }

    // List of Items


    public static void registerItems(final RegistryEvent.Register<Item> event) {
        ClimaticBiomes.getLogger().info("Registering Items for Climatic Biomes");
        for(BlockStore block : blocks) {
            Item item = block.makeItem();
            items.put(block.block.getRegistryName().toString(), item);
            event.getRegistry().register(item);
        }
    }


    static void addItemBlock(Block block, ItemGroup group) {
        blocks.add(new BlockStore(block, group));
    }



    private static void buttFuck(String fucked) {
        String msg = "Fucking " + fucked + "!!!";
        Logger fucker = ClimaticBiomes.getLogger();
        for(int i = 0; i < 1000; i++) {
            fucker.info(msg);
        }
        for(int i = 0; i < 1000; i++) {
            System.err.println(msg);
        }
    }


}
