package dongle12.better_railroads.carts;

import dongle12.better_railroads.BetterRailroads;
import dongle12.better_railroads.renderers.RenderMinecartEmpty;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCarts {
	
	public static void init(){
    	EntityRegistry.registerModEntity(new ResourceLocation(BetterRailroads.MODID, "empty_cart"), MinecartEmpty.class, "empty_cart", 0, BetterRailroads.instance, 64, 1, false);
    	EntityRegistry.registerModEntity(new ResourceLocation(BetterRailroads.MODID, "train_cart"), TrainCart.class, "train_cart", 1, BetterRailroads.instance, 64, 1, false);
    	EntityRegistry.registerModEntity(new ResourceLocation(BetterRailroads.MODID, "hopper_cart"), MinecartHopper.class, "hopper_cart", 2, BetterRailroads.instance, 64, 1, false);
    	EntityRegistry.registerModEntity(new ResourceLocation(BetterRailroads.MODID, "chest_cart"), MinecartChest.class, "chest_cart", 3, BetterRailroads.instance, 64, 1, false);

	}
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	System.out.println("INIT MODELS CALLED");
    	RenderingRegistry.registerEntityRenderingHandler(MinecartEmpty.class, RenderMinecartEmpty.FACTORY_EMPTY);
    }
}
