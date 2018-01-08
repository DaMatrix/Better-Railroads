package dongle12.miscrails.util;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.Comment;
import dongle12.miscrails.MiscRails;

@Config(modid = MiscRails.MODID, name = MiscRails.NAME)
public class ConfigHandler {

	@Comment("Speed multiplier of speed rail. Default: 500 - This makes the rails like ice, essentially removing a speed restriction")
	public static float SPEED_RAIL_MULTIPLIER = 4;
	
	@Comment("Speed multiplier of a powered speed rail. Default: 3. A normal powered rail has a multipier of 1.")
	public static float POWERED_SPEED_RAIL_MULTIPLIER = 6;
	
	@Comment("The max speed of the speed carts")
	public static float MAX_CART_SPEED = 3;
	
	@Comment("The speed multiplier of a wooden rail")
	public static float WOODEN_RAIL_MULTIPLIER = 1;
}
