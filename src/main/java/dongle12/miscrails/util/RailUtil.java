package dongle12.miscrails.util;

import dongle12.miscrails.blocks.LauncherBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RailUtil {
	
	//Check For Rails
	public static boolean FoundRail(World world, BlockPos pos){
		Block found = world.getBlockState(pos).getBlock();
		if(found instanceof BlockRailBase){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean IsBlock(World world, BlockPos pos){
		Block found = world.getBlockState(pos).getBlock();
		if(found instanceof Block){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean IsLauncherBlock(World world, BlockPos pos){
		Block found = world.getBlockState(pos).getBlock();
		if(found instanceof LauncherBlock){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Get Rail Direction
	public static BlockRailBase.EnumRailDirection RailDirection(World world, EntityMinecart cart, BlockPos pos){
		BlockRailBase.EnumRailDirection dir = null;
		IBlockState curState = world.getBlockState(pos);
		if(curState.getBlock() instanceof BlockRailBase){
			dir = ((BlockRailBase) curState.getBlock()).getRailDirection(world, pos, curState, cart);
			return dir;
		}
		else{
			return null;
		}
	}

	
	//Set Rail Speed
	public static float setRailSpeed(BlockRailBase superClass, World world, EntityMinecart cart, BlockPos pos, float speedMultiplier){
		//The default speed of a rail that 
		float retSpeed = 0.4f;
		//Look for Slopes upward
		for(EnumFacing side : EnumFacing.HORIZONTALS){
			//Look at the next block
			BlockPos nextPos = pos.offset(side);
			//Boolean to see if a rail has been found
			boolean foundRail = RailUtil.FoundRail(world, nextPos);
			//If no rail is found, check the next block up or down to find a rail
			if(!foundRail){
				if(RailUtil.FoundRail(world, nextPos.down())){
					foundRail = true;
					nextPos = nextPos.up();
				}
				else if(RailUtil.FoundRail(world,nextPos.down())){
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
					retSpeed = .4f;//super.getRailMaxSpeed(world, cart, pos);
				}
				else{
					retSpeed = superClass.getRailMaxSpeed(world, cart, pos) * speedMultiplier;
				}
			}
		}
		return retSpeed;
	}
}
