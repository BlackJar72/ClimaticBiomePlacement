package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Block;

public class BlockDoor extends DoorBlock {

    public BlockDoor(Block material, String name) {
        super(Properties.from(material).hardnessAndResistance(3.0f));
        setRegistryName(Info.ID, name);
    }


    public BlockDoor(Block material, float tough, String name) {
        super(Properties.from(material).hardnessAndResistance(tough));
        setRegistryName(Info.ID, name);
    }
}
