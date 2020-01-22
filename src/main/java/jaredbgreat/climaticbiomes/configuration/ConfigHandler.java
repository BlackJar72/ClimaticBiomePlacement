package jaredbgreat.climaticbiomes.configuration;

import jaredbgreat.climaticbiomes.generation.generator.SizeScale;


public final class ConfigHandler {

    public static boolean useBoPTable = true;

    static boolean addIslands = true;
    static boolean deepSand = true;
    static boolean rockyScrub = true;
    static boolean hasRivers = true;

    static int        biomeSize  = 16;
    static SizeScale  regionSize = SizeScale.X1;
    static boolean    forceWhole = false;
    static double     sisize     = 6.0;
    static int        mode       = 1;

    public static boolean failfast = false;


    public static  int     modes     = 3;
    public static  boolean includeSI = false;
}
