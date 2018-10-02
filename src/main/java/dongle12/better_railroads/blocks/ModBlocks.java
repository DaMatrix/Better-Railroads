package dongle12.better_railroads.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	@GameRegistry.ObjectHolder("better_railroads:launcher_block")
	public static LauncherBlock launcher_block;
		
    @SideOnly(Side.CLIENT)
    public static void initModels() {
    	launcher_block.initModel();

    }
}
