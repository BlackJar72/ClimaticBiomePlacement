package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockLog extends LogBlock {

    public BlockLog(String name) {
        super(MaterialColor.WOOD, makeProperties());
        setRegistryName(Info.ID, name);
    }


    private static Properties makeProperties() {
        Properties out = Properties.create(Material.WOOD, MaterialColor.QUARTZ);
        out.sound(SoundType.WOOD);
        out.hardnessAndResistance(2.0f);
        return out;
    }
}
