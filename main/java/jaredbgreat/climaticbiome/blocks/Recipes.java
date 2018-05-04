package jaredbgreat.climaticbiome.blocks;

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
				
		GameRegistry.addSmelting(new ItemStack(ModBlocks.pineLog, 1, 0), 
				new ItemStack(Items.COAL, 1, 1), 0.15f);
		
	}
	

}
