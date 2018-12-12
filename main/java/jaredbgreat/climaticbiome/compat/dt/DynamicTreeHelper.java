package jaredbgreat.climaticbiome.compat.dt;

import jaredbgreat.climaticbiome.ConfigHandler;
import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.compat.dt.trees.TreePine;
import jaredbgreat.climaticbiome.compat.dt.trees.TreeSmallJungle;
import jaredbgreat.climaticbiome.util.BlockRegistrar;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.WorldGenRegistry;
import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

/*
 * This is so broken its ridiculous!
 */
public class DynamicTreeHelper {
	public static TreeFamily pineTree;
	public static Species floridaPine;
	
	public static TreeFamily smallJungle;
	public static Species jungleTree;
	
	public static ILeavesProperties pineLeavesProperties;
	public static ILeavesProperties jungleLeavesProperties;
	

	public static void preInit() {
		if(ConfigHandler.useDT) {
			IForgeRegistry<Block> blockRegistry = GameRegistry.findRegistry(Block.class);
			IForgeRegistry<Item> itemRegistry = GameRegistry.findRegistry(Item.class);
			
			pineLeavesProperties 
				= new LeavesProperties(BlockRegistrar.blockPineNeedles.getDefaultState());
			LeavesPaging.getLeavesBlockForSequence(Info.ID, 0, pineLeavesProperties);
			pineTree = new TreePine();
			pineTree.registerSpecies(Species.REGISTRY);
			
			ArrayList<Block> treeBlocks = new ArrayList<>();
			pineTree.getRegisterableBlocks(treeBlocks);
			treeBlocks.addAll(LeavesPaging.getLeavesMapForModId(Info.ID).values());
			blockRegistry.registerAll(treeBlocks.toArray(new Block[treeBlocks.size()]));
					
			ArrayList<Item> treeItems = new ArrayList<>();
			pineTree.getRegisterableItems(treeItems);
			itemRegistry.registerAll(treeItems.toArray(new Item[treeItems.size()]));
		}
	}
	
	
	public static void init() {
		if(ConfigHandler.useDT) {
			floridaPine = TreeRegistry.findSpecies(new ResourceLocation(Info.ID, "pine"));
		}		
		// Lastly ... I guess ....
		WorldGenRegistry.registerBiomeDataBasePopulator(new DataBasePop());
	}
	
	
	public static void postInit() {}
	
	
	@SideOnly(Side.CLIENT)
	public static void clientPreInit() {
		ModelHelper.regModel(pineTree.getDynamicBranch());
		ModelHelper.regModel(pineTree.getCommonSpecies().getSeed());
		ModelHelper.regModel(pineTree);
		ModelHelper.regModel(smallJungle.getDynamicBranch());
		ModelHelper.regModel(smallJungle.getCommonSpecies().getSeed());
		ModelHelper.regModel(smallJungle);
		LeavesPaging.getLeavesMapForModId(Info.ID).forEach((key,leaves) 
				-> ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder()
					.ignore(BlockLeaves.DECAYABLE).build()));
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void clientInit() {
		final int magenta = 0x00FF00FF;
		
		for (BlockDynamicLeaves leaves : LeavesPaging.getLeavesMapForModId(Info.ID).values()) {
			ModelHelper.regColorHandler(leaves, new IBlockColor() {
				@Override
				public int colorMultiplier(IBlockState state, IBlockAccess worldIn, 
						BlockPos pos, int tintIndex) {					
					Block block = state.getBlock();

					if(TreeHelper.isLeaves(block)) {
						return ((BlockDynamicLeaves) block).getProperties(state)
								.foliageColorMultiplier(state, worldIn, pos);
					}
					return magenta;
				}
			});
		}
	}
	
	
	public static Item getPineSeed() {
		return pineTree.getCommonSpecies().getSeed();
	}
	
	
	public static Item getJungleSeed() {
		return smallJungle.getCommonSpecies().getSeed();
	}
	
}
