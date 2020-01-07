package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;

public class BlockSapling extends SaplingBlock {

    public BlockSapling(Tree tree, String name) {
        super(tree, makeProperties());
        setRegistryName(Info.ID, name);
    }


    private static Properties makeProperties() {
        Properties out = Properties.create(Material.PLANTS);
        out.doesNotBlockMovement();
        out.tickRandomly();
        out.sound(SoundType.PLANT);
        out.hardnessAndResistance(0f);
        return out;
    }
}
