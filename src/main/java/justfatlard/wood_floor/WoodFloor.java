package justfatlard.wood_floor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * A thin board lying in a block: on the floor, against the ceiling, or both.
 *
 * <p>It was a single thing that only ever lay on the floor. Making it a slab in the sense that
 * matters - which half of the block it fills - costs one property and gives it the ceiling, which
 * is where exposed boards want to be when you are building a storey above.
 *
 * <p>The double is the interesting one. A vanilla slab doubled fills its block; two boards instead
 * take <b>a surface each</b> - one on the floor, one against the ceiling, the block hollow between
 * them. That is what the boundary between two storeys actually looks like from inside either of
 * them, and it is why a doubled floor still holds water where a doubled slab cannot.
 *
 * <p>It uses vanilla's own slab type property, so anything that already knows how to read a slab -
 * the mixed slabs mod among them - can read this without being told about floors specifically.
 */
public class WoodFloor extends Block implements SimpleWaterloggedBlock {

	public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	/** Board thickness in pixels, matching the model. */
	private static final double THICKNESS = 2.0;

	private static final VoxelShape BOTTOM_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, THICKNESS, 16.0);
	private static final VoxelShape TOP_SHAPE = Block.box(0.0, 16.0 - THICKNESS, 0.0, 16.0, 16.0, 16.0);
	/** Both boards, each at its own surface, with the block hollow between them. */
	private static final VoxelShape DOUBLE_SHAPE = net.minecraft.world.phys.shapes.Shapes.or(BOTTOM_SHAPE, TOP_SHAPE);

	public WoodFloor(BlockBehaviour.Properties settings) {
		super(settings);
		// Bottom is the default on purpose: it is what every floor already placed in a world is,
		// and a state saved before this property existed comes back with the default for it.
		this.registerDefaultState(this.defaultBlockState()
			.setValue(TYPE, SlabType.BOTTOM)
			.setValue(WATERLOGGED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(TYPE)) {
			case TOP -> TOP_SHAPE;
			case DOUBLE -> DOUBLE_SHAPE;
			default -> BOTTOM_SHAPE;
		};
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		BlockState existing = context.getLevel().getBlockState(pos);

		// A second board into a block that already holds one. Not waterlogged: the pair displaces
		// what one of them left room for.
		if (existing.is(this)) {
			return existing.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false);
		}

		boolean inWater = context.getLevel().getFluidState(pos).getType() == Fluids.WATER;
		BlockState placed = defaultBlockState().setValue(WATERLOGGED, inWater);

		return fillsUpperHalf(context) ? placed.setValue(TYPE, SlabType.TOP) : placed;
	}

	/**
	 * Whether this click means the board goes against the ceiling.
	 *
	 * <p>Vanilla's own rule for slabs, kept exactly: the underside of a block always means the top
	 * half, and a side click goes by which side of the halfway line it landed on.
	 */
	private static boolean fillsUpperHalf(BlockPlaceContext context) {
		Direction face = context.getClickedFace();
		if (face == Direction.DOWN) return true;
		if (face == Direction.UP) return false;

		return context.getClickLocation().y - context.getClickedPos().getY() > 0.5;
	}

	@Override
	protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		ItemStack held = context.getItemInHand();
		SlabType type = state.getValue(TYPE);
		if (type == SlabType.DOUBLE || !held.is(asItem())) return false;
		if (!context.replacingClickedOnBlock()) return true;

		boolean aboveMiddle = context.getClickLocation().y - context.getClickedPos().getY() > 0.5;
		Direction face = context.getClickedFace();

		if (type == SlabType.BOTTOM) {
			return face == Direction.UP || (aboveMiddle && face.getAxis().isHorizontal());
		}
		return face == Direction.DOWN || (!aboveMiddle && face.getAxis().isHorizontal());
	}

	/**
	 * Water sits alongside a board whichever half it is in, a doubled one included.
	 *
	 * <p>Vanilla refuses a doubled slab because there is no room left in the block; two boards fill
	 * four pixels of it and leave the other twelve, so the reason does not carry over.
	 */
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView,
			BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState,
			RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}
}
