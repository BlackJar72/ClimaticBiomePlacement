package jaredbgreat.climaticbiome.compat.dt;

import jaredbgreat.climaticbiome.util.BlockRegistrar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;

public class DynamicTreeHelper {
	
	public static LeavesProperties pineLeavesProperties;

	public static void preInit() {
		pineLeavesProperties 
			= new LeavesProperties(BlockRegistrar.blockPineNeedles.getDefaultState());
	}
	
}
