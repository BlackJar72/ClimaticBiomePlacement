package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class BlockPeat extends Block {

    public BlockPeat(String name) {
        super(makeProperties());
        setRegistryName(Info.ID, name);
    }


    @Override
    public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return true;
    }


    private static Block.Properties makeProperties() {
        Block.Properties out = Block.Properties.create(Material.EARTH, MaterialColor.DIRT);
        out.hardnessAndResistance(0.8f); // Dense and waterlogged (not in the MC sense)
        out.harvestLevel(0);
        out.harvestTool(ToolType.SHOVEL);
        out.sound(SoundType.GROUND);
        return out;
    }
}


