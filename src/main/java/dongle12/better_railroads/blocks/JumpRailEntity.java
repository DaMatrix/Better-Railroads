package dongle12.better_railroads.blocks;

import dongle12.better_railroads.util.ConfigHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JumpRailEntity extends TileEntity {
	
   private int jumpHeight = 0;

    public int decrement() {
    	if(jumpHeight > 0){
        	jumpHeight--;
    	}
        markDirty();
        return jumpHeight;
    }
    
    public int increment() {
    	if(jumpHeight < ConfigHandler.MAX_JUMP_RAIL_STRENGTH){
        	jumpHeight++;
    	}
        markDirty();
        return jumpHeight;
    }
    
    public int getJumpHeight(){
    	return jumpHeight;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        jumpHeight = compound.getInteger("jumpHeight");
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("jumpHeight", jumpHeight);
        return compound;
    }
    
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        return oldState.getBlock() != newSate.getBlock();
    }

    
}

