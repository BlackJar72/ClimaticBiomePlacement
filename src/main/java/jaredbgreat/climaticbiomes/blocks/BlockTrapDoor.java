package jaredbgreat.climaticbiomes.blocks;

import jaredbgreat.climaticbiomes.Info;
import net.minecraft.block.Block;
import net.minecraft.block.TrapDoorBlock;

public class BlockTrapDoor extends TrapDoorBlock {

    public BlockTrapDoor(Block from, String name) {
        super(Properties.from(from).hardnessAndResistance(3.0f));
        setRegistryName(Info.ID, name);
    }
}
