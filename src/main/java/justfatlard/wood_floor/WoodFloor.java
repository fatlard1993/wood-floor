package justfatlard.wood_floor;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

public class WoodFloor extends Block implements Waterloggable {
	public static final BooleanProperty WATERLOGGED;
	protected static final VoxelShape SHAPE;

	public WoodFloor(){
		super(Block.Settings.of(Material.WOOD));

		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder){ builder.add(WATERLOGGED); }

	public FluidState getFluidState(BlockState state) {
		return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context){
		return SHAPE;
	}

	static {
		WATERLOGGED = Properties.WATERLOGGED;
		SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
	}
}
