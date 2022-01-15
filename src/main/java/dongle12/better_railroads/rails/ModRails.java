package dongle12.better_railroads.rails;

import dongle12.better_railroads.rails.powered_rails.BrakeRail;
import dongle12.better_railroads.rails.powered_rails.DecelerationRail;
import dongle12.better_railroads.rails.powered_rails.JumpRail;
import dongle12.better_railroads.rails.powered_rails.LaunchingRail;
import dongle12.better_railroads.rails.powered_rails.PoweredSpeedRail;
import dongle12.better_railroads.rails.special_rails.DetectionRail;
import dongle12.better_railroads.rails.standard_rails.IntersectionRail;
import dongle12.better_railroads.rails.standard_rails.SpeedRail;
import dongle12.better_railroads.rails.standard_rails.WoodenRail;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static dongle12.better_railroads.BetterRailroads.*;

public class ModRails {
	
	@GameRegistry.ObjectHolder(MODID + ":speed_rail")
	public static SpeedRail speed_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":powered_speed_rail")
	public static PoweredSpeedRail powered_speed_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":brake_rail")
	public static BrakeRail brake_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":deceleration_rail")
	public static DecelerationRail deceleration_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":wooden_rail")
	public static WoodenRail wooden_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":launching_rail")
	public static LaunchingRail launching_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":intersection_rail")
	public static IntersectionRail intersection_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":detection_rail")
	public static DetectionRail detection_rail;
	
	@GameRegistry.ObjectHolder(MODID + ":jump_rail")
	public static JumpRail jump_rail;
	
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	speed_rail.initModel();
        powered_speed_rail.initModel();
        brake_rail.initModel();
        deceleration_rail.initModel();
        wooden_rail.initModel();
        launching_rail.initModel();
        intersection_rail.initModel();
        detection_rail.initModel();
        jump_rail.initModel();
    }
}
