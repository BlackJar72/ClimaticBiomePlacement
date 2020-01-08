package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockWoodenPressurePlate extends PressurePlateBlock {

    public BlockWoodenPressurePlate(Block from, String name) {
        super(Sensitivity.EVERYTHING, makeProperties(from));
        setRegistryName(Info.ID, name);
    }


    private static Properties makeProperties(Block from) {
        final Properties out = Properties.create(Material.WOOD)
                .doesNotBlockMovement()
                .hardnessAndResistance(0.5F)
                .sound(SoundType.WOOD);
        return out;
    }
}
