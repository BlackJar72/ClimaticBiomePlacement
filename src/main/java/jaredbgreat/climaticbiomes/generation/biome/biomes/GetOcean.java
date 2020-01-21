package jaredbgreat.climaticbiomes.generation.biome.biomes;

import jaredbgreat.climaticbiomes.compat.userdef.DefReader;
import jaredbgreat.climaticbiomes.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.generation.biome.*;
import jaredbgreat.climaticbiomes.generation.generator.ChunkTile;
import jaredbgreat.climaticbiomes.util.BiomeRegistrar;


public class GetOcean implements IBiomeSpecifier {
    private ClimaticWorldSettings  settings;
    private static GetOcean oceans;
    private int nearEdge, farEdge;
    private GetOcean(ClimaticWorldSettings settings) {
        super();
        this.settings = settings;
        nearEdge = 5 + settings.regionSize.whole;
        farEdge  = (256 * settings.regionSize.whole) - 6 - settings.regionSize.whole;
        init();
    }
    private static int icecap;
    BiomeList frozen;
    BiomeList cold;
    BiomeList cool;
    BiomeList warm;
    BiomeList hot;
    BiomeList dfrozen;
    BiomeList dcold;
    BiomeList dcool;
    BiomeList dwarm;
    BiomeList dhot;
    IBiomeSpecifier islands1; // Main land biomes
    IBiomeSpecifier islands2; // Special island-only biomes
    IBiomeSpecifier beaches;
    IBiomeSpecifier coastal;


    public void init() {
        icecap = AbstractTerminalSpecifier.getIdForBiome(BiomeRegistrar.iceCap);
        // Shallows
        frozen = new BiomeList();
        cold   = new BiomeList();
        cool   = new BiomeList();
        warm   = new BiomeList();
        hot    = new BiomeList();
        //Deeps
        dfrozen = new BiomeList();
        dcold   = new BiomeList();
        dcool   = new BiomeList();
        dwarm   = new BiomeList();
        dhot    = new BiomeList();
        // Islands
        islands1 = BiomeClimateTable.getLandTable();
        islands2 = GetIslands.getIslands();
        // Beaches
        beaches = GetBeach.getBeach();
        coastal = GetCoastal.getCoastal();
        // Add biomes
        DefReader.readBiomeData(frozen,  "OceanFrozen.cfg");
        DefReader.readBiomeData(cold,    "OceanCold.cfg");
        DefReader.readBiomeData(cool,    "OceanCool.cfg");
        DefReader.readBiomeData(warm,    "OceanWarm.cfg");
        DefReader.readBiomeData(hot,     "OceanHot.cfg");
        DefReader.readBiomeData(dfrozen, "DeepOceanFrozen.cfg");
        DefReader.readBiomeData(dcold,   "DeepOceanCold.cfg");
        DefReader.readBiomeData(dcool,   "DeepOceanCool.cfg");
        DefReader.readBiomeData(dwarm,   "DeepOceanWarm.cfg");
        DefReader.readBiomeData(dhot,    "DeepOceanHot.cfg");
        if(warm.isEmpty()) {
            warm.addItem(new LeafBiome(0));
        }
        if(hot.isEmpty()) {
            hot.addItem(new LeafBiome(0));
        }
        if(dwarm.isEmpty()) {
            dwarm.addItem(new LeafBiome(24));
        }
        if(dhot.isEmpty()) {
            dhot.addItem(new LeafBiome(24));
        }
        if(cool.isEmpty()) {
            cool.addItem(new LeafBiome(0));
        }
        if(dcool.isEmpty()) {
            dcool.addItem(new LeafBiome(24));
        }
        if(frozen.isEmpty()) {
            frozen.addItem(new LeafBiome(10));
        }
        if(dfrozen.isEmpty()) {
            dfrozen.addItem(new LeafBiome(10));
        }
        // MUST BE LAST, ALWAYS!!!
        fixOceans();
    }


