package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeaves extends LeavesBlock {

    public BlockLeaves(String name) {
        super(makeProperties());
        setRegistryName(Info.ID, name);
    }


    private static Block.Properties makeProperties() {
        Block.Properties out = Block.Properties.create(Material.LEAVES);
        out.hardnessAndResistance(0.2f);
        out.tickRandomly();
        out.sound(SoundType.PLANT);
        return out;
    }
}
