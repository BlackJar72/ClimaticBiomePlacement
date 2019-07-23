package jaredbgreat.climaticbiome.compat.dynamictrees;

import jaredbgreat.climaticbiome.util.BlockRegistrar;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.WorldGenRegistry.BiomeDataBasePopulatorRegistryEvent;
import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;


@Mod.EventBusSubscriber(modid="climaticbiomesdt")
public class DynamicTreeHelper {
	public static TreePine pineTree;
	public static Species pineSpecies;
	
	public static TreeFamily jungle;
	public static Species smallJungle;
	
	public static ILeavesProperties pineLeavesProperties;
	public static ILeavesProperties jungleLeavesProperties;
	

	public static void preInit() {
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
	
	
	public static void init() {
		pineSpecies = TreeRegistry.findSpecies(
				new ResourceLocation(jaredbgreat.climaticbiome.Info.ID, "pine"));
	}
	
	
	@SubscribeEvent
    public static void registerDataBasePopulators(final BiomeDataBasePopulatorRegistryEvent event) {
        event.register(new DataBasePop());
    }
	
	
	public static void postInit() {}
	
	
	@SideOnly(Side.CLIENT)
	public static void clientPreInit() {
		ModelHelper.regModel(pineTree.getDynamicBranch());
		ModelHelper.regModel(pineTree.getCommonSpecies().getSeed());
		ModelHelper.regModel(pineTree);
		LeavesPaging.getLeavesMapForModId(Info.ID).forEach((key,leaves) 
				-> ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder()
					.ignore(BlockLeaves.DECAYABLE).build()));
	}
	
	
	/*
	 * Code for this method taken from Rustic mod by Mangoose.
	 */
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
	
}
