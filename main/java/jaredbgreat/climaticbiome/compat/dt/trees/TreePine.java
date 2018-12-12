package jaredbgreat.climaticbiome.compat.dt.trees;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.blocks.BlockPineNeedles;
import jaredbgreat.climaticbiome.compat.dt.DynamicTreeHelper;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenConiferTopper;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenPodzol;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

public class TreePine extends TreeFamily {
	
	public class PineSpeciesBase extends Species {
		
		public PineSpeciesBase(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily, DynamicTreeHelper.pineLeavesProperties);
			// Purely an initial guess.
			setBasicGrowingParameters(0.2f, 16.0f, 3, 4, 1.3f);
			setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.CONIFER));
			
			envFactor(Type.COLD, 0.50f);
			envFactor(Type.SNOWY, 0.25f);
			envFactor(Type.DRY, 0.75f);
			envFactor(Type.WASTELAND, 0.50f);
			envFactor(Type.FOREST, 1.05f);		
			
			setupStandardSeedDropping();
			
			//Add species features
			addGenFeature(new FeatureGenConiferTopper(getLeavesProperties()));
			addGenFeature(new FeatureGenPodzol());
			
		}
		
		@Override
		public boolean isBiomePerfect(Biome biome) {
			return biome == ModBiomes.pineWoods;
		}
		
	}
	
	
	public TreePine() {
		super(new ResourceLocation(Info.ID, "pine"));		
		IBlockState primLog = BlockRegistrar.blockPineLog.getDefaultState();
		setPrimitiveLog(primLog, new ItemStack(BlockRegistrar.blockPineLog));
		DynamicTreeHelper.pineLeavesProperties.setTree(this);
		addConnectableVanillaLeaves((state) -> {
			return state.getBlock() instanceof BlockPineNeedles;
		});
	}

	
	@Override
	public void createSpecies() {
		setCommonSpecies(new PineSpeciesBase(this));
		getCommonSpecies().generateSeed();
	}



}
