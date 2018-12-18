package jaredbgreat.climaticbiome.compat.dynamictrees.trees;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenCocoa;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenVine;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

public class SpeciesSmallJungle extends Species {
	
	public SpeciesSmallJungle(TreeFamily family) {
		super(new ResourceLocation(Info.ID, family.getName() + "_small"), 
				family, family.getCommonLeaves());

		setBasicGrowingParameters(0.2f, 14.0f, 3, 4, 1.0f);
		setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.JUNGLE));
		envFactor(Type.COLD, 0.15f);
		envFactor(Type.DRY,  0.20f);
		envFactor(Type.HOT, 1.1f);
		envFactor(Type.WET, 1.1f);
		
		setupStandardSeedDropping();
		
		//Add species features
		addGenFeature(new FeatureGenCocoa());
		addGenFeature(new FeatureGenVine().setQuantity(16).setMaxLength(16));
		
	}
	
	
	@Override
	public boolean isBiomePerfect(Biome biome) {
		return biome == ModBiomes.tropicalForest || biome == ModBiomes.tropicalForestHills;
	}

}
