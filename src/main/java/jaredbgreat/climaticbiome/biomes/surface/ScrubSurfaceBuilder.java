package jaredbgreat.climaticbiome.biomes.surface;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import jaredbgreat.climaticbiome.biomes.basic.Scrub;
import jaredbgreat.climaticbiome.biomes.basic.Scrub.ScrubType;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ScrubSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	private Scrub.ScrubType type;	
	
	public ScrubSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> p_i51305_1_) {
		super(p_i51305_1_);
	}
	
	
	public ScrubSurfaceBuilder setScrubType(Scrub.ScrubType type) {
		this.type = type;
		return this;
	}

	
	@Override
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
			BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {    
	      if (noise > 1.25D) {
	    	  if(type == ScrubType.DENSE) {
	          SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, 
	        		  startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, 
	        		  SurfaceBuilder.CORASE_DIRT_DIRT_GRAVEL_CONFIG);
	    	  } else {
		          SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, 
		        		  startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, 
		        		  SurfaceBuilder.SAND_SAND_GRAVEL_CONFIG);	    		  
	    	  }
	       } else {
	          SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, 
	        		  startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, 
	        		  SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
	       }		
	}

}
