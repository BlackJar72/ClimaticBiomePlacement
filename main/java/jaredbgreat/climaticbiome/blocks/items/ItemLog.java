package jaredbgreat.climaticbiome.blocks.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public class ItemLog extends ItemBlock {

	public ItemLog(Block block) {
		super(block);
        setMaxDamage(0);
        setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
	}
	

    public int getMetadata(int damage) { 
        return damage;
    }

}
