package jaredbgreat.climaticbiome.generation.chunk;

import net.minecraft.world.chunk.ChunkPrimer;

public interface IBlockSetter {
	
    public void setBlocksInChunk(int x, int z, ChunkPrimer primer);

}
