package jaredbgreat.climaticbiome.blocks.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * This class is totally broken at this point and should not be used.
 */
public class ItemMultiblock extends ItemMultiTexture {
	public final String[] names;

	public ItemMultiblock(Block block, int number, ItemMultiTexture.Mapper namer) {
		super(block, block, namer);
		setMaxDamage(15);
		this.setHasSubtypes(true);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
        names = new String[number];
        for(int i = 0; i < number; i++) {
        	names[i] = block.getUnlocalizedName() + i;
        }
	}
	
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		for(int i = 0; i < names.length; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return names[stack.getMetadata()];
	}
	
	
	@Override
    public int getMetadata(int damage) { 
        return damage;
    }
	
	
	@Override
	public boolean isDamageable() {
		return false;
	}
	
	
	@Override
	public boolean isRepairable() {
		return false;
	}
	
	
	@Override
	public boolean getHasSubtypes() {
		return true;
	}
	
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}
	
	
	

}
