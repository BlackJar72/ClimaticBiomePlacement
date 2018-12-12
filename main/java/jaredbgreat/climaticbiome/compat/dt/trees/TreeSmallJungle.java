package jaredbgreat.climaticbiome.compat.dt.trees;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.biomes.basic.ModBiomes;
import jaredbgreat.climaticbiome.compat.dt.DynamicTreeHelper;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.ferreusveritas.dynamictrees.ModBlocks;
import com.ferreusveritas.dynamictrees.ModTrees;
import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.blocks.BlockBranch;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenCocoa;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenVine;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.ferreusveritas.dynamictrees.trees.TreeJungle;


public class TreeSmallJungle extends TreeJungle {
	private final ResourceLocation name;
	
	public class SpeciesSmallJungle extends Species {
		SpeciesSmallJungle(TreeFamily treeFamily) {
			super(treeFamily.getName(), treeFamily);
						
			setBasicGrowingParameters(0.2f, 14.0f, 3, 2, 1.0f);
			setGrowthLogicKit(TreeRegistry.findGrowthLogicKit(ModTrees.JUNGLE));
			
			envFactor(Type.COLD,   0.15f);
			envFactor(Type.DRY,    0.20f);
			envFactor(Type.HOT,    1.1f);
			envFactor(Type.FOREST, 1.05f);
			
			setupStandardSeedDropping();
			
			//Add species features
			addGenFeature(new FeatureGenCocoa());
			addGenFeature(new FeatureGenVine());
		}
		
		
		@Override
		public boolean isBiomePerfect(Biome biome) {
			return (biome == ModBiomes.tropicalForest) 
					|| (biome == ModBiomes.tropicalForestHills);
		};
	}
	
	
	public TreeSmallJungle() {
		super();
		this.name = new ResourceLocation(Info.ID, "shortjungle");
		canSupportCocoa = true;		
		IBlockState primLog = Blocks.LOG.getDefaultState()
				.withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
		DynamicTreeHelper.jungleLeavesProperties.setTree(this);

		addConnectableVanillaLeaves((state) -> { 
			return state.getBlock() instanceof BlockOldLeaf 
					&& (state.getValue(BlockOldLeaf.VARIANT) == BlockPlanks.EnumType.JUNGLE); 
			});
	}
	
	@Override
	public void createSpecies() {
		setCommonSpecies(new SpeciesSmallJungle(this));
		getCommonSpecies().generateSeed();
	}
	
	
	/*
	 * The following code taken directly from the dynamic trees mod 
	 */
	@Override
	public boolean onTreeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {	
		//Place Cocoa Pod if we are holding Cocoa Beans
		if(heldItem != null) {
			if(heldItem.getItem() == Items.DYE && heldItem.getItemDamage() == 3) {
				BlockBranch branch = TreeHelper.getBranch(state);
				if(branch != null && branch.getRadius(state) == 8) {
					if(side != EnumFacing.UP && side != EnumFacing.DOWN) {
						pos = pos.offset(side);
					}
					if (world.isAirBlock(pos)) {
						IBlockState cocoaState = ModBlocks.blockFruitCocoa.getStateForPlacement(world, pos, side, hitX, hitY, hitZ, 0, player);
						EnumFacing facing = cocoaState.getValue(BlockHorizontal.FACING);
						world.setBlockState(pos, ModBlocks.blockFruitCocoa.getDefaultState().withProperty(BlockHorizontal.FACING, facing), 2);
						if (!player.capabilities.isCreativeMode) {
							heldItem.shrink(1);
						}
						return true;
					}
				}
			}
		}		
		//Need this here to apply potions or bone meal.
		return super.onTreeActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}
}