package dongle12.better_railroads.rails.powered_rails;

import dongle12.better_railroads.BetterRailroads;
import dongle12.better_railroads.util.RailUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PoweredRail extends BlockRailPowered {
	
	final ItemBlock itemBlock;

	public PoweredRail(String name){
		super();
        setTranslationKey(BetterRailroads.MODID + "." + name);
        setRegistryName(new ResourceLocation(BetterRailroads.MODID, name));
        
        itemBlock = new ItemBlock(this);
        itemBlock.setRegistryName(new ResourceLocation(BetterRailroads.MODID, name));
        itemBlock.setTranslationKey(BetterRailroads.MODID + "." + name);
        setCreativeTab(BetterRailroads.miscRailsTab);
		setHardness(0.7f);
        setSoundType(SoundType.WOOD);
	}
    
    
	private static final AxisAlignedBB FLAT_BOUNDING = new AxisAlignedBB(0f, 0f, 0f, 1.0f, .45f, 1.0f);
    private static final AxisAlignedBB ASCENDING_BOUNDING = new AxisAlignedBB(0f, 0f, 0f, 1.0f, .85f, 1.0f);
    private static final AxisAlignedBB COLLISION_BOX = new AxisAlignedBB(0f, 0f, 0f, 1.0f, -0.15f, 1.0f);
	//USED FOR FLOATING
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos){
		boolean railFound = RailUtil.isRail(world, pos.east()) || RailUtil.isRail(world, pos.west()) || RailUtil.isRail(world, pos.north()) || RailUtil.isRail(world, pos.south());
		boolean blockFound = RailUtil.IsBlock(world, pos.east()) || RailUtil.IsBlock(world, pos.west()) || RailUtil.IsBlock(world, pos.north()) || RailUtil.IsBlock(world, pos.south());
		if(RailUtil.isRail(world, pos.down())){
			return false;
		}
		else if(blockFound || railFound){
			return true;
		}
		else{
			return world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP);
		}
    }
	//USED FOR FLOATING
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
            this.updateState(state, worldIn, pos, blockIn);
        }
    }
	
	//USED FOR FLOATING
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	//USED FOR FLOATING
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	//BETTER COLLISION BOXES
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        BlockRailBase.EnumRailDirection blockrailbase$enumraildirection = state.getBlock() == this ? getRailDirection(source, pos, state, null) : null;
        return blockrailbase$enumraildirection != null && blockrailbase$enumraildirection.isAscending() ? ASCENDING_BOUNDING : FLAT_AABB;
    }
	//BETTER COLLISION BOXES
    @Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return COLLISION_BOX;
	}
}
