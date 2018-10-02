package dongle12.better_railroads.events;

import dongle12.better_railroads.carts.CartLinkable;
import dongle12.better_railroads.carts.MinecartEmpty;
import dongle12.better_railroads.carts.TrainCart;
import dongle12.better_railroads.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LinkEvent {
	
	@SubscribeEvent
	public void linkCart(EntityInteract event){
		
		if(!event.getWorld().isRemote){
			ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
			if(stack == null){
				return;
			}
			if(!(event.getTarget() instanceof MinecartEmpty) || !(event.getTarget() instanceof TrainCart)){
				return;
			}
			if(stack.getItem() == ModItems.wrench){
				if(event.getTarget() instanceof MinecartEmpty){
					
				}
			}
		}
	}
}
