package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BlockAsh extends FallingBlock {

    public BlockAsh(String name) {
        super(makeProperties());
        this.setRegistryName(Info.ID, name);
    }


    private static Block.Properties makeProperties() {
        Block.Properties out = Block.Properties.create(Material.SAND, MaterialColor.STONE);
        out.hardnessAndResistance(0.6f);
        out.harvestLevel(0);
        out.harvestTool(ToolType.SHOVEL);
        out.sound(SoundType.SAND);
        return out;
    }


    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState state) {
        return -8356741;
    }

}
