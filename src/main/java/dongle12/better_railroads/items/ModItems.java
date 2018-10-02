package dongle12.better_railroads.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	@GameRegistry.ObjectHolder("better_railroads:empty_cart")
	public static ItemEmptyCart empty_cart;
	
	@GameRegistry.ObjectHolder("better_railroads:wrench")
	public static Wrench wrench;
	
	@GameRegistry.ObjectHolder("better_railroads:train_cart")
	public static ItemTrainCart train_cart;
	
	@GameRegistry.ObjectHolder("better_railroads:hopper_cart")
	public static ItemHopperCart hopper_cart;
	
	@GameRegistry.ObjectHolder("better_railroads:chest_cart")
	public static ItemChestCart chest_cart;
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	empty_cart.initModel();
    	train_cart.initModel();
    	hopper_cart.initModel();
    	chest_cart.initModel();
    	wrench.initModel();
    }
}
