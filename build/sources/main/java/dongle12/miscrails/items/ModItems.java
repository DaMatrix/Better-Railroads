package dongle12.miscrails.items;

import dongle12.miscrails.carts.SpeedCart;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	@GameRegistry.ObjectHolder("miscrails:speed_cart")
	public static ItemSpeedCart speed_cart;
	
	@GameRegistry.ObjectHolder("miscrails:wrench")
	public static Wrench wrench;
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	speed_cart.initModel();
    	wrench.initModel();
    }
}
