package dongle12.better_railroads.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BrakeRailEntity extends TileEntity {
    public boolean lock = true;
    public double motionX;
    public double motionZ;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.lock = compound.getBoolean("lock");
        this.motionX = compound.getDouble("motionX");
        this.motionZ = compound.getDouble("motionZ");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("lock", this.lock);
        compound.setDouble("motionX", this.motionX);
        compound.setDouble("motionZ", this.motionZ);
        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}

