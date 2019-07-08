package jaredbgreat.climaticbiome.biomes;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SubBiome extends Biome {
	private Biome parent;
	private long  id; // Sub-ID

	public SubBiome(Biome parent, int sid, BiomeProperties properties) {
		super(properties);
		id  = Biome.getIdForBiome(parent) | (sid << 32);
		this.parent = parent;
        this.topBlock = parent.topBlock;
        this.fillerBlock = parent.fillerBlock;
        this.decorator = parent.decorator;
	}
	
	
	public long getSubId() {
		return id;
	}
	
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, 
				int x, int z, double noiseVal) {
		parent.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

}
