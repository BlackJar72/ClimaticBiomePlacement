package jaredbgreat.climaticbiome.biomes.decorators;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class VolcanoDecorator extends BiomeDecorator {
	private WorldGenLakes  extraLava;
    private WorldGenerator extraIron;
    private WorldGenerator extraGold;
	

	@Override
    protected void genDecorations(Biome biome, World world, Random random) {
        extraIron = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), 
        		chunkProviderSettings.ironSize);
        extraGold = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), 
        		(int)(chunkProviderSettings.goldSize * 1.5));
        extraLava = new WorldGenLakes(Blocks.FLOWING_LAVA);
        
    	super.genDecorations(biome, world, random);
    	
    	for (int i = 0; i < 24; ++i)  {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(random.nextInt(64) + 64) + 64);
            BlockPos pos = chunkPos.add(x, y, z);
    		IBlockState old = world.getBlockState(pos);
        	if(old.getMaterial() == Material.ROCK) {
                IBlockState bs = Blocks.FLOWING_LAVA.getDefaultState();
                world.setBlockState(pos, bs, 3);
                world.immediateBlockTick(pos, bs, random);
        	}
        }
    	
    	{
            int i1 = random.nextInt(16) + 8;
            int j1 = random.nextInt(256);
            int k1 = random.nextInt(16) + 8;
            (new WorldGenLakes(Blocks.LAVA)).generate(world, random, chunkPos.add(i1, j1, k1));
        }
    }
	

	@Override
    protected void generateOres(World world, Random random) {
    	super.generateOres(world, random);
        if(TerrainGen.generateOre(world, random, 
        		extraIron, chunkPos, 
        		OreGenEvent.GenerateMinable.EventType.IRON))
        	genStandardOre1(world, random, chunkProviderSettings.ironCount, extraIron, 
        			chunkProviderSettings.ironMinHeight, 
        			(int)(chunkProviderSettings.ironMaxHeight * 0.75));
        if(TerrainGen.generateOre(world, random, goldGen, chunkPos, 
        		OreGenEvent.GenerateMinable.EventType.GOLD)) 
        	genStandardOre1(world, random, chunkProviderSettings.goldCount, extraGold, 
        			chunkProviderSettings.goldMinHeight, 
        			(int)(chunkProviderSettings.goldMaxHeight * 1.5));
        
    }

}
