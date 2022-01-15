package dongle12.better_railroads.rails.powered_rails;

import dongle12.better_railroads.util.ConfigHandler;
import dongle12.better_railroads.util.RailUtil;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LaunchingRail extends PoweredRail {
	

	public LaunchingRail(){
		super("launching_rail");
	}
	
	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos){
		//The default speed of a rail that 
		float retSpeed = 0.4f;
		//Look for Slopes upward
		for(EnumFacing side : EnumFacing.HORIZONTALS){
			//Look at the next block
			BlockPos nextPos = pos.offset(side);
			//Boolean to see if a rail has been found
			boolean foundRail = RailUtil.isRail(world, nextPos);
			//If no rail is found, check the next block up or down to find a rail
			if(!foundRail){
				if(RailUtil.isRail(world, nextPos.down())){
					foundRail = true;
					nextPos = nextPos.up();
				}
				else if(RailUtil.isRail(world,nextPos.down())){
					foundRail = true;
					nextPos = nextPos.down();
				}
			}
			if(foundRail){
				//Get the current direction of the rail found 
				BlockRailBase.EnumRailDirection dir = RailUtil.RailDirection(world, cart, pos);
				BlockRailBase.EnumRailDirection dirNext = RailUtil.RailDirection(world, cart, nextPos);

				//If the direction is not null, and the rail is ascending, start to slow
				if((dir != null && dir.isAscending()) || (dirNext != null && dirNext.isAscending())){
					retSpeed = .6f;
				}
				else{
					retSpeed = super.getRailMaxSpeed(world, cart, pos) * ConfigHandler.POWERED_SPEED_RAIL_MULTIPLIER;
				}
			}
		}
		return retSpeed;
	}
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
        
    public ItemBlock getItemBlock() {
        return itemBlock;
    }
    
    
    @Override
    public boolean canMakeSlopes(IBlockAccess world, BlockPos pos)
    {
        return false;
    }
    
    
    @Override
    public boolean isFlexibleRail(IBlockAccess world, BlockPos pos)
    {
        return false;
    }
    
    //////////////////////////////////////////////////////////
    //items below are needed to extend from BlockRailPowered//
    //////////////////////////////////////////////////////////
    @Override
    public void onMinecartPass(World world, EntityMinecart cart, BlockPos pos) { 
    	
    	if (!world.getBlockState(pos).getValue(BlockRailPowered.POWERED)){
    		cart.motionX = 0D;
    		cart.motionZ = 0D;
    	}
    	else{
    		BlockRailBase.EnumRailDirection dir = RailUtil.RailDirection(world, cart, pos);
    		if(dir == EnumRailDirection.NORTH_SOUTH){
    			if(RailUtil.IsLauncherBlock(world, pos.north())){
    				cart.motionZ = 0.5D;
    			}
    			else if(RailUtil.IsLauncherBlock(world, pos.south())){
    				cart.motionZ = -0.5D;
    			}
    		}
    		else if(dir == EnumRailDirection.EAST_WEST){
    			if(RailUtil.IsLauncherBlock(world, pos.east())){
    				cart.motionX = -0.5D;
    			}
    			else if(RailUtil.IsLauncherBlock(world, pos.west())){
    				cart.motionX = 0.5D;
    			}
    		}
    		else{
    			//Do nothing
    		}
    	}
    }
}
