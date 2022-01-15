package dongle12.better_railroads.events;

import dongle12.better_railroads.carts.MinecartSpeed;
import dongle12.better_railroads.util.ConfigHandler;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CartSpawnEvent {

	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event){

		if(!event.getWorld().isRemote){
			if(event.getEntity().getClass() == net.minecraft.entity.item.EntityMinecartEmpty.class && ConfigHandler.SPEEDY_CART){
				MinecartSpeed newCart = new MinecartSpeed(event.getWorld());
				newCart.setPositionAndRotation(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, event.getEntity().rotationPitch, event.getEntity().rotationYaw);
				event.getWorld().spawnEntity(newCart);
				event.getEntity().setDead();
				event.setCanceled(true);
			}
		}
	}
}
