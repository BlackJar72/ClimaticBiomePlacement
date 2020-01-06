package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockPlanks extends Block {

    public BlockPlanks(String name) {
        super(makeProperties());
        this.setRegistryName(Info.ID, name);
    }


    private static Block.Properties makeProperties() {
        Block.Properties out = Block.Properties.create(Material.WOOD, MaterialColor.WOOD);
        out.hardnessAndResistance(2.0F, 3.0F);
        out.harvestLevel(0);
        out.harvestTool(ToolType.AXE);
        out.sound(SoundType.WOOD);
        return out;
    }

}