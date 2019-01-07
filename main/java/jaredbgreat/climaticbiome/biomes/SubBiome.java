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
	private int id; // Sub-ID

	public SubBiome(Biome parent, int sid, BiomeProperties properties) {
		super(properties);
		id = Biome.getIdForBiome(parent) | (sid << 8);
		this.parent = parent;
        this.topBlock = parent.topBlock;
        this.fillerBlock = parent.fillerBlock;
        this.decorator = parent.decorator;
	}
	
	
	public int getSubId() {
		return id;
	}
	
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, 
				int x, int z, double noiseVal) {
		parent.genTerrainBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}
	
	
	
	/*
	 * The methods below should now normally be called in the current system,
	 * as these biomes are only used for generating the terrain while the 
	 * parent biome will used for decoration.  However, they are here in case needed 
	 * in some future version.
	 */
	
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return parent.getRandomTreeFeature(rand);
	}
	
	

	@Override
    public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
		return parent.pickRandomFlower(rand, pos);
    }
	
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		parent.decorate(worldIn, rand, pos);
	}
	
	
	@Override
    public Class <? extends Biome > getBiomeClass() {
        return parent.getBiomeClass();
    }
	

    @Override
    public void addDefaultFlowers() {
    	parent.addDefaultFlowers();
    }

}
