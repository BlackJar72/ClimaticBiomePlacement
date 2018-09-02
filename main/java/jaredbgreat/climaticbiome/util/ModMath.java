package jaredbgreat.climaticbiome.util;

public class ModMath {
        
    public static int modRight(int a, int b) {
    	int out = a % b;
    	if(a < 0) {
    		out += b;
    	}
    	return out;
    }

}
