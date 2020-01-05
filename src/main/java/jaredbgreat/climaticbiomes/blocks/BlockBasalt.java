package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBasalt extends Block {

    public BlockBasalt() {
        super(makeProperties());
        this.setRegistryName(Info.ID, "block_basalt");
    }


    private static Block.Properties makeProperties() {
        Block.Properties out = Block.Properties.create(Material.ROCK);
        out.hardnessAndResistance(1.75f, 32f);
        out.harvestLevel(0);
        out.harvestTool(ToolType.PICKAXE);
        out.sound(SoundType.STONE);
        return out;
    }

}
/*
    public BlockIgneous(String name) {
        super(Material.ROCK);
        this.name = name;
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setRegistryName(Info.ID, name);
        setUnlocalizedName(Info.ID + "." + name);
        setHardness(1.75f);
        setHarvestLevel("pickaxe", 0);
        setResistance(32f);
        BlockRegistrar.addBlock(this);
        ItemRegistrar.addItem(new ItemBlock(this).setRegistryName(getRegistryName()));
    }
*/