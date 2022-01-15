package dongle12.better_railroads.carts;

import dongle12.better_railroads.BetterRailroads;
import dongle12.better_railroads.renderers.RenderMinecartEmpty;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCarts {
	
	public static void init(){
    	EntityRegistry.registerModEntity(new ResourceLocation(BetterRailroads.MODID, "speed_cart"), MinecartSpeed.class, "speed_cart", 0, BetterRailroads.instance, 64, 1, false);
	}
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	System.out.println("INIT MODELS CALLED");
    	RenderingRegistry.registerEntityRenderingHandler(MinecartSpeed.class, RenderMinecartEmpty.FACTORY_EMPTY);
    }
}
