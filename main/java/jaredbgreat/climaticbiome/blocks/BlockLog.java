package jaredbgreat.climaticbiome.blocks;

import static net.minecraft.block.BlockLog.LOG_AXIS;
import jaredbgreat.climaticbiome.Info;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

//For reasons I don't understand I can't instantiate the vanilla class, but can do this!?
public class BlockLog extends net.minecraft.block.BlockLog {
	private final String name;
	
    public BlockLog(String name) {
        this.name = name;
        setUnlocalizedName(name);
		setRegistryName(Info.ID + ":" + name);
		setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
    }
    
    
    public IBlockState getBasicState() {
    	return blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y);
    }
	
    

    public int getMetaFromState(IBlockState state) {
        switch ((EnumAxis)state.getValue(LOG_AXIS)) {
            case X: return 0x04;
            case Y: return 0x0;
            case Z: return 0x08;
            case NONE: return 0x0C;
            default: return 0x0;
        }
    }

    
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        switch (meta & 0x0C) {
            case 0x0:
                state = state.withProperty(LOG_AXIS, EnumAxis.Y);
                break;
            case 0x04:
            	state = state.withProperty(LOG_AXIS, EnumAxis.X);
                break;
            case 0x08:
                state = state.withProperty(LOG_AXIS, EnumAxis.Z);
                break;
            case 0x0C:
                state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
                break;
        }
        return state;
    }    


    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
    }
	
	
	public String getName() {
		return name;
	}
	
}
