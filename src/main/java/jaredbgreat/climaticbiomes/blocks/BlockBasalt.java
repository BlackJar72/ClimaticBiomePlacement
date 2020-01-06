package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBasalt extends Block {

    public BlockBasalt(String name) {
        super(makeProperties());
        this.setRegistryName(Info.ID, name);
    }


    private static Block.Properties makeProperties() {
        Block.Properties out = Block.Properties.create(Material.ROCK);
        out.hardnessAndResistance(1.75f, 2f);
        out.harvestLevel(0);
        out.harvestTool(ToolType.PICKAXE);
        out.sound(SoundType.STONE);
        return out;
    }

}