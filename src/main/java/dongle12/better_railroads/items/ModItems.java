package dongle12.better_railroads.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static dongle12.better_railroads.BetterRailroads.*;

public class ModItems {
	
	@GameRegistry.ObjectHolder(MODID + ":speed_cart")
	public static ItemEmptyCart speed_cart;
	
	@GameRegistry.ObjectHolder(MODID + ":wrench")
	public static Wrench wrench;
	
	@GameRegistry.ObjectHolder(MODID + ":train_cart")
	public static ItemTrainCart train_cart;
	
	@GameRegistry.ObjectHolder(MODID + ":hopper_cart")
	public static ItemHopperCart hopper_cart;
	
	@GameRegistry.ObjectHolder(MODID + ":chest_cart")
	public static ItemChestCart chest_cart;
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	speed_cart.initModel();
    	train_cart.initModel();
    	hopper_cart.initModel();
    	chest_cart.initModel();
    	wrench.initModel();
    }
}
