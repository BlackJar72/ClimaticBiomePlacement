package jaredbgreat.climaticbiome.biomes.feature;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class GenNoTree extends WorldGenAbstractTree {

	public GenNoTree() {
		super(false);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		// Do Nothing!
		return false;
	}

}
