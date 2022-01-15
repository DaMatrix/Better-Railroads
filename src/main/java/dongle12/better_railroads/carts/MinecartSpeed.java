package dongle12.better_railroads.carts;

import dongle12.better_railroads.items.ModItems;
import dongle12.better_railroads.util.ConfigHandler;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MinecartSpeed extends EntityMinecartEmpty {
    private static final int[][][] MATRIX = { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };

    public MinecartSpeed(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public MinecartSpeed(World world) {
        super(world);
    }

    @Override
    public float getMaxCartSpeedOnRail() {
        return ConfigHandler.MAX_CART_SPEED;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    public void killMinecart(DamageSource source) {
        this.setDead();

        if (this.world.getGameRules().getBoolean("doEntityDrops")) {
            ItemStack itemstack = new ItemStack(ModItems.speed_cart, 1);

            if (this.hasCustomName()) {
                itemstack.setStackDisplayName(this.getCustomNameTag());
            }

            this.entityDropItem(itemstack, 0.0F);
        }
    }

    @Override
    protected void moveAlongTrack(BlockPos pos, IBlockState state) {
        //DaPorkchop_: totally redesigned implementation which allows the minecart to travel more than one block per tick without breaking stuff

        double origMotionX = this.motionX;
        double origMotionZ = this.motionZ;
        double origMotionSq = origMotionX * origMotionX + origMotionZ * origMotionZ;
        if (!ConfigHandler.EXPERIMENTAL_FAST_CART_HANDLING || origMotionSq <= 1.0d) { //speed is within vanilla limits, keep it
            super.moveAlongTrack(pos, state);
            return;
        }

        final double maxStep = 0.1d;

        double totalMotion = 0.0d;

        double totalDistance = Math.sqrt(origMotionSq);
        double distanceCovered = 0.0d;
        do {
            int railX = MathHelper.floor(this.posX);
            int railY = MathHelper.floor(this.posY);
            int railZ = MathHelper.floor(this.posZ);
            if (BlockRailBase.isRailBlock(this.world, new BlockPos(railX, railY - 1, railZ))) {
                railY--;
            }

            pos = new BlockPos(railX, railY, railZ);
            state = this.world.getBlockState(pos);

            if (!BlockRailBase.isRailBlock(state)) {
                break;
            }

            Vec3d lastPos = new Vec3d(this.posX, this.posY, this.posZ);

            //normalize vector
            double f = MathHelper.fastInvSqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.motionX *= f;
            this.motionZ *= f;

            //scale to step velocity
            this.motionX *= maxStep;
            this.motionZ *= maxStep;

            //this.motionX = MathHelper.clamp(this.motionX, -maxStep, maxStep);
            //this.motionZ = MathHelper.clamp(this.motionZ, -maxStep, maxStep);
            super.moveAlongTrack(pos, state);

            totalMotion += Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

            distanceCovered += new Vec3d(this.posX, this.posY, this.posZ).distanceTo(lastPos);
        } while (distanceCovered < totalDistance);

        //normalize vector
        double f = MathHelper.fastInvSqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.motionX *= f;
        this.motionZ *= f;

        //scale to current velocity
        this.motionX *= Math.min(totalMotion, ConfigHandler.MAX_CART_SPEED);
        this.motionZ *= Math.min(totalMotion, ConfigHandler.MAX_CART_SPEED);
    }
}
