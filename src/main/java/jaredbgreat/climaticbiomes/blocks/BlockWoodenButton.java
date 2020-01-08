package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;

public class BlockWoodenButton extends WoodButtonBlock {

    public BlockWoodenButton(String name) {
        super(makeProperties());
        setRegistryName(Info.ID, name);
    }


    private static Properties makeProperties() {
        return Properties.create(Material.MISCELLANEOUS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0.5f)
                .sound(SoundType.WOOD);
    }
}
