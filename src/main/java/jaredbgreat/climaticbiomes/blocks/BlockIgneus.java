package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockIgneus extends Block {

    public BlockIgneus(String name) {
        super(makeProperties());
        this.setRegistryName(Info.ID, name);
    }


    private static Block.Properties makeProperties() {
        Block.Properties out = Block.Properties.create(Material.ROCK, MaterialColor.STONE);
        out.hardnessAndResistance(1.75f, 6.4f);
        out.harvestLevel(0);
        out.harvestTool(ToolType.PICKAXE);
        out.sound(SoundType.STONE);
        return out;
    }

}