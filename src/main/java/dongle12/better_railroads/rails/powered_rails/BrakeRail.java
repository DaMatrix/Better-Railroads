package dongle12.better_railroads.rails.powered_rails;

import dongle12.better_railroads.blocks.BrakeRailEntity;
import dongle12.better_railroads.util.ConfigHandler;
import dongle12.better_railroads.util.RailUtil;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BrakeRail extends PoweredRail implements ITileEntityProvider {
    public BrakeRail() {
        super("brake_rail");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new BrakeRailEntity();
    }

    private BrakeRailEntity getTE(World world, BlockPos pos) {
        return (BrakeRailEntity) world.getTileEntity(pos);
    }

    @Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
        //The default speed of a rail that
        float retSpeed = 0.4f;
        //Look for Slopes upward
        for (EnumFacing side : EnumFacing.HORIZONTALS) {
            //Look at the next block
            BlockPos nextPos = pos.offset(side);
            //Boolean to see if a rail has been found
            boolean foundRail = RailUtil.isRail(world, nextPos);
            //If no rail is found, check the next block up or down to find a rail
            if (!foundRail) {
                if (RailUtil.isRail(world, nextPos.down())) {
                    foundRail = true;
                    nextPos = nextPos.up();
                } else if (RailUtil.isRail(world, nextPos.down())) {
                    foundRail = true;
                    nextPos = nextPos.down();
                }
            }
            if (foundRail) {
                //Get the current direction of the rail found
                BlockRailBase.EnumRailDirection dir = RailUtil.RailDirection(world, cart, pos);
                BlockRailBase.EnumRailDirection dirNext = RailUtil.RailDirection(world, cart, nextPos);

                //If the direction is not null, and the rail is ascending, start to slow
                if ((dir != null && dir.isAscending()) || (dirNext != null && dirNext.isAscending())) {
                    retSpeed = .8f;
                } else {
                    retSpeed = super.getRailMaxSpeed(world, cart, pos) * ConfigHandler.POWERED_SPEED_RAIL_MULTIPLIER;
                }
            }
        }
        return retSpeed;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public ItemBlock getItemBlock() {
        return itemBlock;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //items below are needed to extend from BlockRailPowered but in this case used to override the speed of the cart to stop it//
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onMinecartPass(World world, EntityMinecart cart, BlockPos pos) {
        BrakeRailEntity te = this.getTE(world, pos);

        if (world.getBlockState(pos).getValue(BlockRailPowered.POWERED)) {
            cart.motionX = te.motionX;
            cart.motionZ = te.motionZ;
        } else {
            if (cart.motionX != 0.0d || cart.motionZ != 0.0d) {
                te.motionX = cart.motionX;
                te.motionZ = cart.motionZ;
                cart.posX = pos.getX() + 0.5d;
                cart.posZ = pos.getZ() + 0.5d;
                cart.motionX = cart.motionZ = 0.0d;
            }
        }
    }
}
