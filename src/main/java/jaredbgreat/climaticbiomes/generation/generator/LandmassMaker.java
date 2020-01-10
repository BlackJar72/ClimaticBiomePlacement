package jaredbgreat.climaticbiomes.generation.generator;

import jaredbgreat.climaticbiomes.configuration.ClimaticWorldSettings;
import jaredbgreat.climaticbiomes.configuration.ConfigHandler;
import jaredbgreat.climaticbiomes.util.HeightNoise;
import jaredbgreat.climaticbiomes.util.SpatialNoise;

public class LandmassMaker {
    SpatialNoise random;
    int regx, regz, size;
    SizeScale scale;
    double currentScale;
    BasinNode[] basins;
    int xoff, zoff;


    LandmassMaker(int rx, int ry, SpatialNoise rand,
                  BasinNode[] basinAr, SizeScale sc, int startW,
                  int xoffIn, int zoffIn) {
        random = rand;
        size = startW * sc.whole;
        regx = rx;
        regz = ry;
        scale = sc;
        currentScale = 1.0;
        basins = basinAr;
        xoff = xoffIn;
        zoff = zoffIn;
    }


    public ChunkTile[] generate(ClimaticWorldSettings settings) {
        double beachThreshold = 0.70;
        ChunkTile[] out = new ChunkTile[size * size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                out[(i * size) + j]
                        = new ChunkTile(i, j, xoff, zoff);
            }
        HeightNoise heightmaker
                = new HeightNoise(random, size, 16 * scale.whole, 1.0, regx, regz);
        double[][] heights = heightmaker.process(0);
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                out[(i * size) + j].height
                        = edgeFix(out[(i * size) + j],
                        BasinNode.summateEffect(basins,
                                out[(i * size) + j],
                                scale.inv));
                out[(i * size) + j].val = (int)out[(i * size) + j].height;
                out[(i * size) + j].height /= 10.0;
                out[(i * size) + j].height = ((out[(i * size) + j].height
                        + (heights[i][j] / 2.0) + 0.5)
                        * out[(i * size) + j].height)
                        + heights[i][j];
            }

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(out[(i * size) + j].height > 0.6) {
                    out[(i * size) + j].rlBiome = 1;
                    if(settings.extraBeaches ||
                            out[(i * size) + j].height < beachThreshold) {
                        out[(i * size) + j].beach = true;
                    }
                } else {
                    out[(i * size) + j].rlBiome = 0;
                }
            }

        return out;
    }


    protected double edgeFix(ChunkTile t, double val) {
        if(t.x < (10 * scale.whole)) {
            val += ((t.x - (10 * scale.whole)) / (2 * scale.whole));
        } else if(t.x >= (size - (10 * scale.whole))) {
            val -= ((t.x - size + (10 * scale.whole)) / (2 * scale.whole));
        }
        if(t.z < (10 * scale.whole)) {
            val += ((t.z - (10 * scale.whole)) /  (2 * scale.whole));
        } else if(t.z >= (size - (10 * scale.whole))) {
            val -= ((t.z - size + (10 * scale.whole)) / (2 * scale.whole));
        }
        return val;
    }


}
