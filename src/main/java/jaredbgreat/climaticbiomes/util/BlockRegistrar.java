package jaredbgreat.climaticbiomes.util;

import jaredbgreat.climaticbiomes.ClimaticBiomes;
import jaredbgreat.climaticbiomes.Info;
import jaredbgreat.climaticbiomes.blocks.*;
import jaredbgreat.climaticbiomes.blocks.ItemBlocks.ItemFuelBlock;
import net.minecraft.block.*;
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
    static BlockLog pineLog;
    static BlockLog pineLogStripped;
    static BlockLeaves pineNeedles;
    static BlockPlanks blockPinePlanks;
    static BlockPlanks blockPinePlanksLong;
    static SlabBlock slabPine;
    static StairsBlock stairPine;
    static FenceBlock pineFence;
    static FenceGateBlock pineGate;
    static DoorBlock pineDoor;
    static StandingSignBlock pineSign;
    static WallSignBlock pineWallSign;
    static PressurePlateBlock pinePPlate;
    static TrapDoorBlock pineTDoor;
    static WoodButtonBlock pineNutton;
    static RotatedPillarBlock barkPine;
    static RotatedPillarBlock woodPine;
    static FlowerPotBlock pinePotted;

    // Misc Blocks
    static BlockAsh blockVolcanicAsh;
    static BlockPeat blockPeat;
    static BlockCob blockCob;
    static BlockCob blockMudBrickL;
    static BlockCob blockMudBrickS;

    // Slabs
    static SlabBlock slabBasalt;
    static SlabBlock slabPolishedBasalt;
    static SlabBlock slabBasaltBricks;
    static SlabBlock slabBasaltBricksCracked;
    static SlabBlock slabGraniteBricks;
    static SlabBlock slabGraniteBricksCracked;
    static SlabBlock slabAndesiteBricks;
    static SlabBlock slabAndesiteBricksCracked;
    static SlabBlock slabDioriteBricks;
    static SlabBlock slabDioriteBricksCracked;
    static SlabBlock slabCob;
    static SlabBlock slabMudBrickL;
    static SlabBlock slabMudBrickS;

    // Stairs
    static StairsBlock stairBasalt;
    static StairsBlock stairPolishedBasalt;
    static StairsBlock stairBasaltBricks;
    static StairsBlock stairBasaltBricksCracked;
    static StairsBlock stairGraniteBricks;
    static StairsBlock stairGraniteBricksCracked;
    static StairsBlock stairAndesiteBricks;
    static StairsBlock stairAndesiteBricksCracked;
    static StairsBlock stairDioriteBricks;
    static StairsBlock stairDioriteBricksCracked;
    static StairsBlock stairCob;
    static StairsBlock stairMudBrickL;
    static StairsBlock stairMudBrickS;

    // Fence Walls
    static WallBlock wallBasalt;
    static WallBlock wallPolishedBasalt;
    static WallBlock wallBasaltBricks;
    static WallBlock wallBasaltBricksCracked;
    static WallBlock wallGraniteBricks;
    static WallBlock wallGraniteBricksCracked;
    static WallBlock wallAndesiteBricks;
    static WallBlock wallAndesiteBricksCracked;
    static WallBlock wallDioriteBricks;
    static WallBlock wallDioriteBricksCracked;
    static WallBlock wallCob;
    static WallBlock wallMudBrickL;
    static WallBlock wallMudBrickS;




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
        ItemRegistrar.addItem(new ItemFuelBlock(pineLog = new BlockLog("pine_log"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(pineNeedles = new BlockLeaves("pine_leaves"), 100));
        ItemRegistrar.addItem(new ItemFuelBlock(blockPinePlanks = new BlockPlanks("pine_planks"), 300));
        ItemRegistrar.addItem(new ItemFuelBlock(slabPine = makeSlab(blockPinePlanks, "pine_slab"), 300));

        // Misc Blocks
        ItemRegistrar.addItemBlock(blockVolcanicAsh = new BlockAsh("volcanic_ash"), ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItem(new ItemFuelBlock(blockPeat = new BlockPeat("peat"), 3200));
        ItemRegistrar.addItemBlock(blockCob = new BlockCob("cob"), ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockMudBrickL = new BlockCob("mud_bricks_large"), ItemGroup.BUILDING_BLOCKS);
        ItemRegistrar.addItemBlock(blockMudBrickS = new BlockCob("mud_bricks_small"), ItemGroup.BUILDING_BLOCKS);

    }


    private static void registerBlocks(final RegistryEvent.Register<Block> event) {
        ClimaticBiomes.getLogger().info("Registering Blocks for Climatic Biomes");
        // Volcano and Stone
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
        // Pine
        event.getRegistry().register(pineLog);
        event.getRegistry().register(pineNeedles);
        event.getRegistry().register(blockPinePlanks);
        event.getRegistry().register(slabPine);
        // Misc
        event.getRegistry().register(blockPeat);
        event.getRegistry().register(blockCob);
        event.getRegistry().register(blockMudBrickL);
        event.getRegistry().register(blockMudBrickS);
    }


    private static SlabBlock makeSlab(Block whole) {
        SlabBlock out = new SlabBlock(Block.Properties.from(whole));
        out.setRegistryName(whole.getRegistryName() + "_slab");
        return out;
    }


    private static SlabBlock makeSlab(Block whole, String name) {
        SlabBlock out = new SlabBlock(Block.Properties.from(whole));
        out.setRegistryName(Info.ID, name);
        return out;
    }


}
