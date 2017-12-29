package jaredbgreat.climaticbiome.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class Recipes {
	
	public static void register() {	
		OreDictionary.registerOre("logWood", ModBlocks.pineLog);	
		OreDictionary.registerOre("plankWood", ModBlocks.pinePlanks);	
		OreDictionary.registerOre("treeLeave", ModBlocks.pineNeedle);	
		OreDictionary.registerOre("treeSapling", ModBlocks.pineSapling);
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.pinePlanks, 4, 0), 
				ModBlocks.pineLog);
		
		
//		GameRegistry.addRecipe(new ItemStack(ModBlocks.pinePlanks, 4, 0),
//				"L",
//				'L', new ItemStack(ModBlocks.pineLog, 1, 0)
//		);
//		
//		GameRegistry.addRecipe(new ItemStack(Items.STICK, 4, 0),
//				"P",
//				"P",
//				'P', new ItemStack(ModBlocks.pinePlanks, 1, 0)
//		);
//		
//		 
//		GameRegistry.addRecipe(new ItemStack(Blocks.CRAFTING_TABLE, 4, 0),
//				"PP",
//				"PP",
//				'P', new ItemStack(ModBlocks.pinePlanks, 1, 0)
//		);
		
	}
	

}
