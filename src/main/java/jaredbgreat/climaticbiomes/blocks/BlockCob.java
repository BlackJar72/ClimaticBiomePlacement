package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.material.MaterialColor;

public class BlockCob extends Block {

    public BlockCob(String name) {
        super(makeProperties());
        setRegistryName(Info.ID, name);
    }


    private static Properties makeProperties() {
        Properties out = Properties.create(Material.EARTH, MaterialColor.ADOBE);
        out.hardnessAndResistance(2.0f);
        out.sound(SoundType.STONE);
        return out;
    }

}
