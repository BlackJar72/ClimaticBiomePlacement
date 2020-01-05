package jaredbgreat.climaticbiomes.proxy;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy implements IProxy {
    private static ClientProxy clientProxy;


    public static ClientProxy getProxy() {
        if(clientProxy == null) {
            clientProxy = new ClientProxy();
        }
        return clientProxy;
    }


    public void acceptEventForward(final FMLClientSetupEvent event) {
        // TODO:  Anything?
    }
}
