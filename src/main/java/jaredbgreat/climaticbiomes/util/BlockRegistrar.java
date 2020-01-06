package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.blocks.BlockBasalt;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;

public final class BlockRegistrar {

    // List of Blocks
    static BlockBasalt blockBasalt;
    static BlockBasalt blockPolishedBasalt;
    static BlockBasalt blockBasaltBricks;
    static BlockBasalt blockBasaltBricksCracked;




    public static void setupBlocks(final RegistryEvent.Register<Block> event) {
        ClimaticBiomes.getLogger().info("Preparing Blocks for Climatic Biomes");
        makeBlocks();
        registerBlocks(event);
    }


    private static void makeBlocks() {
        ClimaticBiomes.getLogger().info("Creating Blocks for Climatic Biomes");
        ItemRegistrar.addItemBlock(blockBasalt = new BlockBasalt("block_basalt"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockPolishedBasalt = new BlockBasalt("basalt_polished"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockBasaltBricks = new BlockBasalt("basalt_bricks"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockBasaltBricksCracked = new BlockBasalt("basalt_bricks_cracked"),
                ItemGroup.BUILDING_BLOCKS);
    }


    private static void registerBlocks(final RegistryEvent.Register<Block> event) {
        ClimaticBiomes.getLogger().info("Registering Blocks for Climatic Biomes");
        event.getRegistry().register(blockBasalt);
        event.getRegistry().register(blockPolishedBasalt);
        event.getRegistry().register(blockBasaltBricks);
        event.getRegistry().register(blockBasaltBricksCracked);

    }

    
}
