package jaredbgreat.climaticbiome.generation.mutators;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class DecRocksMore implements ISpecialDecoration {
	private static final WorldGenBlockBlob ROCK_PILES 
		= new WorldGenBlockBlob(Blocks.COBBLESTONE, 0);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, 
				IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    	int n = random.nextInt(3) + 2;
    	int x, y, z;
		for (int i = 0; i < n; i++) {
    		x = (chunkX * 16) + random.nextInt(16) + 8;
    		z = (chunkZ * 16) + random.nextInt(16) + 8;
    		y = world.getHeight(x, z);
            ROCK_PILES.generate(world, random, new BlockPos(x, y, z));
		}
		
	}

}
