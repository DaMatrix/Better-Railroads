package dongle12.better_railroads.util;

import dongle12.better_railroads.BetterRailroads;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = BetterRailroads.MODID, name = BetterRailroads.NAME)
public class ConfigHandler {

	@Comment("Speed multiplier of speed rail. Default 3; Default for a vanilla rail is .4")
	public static float SPEED_RAIL_MULTIPLIER = 3;
	
	@Comment("Speed multiplier of a powered speed rail. Default: 4; A normal powered rail has a multipier of 1.")
	public static float POWERED_SPEED_RAIL_MULTIPLIER = 4;
	
	@Comment("The max speed of the speed cart. Default for vanilla is 1.2")
	public static float MAX_CART_SPEED = 3;
	
	@Comment("The speed multiplier of a wooden rail. Default: 1; Default for a vanilla rail is .4")
	public static float WOODEN_RAIL_MULTIPLIER = 1;
	
	@Comment("The max height that the Jump Rail can use")
	public static int MAX_JUMP_RAIL_STRENGTH = 10;
	
	@Comment("Use Faster cart instead of default cart")
	public static boolean SPEEDY_CART = true;
}
