package jaredbgreat.climaticbiomes.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler {


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        ItemRegistrar.registerItems(event);
    }


    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        BlockRegistrar.setupBlocks(event);
    }


    @SubscribeEvent
    public static void registerBimoes(final RegistryEvent.Register<Biome> event) {
        BiomeRegistrar.setupBlocks(event);
    }

}
