package dongle12.better_railroads.util;

import dongle12.better_railroads.BetterRailroads;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static dongle12.better_railroads.BetterRailroads.*;

@Config(modid = MODID)
@Mod.EventBusSubscriber
public class ConfigHandler {

	@Comment("Speed multiplier of speed rail. Default 3; Default for a vanilla rail is .4")
	public static float SPEED_RAIL_MULTIPLIER = 3;
	
	@Comment("Speed multiplier of a powered speed rail. Default: 4; A normal powered rail has a multipier of 1.")
	public static float POWERED_SPEED_RAIL_MULTIPLIER = 4;
	
	@Comment("The max speed of the speed cart. Default for vanilla is 1.2")
	public static float MAX_CART_SPEED = 4;
	
	@Comment("The speed multiplier of a wooden rail. Default: 1; Default for a vanilla rail is .4")
	public static float WOODEN_RAIL_MULTIPLIER = 1;
	
	@Comment("The max height that the Jump Rail can use")
	public static int MAX_JUMP_RAIL_STRENGTH = 10;
	
	@Comment("Use Faster cart instead of default cart")
	public static boolean SPEEDY_CART = true;

	@Comment("Enables experimental code which makes very fast minecarts less buggy when going around corners.")
	public static boolean EXPERIMENTAL_FAST_CART_HANDLING = true;

	@SubscribeEvent
	public static void configChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (MODID.equals(event.getModID())) {
			ConfigManager.sync(MODID, Config.Type.INSTANCE);
		}
	}
}
