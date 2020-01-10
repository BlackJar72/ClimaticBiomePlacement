package jaredbgreat.climaticbiomes.generation.cache;

import jaredbgreat.climaticbiomes.util.SpatialNoise;

public class MutableCoords {
    private static final SpatialNoise NH = new SpatialNoise(0xADD5C0DE);
    private int x, z;


    public MutableCoords() {}


    public MutableCoords init(int x, int z) {
        this.x = x;
        this.z = z;
        return this;
    }


    @Override
    public boolean equals(Object other) {
        if(other instanceof MutableCoords) {
            MutableCoords o = (MutableCoords)other;
            return ((o.x == x) && (o.z == z));
        }
        return false;
    }


    public boolean equals(int x, int z) {
        return (this.x == x) && (this.z == z);
    }


    @Override
    public int hashCode() {
        return NH.intFor(x, z, 0);
    }


    public static int hashCoords(int x, int z) {
        return NH.intFor(x, z, 0);
    }


    public int getX() {
        return x;
    }


    public int getZ() {
        return z;
    }


    public String toString() {
        return "(" + x + ", " + z + ")";
    }

}
