package jaredbgreat.climaticbiomes.generation.cache;

import net.minecraft.server.MinecraftServer;

public class AbstractCachable implements ICachable {
    private final Coords coords;
    private long timestamp;


    public AbstractCachable(int x, int z) {
        coords = new Coords(x, z);
        timestamp = System.currentTimeMillis();
    }


    public AbstractCachable(Coords coords) {
        this.coords = coords;
        timestamp = System.currentTimeMillis();
    }


    @Override
    public void use() {
        timestamp = System.currentTimeMillis();
    }


    @Override
    public boolean isOldData() {
        long t = System.currentTimeMillis() - timestamp;
        return ((t > 120000) || (t < 0)); // 10 minutes
    }


    @Override
    public Coords getCoords() {
        return coords;
    }


    public String toString() {
        return super.toString() + " at " + coords.toString();
    }

}
