package dongle12.better_railroads.carts;

import dongle12.better_railroads.util.ConfigHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class TrainCart extends EntityMinecartFurnace {

	private int fuel;
	public double localPushX;
	public double localPushZ;

	//1 is gunpowder, 2 is blaze rod, 3 is coal. This is to make it easier to NBT write
	private int fuelUsed;
	private boolean fueled;
	
	public TrainCart(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	public TrainCart(World world){
		super(world);
	}

	@Override
	public float getMaxCartSpeedOnRail(){
		return ConfigHandler.MAX_CART_SPEED;
	}
	
	@Override
    public boolean canBePushed()
    {
		return true;
    }
	
	@Override
	public void onUpdate()
    {
        super.onUpdate();

        if (this.fuel > 0)
        {
            --this.fuel;
        }

        if (this.fuel <= 0)
        {
            this.localPushX = 0.0D;
            this.localPushZ = 0.0D;
            fueled = false;
            fuelUsed = 0;
        }

    }
	

	@Override
    public void killMinecart(DamageSource source)
    {
        super.killMinecart(source);

        if (!source.isExplosion() && this.world.getGameRules().getBoolean("doEntityDrops"))
        {
            this.entityDropItem(new ItemStack(Blocks.FURNACE, 1), 0.0F);
        }
    }

	@Override
    protected void moveAlongTrack(BlockPos pos, IBlockState state)
    {
        super.moveAlongTrack(pos, state);

        double d0 = this.localPushX * this.localPushX + this.localPushZ * this.localPushZ;


        if (d0 > 1.0E-4D && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001D)
        {
        	/*
            d0 = (double)MathHelper.sqrt(d0);
            this.localPushX /= d0;
            this.localPushZ /= d0;


            else
            {
                double d1 = d0 / this.getMaximumSpeed();
                this.localPushX *= d1;
                this.localPushZ *= d1;
            }
            */
            if (this.localPushX * this.motionX + this.localPushZ * this.motionZ < 0.0D)
            {
                this.localPushX = 0.0D;
                this.localPushZ = 0.0D;
            }
        }
        if(Math.abs(this.motionX) < Math.abs(localPushX) && fueled){
        	if((motionX < 0 && localPushX < 0) || (motionX > 0 && localPushX > 0)){
        		motionX = localPushX;
        	}
        	
        	else if((motionX > 0 && localPushX < 0) || (motionX < 0 && localPushX > 0)){
        		motionX = localPushX * -1;
        	}	
        }
        if(Math.abs(this.motionZ) < Math.abs(localPushZ) && fueled){
        	if((motionZ < 0 && localPushZ < 0) || (motionZ > 0 && localPushZ > 0)){
        		motionZ = localPushZ;
        	}
        	
        	else if((motionZ > 0 && localPushZ < 0) || (motionZ < 0 && localPushZ > 0)){
        		motionZ = localPushZ * -1;
        	}	
        }
        if(connected){
        	moveConnected();
        }
    }

	@Override
    protected void applyDrag()
    {
		if(fuel > 0){
			return;
		}
        double d0 = this.localPushX * this.localPushX + this.localPushZ * this.localPushZ;

        if (d0 > 1.0E-4D)
        {
            d0 = (double)MathHelper.sqrt(d0);
            this.localPushX /= d0;
            this.localPushZ /= d0;
            double d1 = 1.0D;
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.0D;
            this.motionZ *= 0.800000011920929D;
            this.motionX += this.localPushX * 1.0D;
            this.motionZ += this.localPushZ * 1.0D;
        }
        else
        {
            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.0D;
            this.motionZ *= 0.9800000190734863D;
        }

        super.applyDrag();
    }

	@Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        double speedMultiplier = 1.0d;

        //if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.minecart.MinecartInteractEvent(this, player, hand))) return true;
        if(!fueled){
        	if(itemstack.getItem() == Items.GUNPOWDER){
        		fuelUsed = 1;
        		fueled = true;     
        		System.out.println("Fuel Locked to Gunpowder");
        	}
        	if(itemstack.getItem() == Items.BLAZE_ROD){
        		fuelUsed = 2;
        		fueled = true;
        		System.out.println("Fuel Locked to Blaze Rod");
        	}
        	if(itemstack.getItem() == Items.COAL){
        		fuelUsed = 3;
        		fueled = true;
        		System.out.println("Fuel Locked to Coal");
        	}
        }
        
        if (itemstack.getItem() == Items.GUNPOWDER && this.fuel + 3600 <= 32400 && fuelUsed == 1){
            this.fuel += 3600;
            speedMultiplier = 1.5d;
            System.out.println("Inserting Gunpoweder");
            itemstack.shrink(1);
        }
        
        if (itemstack.getItem() == Items.BLAZE_ROD && this.fuel + 8100 <= 32400 && fuelUsed == 2){
            this.fuel += 8100;
            speedMultiplier = 2.0d;
            System.out.println("Inserting Blaze Rods");
            itemstack.shrink(1);
            this.setCustomNameTag("Fuel: Blaze Rod\nFuel Left:" + fuel/20/60);
        }
        
        if (itemstack.getItem() == Items.COAL && this.fuel + 3200 <= 25600 && fuelUsed == 3){
            this.fuel += 3200;
            speedMultiplier = 1.0d;
            System.out.println("Inserting Coal");
            itemstack.shrink(1);

        }
        
        if(itemstack.getItem() == Items.FLINT_AND_STEEL && fueled){
            this.localPushX = (this.posX - player.posX) * speedMultiplier;
            this.localPushZ = (this.posZ - player.posZ) * speedMultiplier;
            motionX = localPushX * .5d;
            motionZ = localPushZ * .5d;
        }

        return true;
    }

	@Override
    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setDouble("PushX", this.localPushX);
        compound.setDouble("PushZ", this.localPushZ);
        compound.setShort("Fuel", (short)this.fuel);
        compound.setShort("FuelUsed", (short)this.fuelUsed);
        compound.setBoolean("Fueled", this.fueled);
    }

	@Override
    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.localPushX = compound.getDouble("PushX");
        this.localPushZ = compound.getDouble("PushZ");
        this.fuel = compound.getShort("Fuel");
        this.fuelUsed = compound.getShort("FuelUsed");
        this.fueled = compound.getBoolean("Fueled");
    }
	
	//Connected Carts
	public boolean connected;
	public EntityMinecart connectedCart;
	
	public void moveConnected(){
		double dirX = this.posX - connectedCart.posX;
		double dirZ = this.posZ - connectedCart.posZ;
		double dis = Math.sqrt(dirX * dirZ);
		if(dis < 5d){
			this.motionX = dirX;
			this.motionZ = dirZ;
		}
	}
}
