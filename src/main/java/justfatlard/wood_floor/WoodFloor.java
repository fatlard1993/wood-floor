package justfatlard.wood_floor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WoodFloor extends Block implements SimpleWaterloggedBlock {
	public static final BooleanProperty WATERLOGGED;
	protected static final VoxelShape SHAPE;

	public WoodFloor(BlockBehaviour.Properties settings){
		super(settings);

		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){ builder.add(WATERLOGGED); }

	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context){
		return SHAPE;
	}

	static {
		WATERLOGGED = BlockStateProperties.WATERLOGGED;
		SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	}
}
