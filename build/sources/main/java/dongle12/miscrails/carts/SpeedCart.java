package dongle12.miscrails.carts;

import dongle12.miscrails.util.ConfigHandler;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class SpeedCart extends EntityMinecartEmpty {

	public SpeedCart(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	public SpeedCart(World world){
		super(world);
	}

	@Override
	public float getMaxCartSpeedOnRail(){
		return ConfigHandler.MAX_CART_SPEED;
	}
	
	@Override
    public boolean canBePushed()
    {
        return false;
    }
	
}
