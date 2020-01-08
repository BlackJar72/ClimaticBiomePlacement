package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.Info;
import jaredbgreat.climaticbiomes.blocks.*;
import jaredbgreat.climaticbiomes.blocks.ItemBlocks.ItemFuelBlock;
import jaredbgreat.climaticbiomes.features.PineTree;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;

import java.util.function.Supplier;

public final class BlockRegistrar {

    // Stony Blocks
    public static BlockIgneus blockBasalt;
    public static BlockIgneus blockPolishedBasalt;
    public static BlockIgneus blockBasaltBricks;
//    public static BlockIgneus blockBasaltBricksCracked;
//    public static BlockIgneus blockGraniteBricks;
//    public static BlockIgneus blockGraniteBricksCracked;
//    public static BlockIgneus blockAndesiteBricks;
//    public static BlockIgneus blockAndesiteBricksCracked;
//    public static BlockIgneus blockDioriteBricks;
//    public static BlockIgneus blockDioriteBricksCracked;

    //Pine Blocks
    public static BlockLog pineLog;
    public static BlockLog pineLogStripped;
    public static BlockLeaves pineNeedles;
    public static BlockSapling pineSapling;
    public static BlockPlanks blockPinePlanks;
    public static BlockPlanks blockPinePlanksLong;
    public static SlabBlock slabPine;
    public static StairsBlock pineStairs;
    public static FenceBlock pineFence;
    public static FenceGateBlock pineGate;
    public static DoorBlock pineDoor;
    public static StandingSignBlock pineSign;
    public static WallSignBlock pineWallSign;
    public static PressurePlateBlock pinePPlate;
    public static TrapDoorBlock pineTDoor;
    public static WoodButtonBlock pineButton;
    public static RotatedPillarBlock barkPine;
    public static RotatedPillarBlock woodPine;
    public static FlowerPotBlock pinePotted;

    // Misc Blocks
    public static BlockAsh blockVolcanicAsh;
    public static BlockPeat blockPeat;
//    public static BlockCob blockCob;
//    public static BlockCob blockMudBrickL;
//    public static BlockCob blockMudBrickS;

    // Slabs
    public static SlabBlock slabBasalt;
    public static SlabBlock slabPolishedBasalt;
    public static SlabBlock slabBasaltBricks;
//    public static SlabBlock slabBasaltBricksCracked;
//    public static SlabBlock slabGraniteBricks;
//    public static SlabBlock slabGraniteBricksCracked;
//    public static SlabBlock slabAndesiteBricks;
//    public static SlabBlock slabAndesiteBricksCracked;
//    public static SlabBlock slabDioriteBricks;
//    public static SlabBlock slabDioriteBricksCracked;
//    public static SlabBlock slabCob;
//    public static SlabBlock slabMudBrickL;
//    public static SlabBlock slabMudBrickS;

    // Stairs
    public static StairsBlock stairBasalt;
    public static StairsBlock stairPolishedBasalt;
    public static StairsBlock stairBasaltBricks;
//    public static StairsBlock stairBasaltBricksCracked;
//    public static StairsBlock stairGraniteBricks;
//    public static StairsBlock stairGraniteBricksCracked;
//    public static StairsBlock stairAndesiteBricks;
//    public static StairsBlock stairAndesiteBricksCracked;
//    public static StairsBlock stairDioriteBricks;
//    public static StairsBlock stairDioriteBricksCracked;
//    public static StairsBlock stairCob;
//    public static StairsBlock stairMudBrickL;
//    public static StairsBlock stairMudBrickS;

    // Fence Walls
    public static WallBlock wallBasalt;
    public static WallBlock wallPolishedBasalt;
    public static WallBlock wallBasaltBricks;
//    public static WallBlock wallBasaltBricksCracked;
//    public static WallBlock wallGraniteBricks;
//    public static WallBlock wallGraniteBricksCracked;
//    public static WallBlock wallAndesiteBricks;
//    public static WallBlock wallAndesiteBricksCracked;
//    public static WallBlock wallDioriteBricks;
//    public static WallBlock wallDioriteBricksCracked;
//    public static WallBlock wallCob;
//    public static WallBlock wallMudBrickL;
//    public static WallBlock wallMudBrickS;




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
        slabBasalt = makeSlab(blockBasalt);
        slabPolishedBasalt = makeSlab(blockPolishedBasalt);
        slabBasaltBricks = makeSlab(blockBasaltBricks);
//        ItemRegistrar.addItemBlock(blockBasaltBricksCracked = new BlockIgneus("basalt_bricks_cracked"),
//                ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockGraniteBricks = new BlockIgneus("granite_bricks"),
//                ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockGraniteBricksCracked = new BlockIgneus("granite_bricks_cracked"),
//                ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockAndesiteBricks = new BlockIgneus("andesite_bricks"),
//                ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockAndesiteBricksCracked = new BlockIgneus("andesite_bricks_cracked"),
//                ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockDioriteBricks = new BlockIgneus("diorite_bricks"),
//                ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockDioriteBricksCracked = new BlockIgneus("diorite_bricks_cracked"),
//                ItemGroup.BUILDING_BLOCKS);

