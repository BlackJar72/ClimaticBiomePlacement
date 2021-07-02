package jaredbgreat.climaticbiome.items;

import jaredbgreat.climaticbiome.Info;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * This may not be used, as I'm largely treating the actual blocks as the
 * fuel source.
 * 
 * @author JaredBGreat
 */
public class ItemPeatBrick extends ModItemBase {	
	
	public ItemPeatBrick() {
		setCreativeTab(CreativeTabs.MISC);
		setRegistryName(Info.ID, "peatbrick");
		setUnlocalizedName(Info.ID + ".peatbrick");
        setMaxDamage(0);
	}
    
    
//    @Override
//	public int getItemBurnTime(ItemStack stack) {
//		return 1600;    	
//    }

}
