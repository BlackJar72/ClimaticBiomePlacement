package jaredbgreat.climaticbiome.compat.dt;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.biomes.ModBiomes;
import jaredbgreat.climaticbiome.util.BlockRegistrar;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

public class TreePine extends TreeFamily {
	
	public class SouthernPineSpecies extends Species {
		
		public SouthernPineSpecies(TreeFamily treeFamily) {
			// Purely an initial guess.
			setBasicGrowingParameters(0.2f, 15.0f, 3, 3, 1.3f);
			
			envFactor(Type.COLD, 0.50f);
			envFactor(Type.SNOWY, 0.25f);
			envFactor(Type.DRY, 0.75f);
			envFactor(Type.WASTELAND, 0.25f);
			envFactor(Type.FOREST, 1.05f);			
			
			setupStandardSeedDropping();
			
		}
		
		@Override
		public boolean isBiomePerfect(Biome biome) {
			return biome == ModBiomes.pineWoods;
		}
		
	}
	
	
	public TreePine() {
		super(new ResourceLocation(Info.ID, "pine"));

		//Set up primitive log. This controls what is dropped on harvest.
		setPrimitiveLog(BlockRegistrar.blockPineLog.getDefaultState());

		DynamicTreeHelper.pineLeavesProperties.setTree(this);
	}

	
	@Override
	public void createSpecies() {
		setCommonSpecies(new SouthernPineSpecies(this));
		getCommonSpecies().generateSeed();
	}

//	
//	//Since we created a DynamicSapling in the common species we need to let it out to be registered.
//	@Override
//	public List<Block> getRegisterableBlocks(List<Block> blockList) {
//		blockList.add(getCommonSpecies().getDynamicSapling().getBlock());
//		return super.getRegisterableBlocks(blockList);
//	}


}
