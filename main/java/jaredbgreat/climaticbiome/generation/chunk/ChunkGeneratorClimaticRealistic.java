package jaredbgreat.climaticbiome.generation.chunk;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkGeneratorClimaticRealistic implements IChunkGenerator /*extends ChunkGeneratorOverworld*/ {

	public ChunkGeneratorClimaticRealistic(World worldIn, long seed,
			boolean mapFeaturesEnabledIn, String generatorOptions) {
		//super(worldIn, seed, mapFeaturesEnabledIn, generatorOptions);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Chunk generateChunk(int x, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void populate(int x, int z) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public List<SpawnListEntry> getPossibleCreatures(
			EnumCreatureType creatureType, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName,
			BlockPos position, boolean findUnexplored) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean isInsideStructure(World worldIn, String structureName,
			BlockPos pos) {
		// TODO Auto-generated method stub
		return false;
	}

}