        // Pine related blocks
        ItemRegistrar.addItem(new ItemFuelBlock(pineLog
                = new BlockLog("pine_log"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(pineLogStripped
                = new BlockLog("pine_log_stripped"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(barkPine
                = makePillar(pineLog, "pine_bark"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(woodPine
                = makePillar(pineLogStripped, "pine_wood"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(pineNeedles
                = new BlockLeaves("pine_leaves"), 100));
        ItemRegistrar.addItem(new ItemFuelBlock(blockPinePlanks
                = new BlockPlanks("pine_planks"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(slabPine
                = makeSlab(blockPinePlanks, "pine_slab"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(pineStairs
                = makeStairs(blockPinePlanks, "pine_stairs"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(pineFence =
                makeWoodenFence(blockPinePlanks, "pine_fence"), 200, ItemGroup.DECORATIONS));
        ItemRegistrar.addItem(new ItemFuelBlock(pineGate =
                makeWoodenGate(blockPinePlanks, "pine_gate"), 300, ItemGroup.REDSTONE));
        ItemRegistrar.addItem(new ItemFuelBlock(pineDoor =
                new BlockDoor(blockPinePlanks, "pine_door"), 300, ItemGroup.REDSTONE));
        ItemRegistrar.addItem(new ItemFuelBlock(pineTDoor =
                new BlockTrapDoor(blockPinePlanks, "pine_trapdoor"), 300, ItemGroup.REDSTONE));
        ItemRegistrar.addItem(new ItemFuelBlock(pinePPlate =
                new BlockWoodenPressurePlate(blockPinePlanks, "pine_pressure_plate"),
                300, ItemGroup.REDSTONE));
        ItemRegistrar.addItem(new ItemFuelBlock(pineButton =
                new BlockWoodenButton("pine_button"), 100, ItemGroup.REDSTONE));
        ItemRegistrar.addItem(new ItemFuelBlock(pineSapling =
                new BlockSapling(new PineTree(), "pine_sapling"), 100, ItemGroup.DECORATIONS));


        // Misc Blocks
        ItemRegistrar.addItemBlock(blockVolcanicAsh = new BlockAsh("volcanic_ash"), ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItem(new ItemFuelBlock(blockPeat = new BlockPeat("peat"), 3200));
//        ItemRegistrar.addItemBlock(blockCob = new BlockCob("cob"), ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockMudBrickL = new BlockCob("mud_bricks_large"), ItemGroup.BUILDING_BLOCKS);
//        ItemRegistrar.addItemBlock(blockMudBrickS = new BlockCob("mud_bricks_small"), ItemGroup.BUILDING_BLOCKS);

    }


    private static void registerBlocks(final RegistryEvent.Register<Block> event) {
        ClimaticBiomes.getLogger().info("Registering Blocks for Climatic Biomes");
        // Volcano and Stone
        event.getRegistry().register(blockBasalt);
        event.getRegistry().register(blockPolishedBasalt);
        event.getRegistry().register(blockBasaltBricks);
        event.getRegistry().register(slabBasalt);
        event.getRegistry().register(slabPolishedBasalt);
        event.getRegistry().register(slabBasaltBricks);
//        event.getRegistry().register(blockBasaltBricksCracked);
//        event.getRegistry().register(blockGraniteBricks);
//        event.getRegistry().register(blockGraniteBricksCracked);
//        event.getRegistry().register(blockAndesiteBricks);
//        event.getRegistry().register(blockAndesiteBricksCracked);
//        event.getRegistry().register(blockDioriteBricks);
//        event.getRegistry().register(blockDioriteBricksCracked);
        event.getRegistry().register(blockVolcanicAsh);
        // Pine
        event.getRegistry().register(pineLog);
        event.getRegistry().register(pineLogStripped);
        event.getRegistry().register(barkPine);
        event.getRegistry().register(woodPine);
        event.getRegistry().register(pineNeedles);
        event.getRegistry().register(pineSapling);
        event.getRegistry().register(blockPinePlanks);
        event.getRegistry().register(slabPine);
        event.getRegistry().register(pineStairs);
        event.getRegistry().register(pineFence);
        event.getRegistry().register(pineGate);
        event.getRegistry().register(pineDoor);
        event.getRegistry().register(pineTDoor);
        event.getRegistry().register(pinePPlate);
        event.getRegistry().register(pineButton);

        // Misc
        event.getRegistry().register(blockPeat);
//        event.getRegistry().register(blockCob);
//        event.getRegistry().register(blockMudBrickL);
//        event.getRegistry().register(blockMudBrickS);

    }


    private static SlabBlock makeSlab(Block whole) {
        SlabBlock out = new SlabBlock(Block.Properties.from(whole));
        out.setRegistryName(whole.getRegistryName() + "_slab");
        ItemRegistrar.addItemBlock(out, ItemGroup.BUILDING_BLOCKS);
        return out;
    }


    private static SlabBlock makeSlab(Block whole, String name) {
        SlabBlock out = new SlabBlock(Block.Properties.from(whole));
        out.setRegistryName(Info.ID, name);
        return out;
    }


    private static RotatedPillarBlock makePillar(Block whole, String name) {
        RotatedPillarBlock out = new RotatedPillarBlock(Block.Properties.from(whole));
        out.setRegistryName(Info.ID, name);
        return out;
    }


    private static StairsBlock makeStairs(Block whole, String name) {
        BlockStairs out = new BlockStairs(whole.getDefaultState(), Block.Properties.from(whole));
        out.setRegistryName(Info.ID, name);
        return out;
    }


    private static FenceBlock makeWoodenFence(Block whole, String name) {
        FenceBlock out = new FenceBlock(Block.Properties.from(whole)
                .hardnessAndResistance(2.0f, 3.0f));
        out.setRegistryName(Info.ID, name);
        return out;
    }


    private static FenceGateBlock makeWoodenGate(Block whole, String name) {
        FenceGateBlock out = new FenceGateBlock(Block.Properties.from(whole)
                .hardnessAndResistance(2.0f, 3.0f));
        out.setRegistryName(Info.ID, name);
        return out;
    }


}
