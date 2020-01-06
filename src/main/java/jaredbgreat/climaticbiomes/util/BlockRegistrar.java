package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.blocks.BlockAsh;
import jaredbgreat.climaticbiomes.blocks.BlockIgneus;
import jaredbgreat.climaticbiomes.blocks.BlockPeat;
import jaredbgreat.climaticbiomes.blocks.BlockPlanks;
import jaredbgreat.climaticbiomes.blocks.ItemBlocks.ItemFuelBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;

public final class BlockRegistrar {

    // Stony Blocks
    static BlockIgneus blockBasalt;
    static BlockIgneus blockPolishedBasalt;
    static BlockIgneus blockBasaltBricks;
    static BlockIgneus blockBasaltBricksCracked;
    static BlockIgneus blockGraniteBricks;
    static BlockIgneus blockGraniteBricksCracked;
    static BlockIgneus blockAndesiteBricks;
    static BlockIgneus blockAndesiteBricksCracked;
    static BlockIgneus blockDioriteBricks;
    static BlockIgneus blockDioriteBricksCracked;

    //Pine Blocks
    static BlockPlanks blockPinePlanks;

    // Misc Blocks
    static BlockAsh blockVolcanicAsh;
    static BlockPeat blockPeat;



    public static void setupBlocks(final RegistryEvent.Register<Block> event) {
        ClimaticBiomes.getLogger().info("Preparing Blocks for Climatic Biomes");
        makeBlocks();
        registerBlocks(event);
    }


    private static void makeBlocks() {
        ClimaticBiomes.getLogger().info("Creating Blocks for Climatic Biomes");
        // Basalt, plus variants on vanilla igneus intrusive blocks
        ItemRegistrar.addItemBlock(blockBasalt = new BlockIgneus("block_basalt"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockPolishedBasalt = new BlockIgneus("basalt_polished"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockBasaltBricks = new BlockIgneus("basalt_bricks"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockBasaltBricksCracked = new BlockIgneus("basalt_bricks_cracked"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockGraniteBricks = new BlockIgneus("granite_bricks"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockGraniteBricksCracked = new BlockIgneus("granite_bricks_cracked"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockAndesiteBricks = new BlockIgneus("andesite_bricks"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockAndesiteBricksCracked = new BlockIgneus("andesite_bricks_cracked"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockDioriteBricks = new BlockIgneus("diorite_bricks"),
                ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockDioriteBricksCracked = new BlockIgneus("diorite_bricks_cracked"),
                ItemGroup.BUILDING_BLOCKS);
        // Pine related blocks
        ItemRegistrar.addItem(new ItemFuelBlock(blockPinePlanks = new BlockPlanks("pine_planks"), 300));

        // Misc Blocks
        ItemRegistrar.addItemBlock(blockVolcanicAsh = new BlockAsh("volcanic_ash"), ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItem(new ItemFuelBlock(blockPeat = new BlockPeat("peat"), 3200));
    }


    private static void registerBlocks(final RegistryEvent.Register<Block> event) {
        ClimaticBiomes.getLogger().info("Registering Blocks for Climatic Biomes");
        event.getRegistry().register(blockBasalt);
        event.getRegistry().register(blockPolishedBasalt);
        event.getRegistry().register(blockBasaltBricks);
        event.getRegistry().register(blockBasaltBricksCracked);
        event.getRegistry().register(blockGraniteBricks);
        event.getRegistry().register(blockGraniteBricksCracked);
        event.getRegistry().register(blockAndesiteBricks);
        event.getRegistry().register(blockAndesiteBricksCracked);
        event.getRegistry().register(blockDioriteBricks);
        event.getRegistry().register(blockDioriteBricksCracked);
        event.getRegistry().register(blockVolcanicAsh);
        event.getRegistry().register(blockPinePlanks);
        event.getRegistry().register(blockPeat);
    }


}
