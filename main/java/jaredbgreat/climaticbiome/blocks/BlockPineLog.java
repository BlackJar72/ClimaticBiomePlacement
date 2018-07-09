package jaredbgreat.climaticbiome.blocks;

import jaredbgreat.climaticbiome.Info;
import jaredbgreat.climaticbiome.blocks.itemblocks.ItemPineLog;
import jaredbgreat.climaticbiome.util.BlockRegistrar;
import jaredbgreat.climaticbiome.util.ItemRegistrar;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

//For reasons I don't understand I can't instantiate the vanilla class, but can do this!?
public class BlockPineLog extends BlockLogBase {
	
    public BlockPineLog() {
        setUnlocalizedName(Info.ID + ".pine_log");
		setRegistryName(Info.ID, "pine_log");		
		setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
		BlockRegistrar.addBlock(this);
		ItemRegistrar.addItem(new ItemPineLog(this));
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


    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
    }
	
}
