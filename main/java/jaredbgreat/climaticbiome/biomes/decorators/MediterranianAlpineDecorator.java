package jaredbgreat.climaticbiome.biomes.decorators;

import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.biomes.feature.GenNoTree;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class MediterranianAlpineDecorator extends BiomeDecorator  {
	GenNoTree NOTHING;
	
	public MediterranianAlpineDecorator() {
		NOTHING = new GenNoTree();
	}
	

	@Override
    protected void genDecorations(Biome biome, World world, Random random) {        
    	super.genDecorations(biome, world, random);
    	
    	for(int i = 0; i < 6; i++) {
            int x = random.nextInt(16) + 8 + chunkPos.getX();
            int z = random.nextInt(16) + 8 + chunkPos.getZ();
    		int y = world.getHeight(x, z); 
    		int cutoff = y + random.nextInt(16);
    		WorldGenAbstractTree tree;
    		if(cutoff < 120) {
	    		if(cutoff < 96) {
	    			if(random.nextBoolean()) {
	    				tree = ModBiomes.denseScrub.getRandomTreeFeature(random);
	    			} else {
	    				tree = NOTHING;
	    			}
	    		} else {
	    			tree = biome.getRandomTreeFeature(random);
	    		}
	    		BlockPos pos = new BlockPos(x, y, z);
	    		tree.generate(world, random, pos);
    		}
    	}
    }

}
