package dongle12.better_railroads.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static dongle12.better_railroads.BetterRailroads.*;

public class ModItems {
	
	@GameRegistry.ObjectHolder(MODID + ":speed_cart")
	public static ItemSpeedCart speed_cart;
	
	@GameRegistry.ObjectHolder(MODID + ":wrench")
	public static Wrench wrench;
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	speed_cart.initModel();
    	wrench.initModel();
    }
}
