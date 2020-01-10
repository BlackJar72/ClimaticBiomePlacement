package jaredbgreat.climaticbiomes;

import jaredbgreat.climaticbiomes.proxy.ClientProxy;
import jaredbgreat.climaticbiomes.testing.TestWorldType;
import jaredbgreat.climaticbiomes.util.BlockRegistrar;
import jaredbgreat.climaticbiomes.util.ItemRegistrar;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Info.ID)
public class ClimaticBiomes {
    private static final Logger LOGGER = LogManager.getLogger();
    public static ClimaticBiomes instance;

    // TODO: Delete this when ready to create the Climatic World Type
    // public static final WorldType testWorlds = new TestWorldType();


    public ClimaticBiomes() {
        instance = this;
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::fowardToClientProxy);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Starting Climatic Biomes");
    }


    private void fowardToClientProxy(final FMLClientSetupEvent event) {
        ClientProxy.getProxy().acceptEventForward(event);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {/*Do Nothing -- for now...*/}


    public static Logger getLogger() {
        return LOGGER;
    }


}
