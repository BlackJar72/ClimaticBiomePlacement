package jaredbgreat.climaticbiome.compat.dynamictrees.trees;

import jaredbgreat.climaticbiome.Info;
import net.minecraft.util.ResourceLocation;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

public class SpeciesSmallJungle extends Species {
	
	public SpeciesSmallJungle(TreeFamily family) {
		super(new ResourceLocation(Info.ID, family.getName() + "_small"), 
				family, family.getCommonLeaves());

		setBasicGrowingParameters(0.2f, 16.0f, 3, 4, 1.3f);
		setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.JUNGLE));
		
		
	}

}
