package jaredbgreat.climaticbiome.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class Recipes {
	
	public static void register() {	
		OreDictionary.registerOre("logWood", ModBlocks.pineLog);	
		OreDictionary.registerOre("plankWood", ModBlocks.pinePlanks);
		OreDictionary.registerOre("treeLeave", ModBlocks.pineNeedle);	
		OreDictionary.registerOre("treeSapling", ModBlocks.pineSapling);
		
		
		//GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.pinePlanks, 4, 0), 
		//		ModBlocks.pineLog);
		
	}
	

}
