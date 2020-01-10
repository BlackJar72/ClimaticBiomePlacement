package jaredbgreat.climaticbiome.util;

public class ModMath {
        
    public static int modRight(int a, int b) {
    	return (a & 0x7fffffff) % b;
    }

}