    @Override
    public long getBiome(ChunkTile tile) {
        if(!tile.isRiver() && !swampy(tile)) {
            if(tile.isBeach()) {
                return beaches.getBiome(tile);
            } else if(tile.isCoastal()) {
                return coastal.getBiome(tile);
            }
        }
        int temp = tile.getTemp();
        int seed = tile.getBiomeSeed();
        int iceNoise = tile.getNoise();
        tile.nextBiomeSeed();
        if(settings.addIslands && (tile.getHeight() < 0.5)
                && (tile.getVal() < 3)
                && ((seed % 5) == 0)
                && notNearEdge(tile)) {
            int noise = tile.getNoise();
            if((seed % 31) == 0) {
                if((tile.getTemp() > 9) && (tile.getTemp() < 19)
                        && (tile.getWet() > 3)) {
                    if(noise < 5) {
                        return 14;
                    }
                    if(noise < 7) {
                        return 15;
                    }
                }
                return 0;
            } else if(settings.addIslands && ((seed & 1) == 0)) {
                if(noise > (4 + (seed % 3))) {
                    return islands1.getBiome(tile.nextBiomeSeed());
                }
            } else if(settings.addIslands) {
                if(noise > (seed % 3)) {
                    return islands2.getBiome(tile.nextBiomeSeed());
                }
            } else {
                return getForIsland(tile);
            }
        } else if((tile.getHeight()) < 0.2) {
            return getDeepOcean(tile, temp, iceNoise);
        } else if(tile.getHeight() < 0.5) {
            return getShallowOcean(tile, temp, iceNoise);
        }
        return coastal.getBiome(tile);
    }


    public long getDeepOcean(ChunkTile tile, int temp, int iceNoise) {
        if(((iceNoise / 2) - temp) > -2) {
            if(((iceNoise / 2) - temp) > 0) {
                return icecap;
            }
            return dfrozen.getBiome(tile);
        }
        if(temp < 10) {
            return dcold.getBiome(tile);
        }
        if(temp < 16) {
            return dcool.getBiome(tile);
        }
        if(temp < 20) {
            return dwarm.getBiome(tile);
        }
        return dhot.getBiome(tile);
    }


    public long getShallowOcean(ChunkTile tile, int temp, int iceNoise) {
        if(((iceNoise / 2) - temp) > -2) {
            if(((iceNoise / 2) - temp) > 0) {
                return icecap;
            }
            return frozen.getBiome(tile);
        }
        if(temp < 10) {
            return cold.getBiome(tile);
        }
        if(temp < 16) {
            return cool.getBiome(tile);
        }
        if(temp < 20) {
            return warm.getBiome(tile);
        }
        return hot.getBiome(tile);
    }


    public static GetOcean getOcean(ClimaticWorldSettings settings) {
        if(oceans == null) {
            oceans = new GetOcean(settings);
        }
        return oceans;
    }


    public static GetOcean getOceanForIslands() {
        return oceans;
    }


    /**
     * This fixes possible problems with ocean,
     * specifically, makes sure there are no 
     * oceans types empty.  This way mods can 
     * add temperature specific oceans, while 
     * not relying on them.
     */
    private void fixOceans() {
        if(warm.isEmpty()) {
            warm = cool;
        }
        if(hot.isEmpty()) {
            hot = warm;
        }
        if(cold.isEmpty()) {
            cold = cool;
        }
        if(frozen.isEmpty()) {
            // Should never be true, but just in case.
            frozen = cold;
        }
        if(dcool.isEmpty()) {
            // Should never be true, but just in case.
            dcool = cool;
        }
        if(dwarm.isEmpty()) {
            dwarm = dcool;
        }
        if(dhot.isEmpty()) {
            dhot = dwarm;
        }
        if(dcold.isEmpty()) {
            dcold = dcool;
        }
        if(dfrozen.isEmpty()) {
            // Should never be true, but just in case.
            dfrozen = dcold;
        }
    }

    /**
     * This will get the oceans surrounding islands.
     *
     * @param tile
     * @return
     */
    public long getForIsland(ChunkTile tile) {
        tile.nextBiomeSeed();
        return getShallowOcean(tile, tile.getTemp(), tile.getNoise());
    }


    private boolean notNearEdge(ChunkTile tile) {
        int x = tile.getX();
        int z = tile.getZ();
        return ((x > nearEdge) && (x < farEdge)) && ((z > nearEdge) && (z < farEdge));
    }


    @Override
    public boolean isEmpty() {
        return false;
    }


    private boolean swampy(ChunkTile tile) {
        return ((tile.getTemp() > 7)
                && ((tile.getWet() - tile.getVal() - tile.getHeight()) > 0));
    }


    public void listOut() {

    }


}
