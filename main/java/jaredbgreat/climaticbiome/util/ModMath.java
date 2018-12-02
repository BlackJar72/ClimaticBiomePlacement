package jaredbgreat.climaticbiome.util;

public class ModMath {
        
    public static int modRight(int a, int b) {
    	int out = a % b;
    	if(a < 0) {
    		out += b;
    	}
    	if(out > b) {
    		System.out.print(a + " % " + b + " = " +out);
    	}
    	return out;
    }

}
