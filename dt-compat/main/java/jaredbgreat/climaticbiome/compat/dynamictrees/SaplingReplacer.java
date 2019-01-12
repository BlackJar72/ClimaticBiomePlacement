package jaredbgreat.climaticbiome.compat.dynamictrees;

import jaredbgreat.climaticbiome.util.BlockRegistrar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.trees.Species;

/**
 * This is supposed to replace placed sappling with dynamic trees; 
 * I have no idea why it doesn't work.
 */
//@Mod.EventBusSubscriber(modid="climaticbiomesdt")
public class SaplingReplacer {

	@SubscribeEvent
	public void onPlaceSaplingEvent(PlaceEvent event) {
		IBlockState state = event.getPlacedBlock();		
		Species species = null;
		
		if(state.getBlock() == BlockRegistrar.blockPineSappling) {
			species = TreeRegistry.findSpecies(new ResourceLocation(Info.ID, "pine"));
		}
		
		if(species != null) {
			event.getWorld().setBlockToAir(event.getPos());
			if(!species.plantSapling(event.getWorld(), event.getPos())) {
				double x = event.getPos().getX() + 0.5;
				double y = event.getPos().getY() + 0.5;
				double z = event.getPos().getZ() + 0.5;
				EntityItem itemEntity = new EntityItem(event.getWorld(), 
						x, y, z, species.getSeedStack(1));
				event.getWorld().spawnEntity(itemEntity);
			}
		}
	}
	
}
