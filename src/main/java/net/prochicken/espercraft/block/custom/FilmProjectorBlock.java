// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.prochicken.espercraft.block.entity.custom.FilmProjectorBlockEntity;
import net.prochicken.espercraft.item.ModItems;
import org.jspecify.annotations.Nullable;

public class FilmProjectorBlock extends BaseEntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 7);
    private static MapCodec<FilmProjectorBlock> CODEC = simpleCodec(FilmProjectorBlock::new);

    // Turns a box clockwise by 90 degrees, 'turns' times, around the centre of the block
    private static VoxelShape rotatedBox(int turns, double x1, double y1, double z1, double x2, double y2, double z2) {
        for (int i = 0; i < turns; i++) {
            double newX1 = 16 - z2;
            double newX2 = 16 - z1;
            double newZ1 = x1;
            double newZ2 = x2;
            x1 = newX1; x2 = newX2;
            z1 = newZ1; z2 = newZ2;
        }
        return Block.box(x1, y1, z1, x2, y2, z2);
    }

    private static final VoxelShape STAND = Block.box(4.5, 0, 4.5, 11.5, 8, 11.5);
    private static final VoxelShape DIAGONAL_COLLIDER = Block.box(4.5, 0, 4.5, 11.5, 16, 11.5);

    private static VoxelShape makeShape(int turns) {
        VoxelShape body = rotatedBox(turns, 4, 8, 1, 12, 16, 14);   // camera body
        VoxelShape lens = rotatedBox(turns, 6, 10, 14, 10, 14, 16);   // lens
        return Shapes.or(STAND, body, lens);
    }

    private static final VoxelShape DIAGONAL_SHAPE = Shapes.or(
            STAND,
            Block.box(2, 8, 2, 14, 16, 14)
    );

    private static final VoxelShape[] SHAPES = {
            makeShape(0),    // 0: north
            DIAGONAL_SHAPE,  // 1
            makeShape(1),    // 2: east
            DIAGONAL_SHAPE,  // 3
            makeShape(2),    // 4: south
            DIAGONAL_SHAPE,  // 5
            makeShape(3),    // 6: west
            DIAGONAL_SHAPE   // 7
    };

    private static final VoxelShape[] SHAPE_COLLIDERS = {
            makeShape(0),    // 0: north
            DIAGONAL_COLLIDER,  // 1
            makeShape(1),    // 2: east
            DIAGONAL_COLLIDER,  // 3
            makeShape(2),    // 4: south
            DIAGONAL_COLLIDER,  // 5
            makeShape(3),    // 6: west
            DIAGONAL_COLLIDER   // 7
    };

    public FilmProjectorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(ROTATION, 0)
                .setValue(POWERED, false)
        );
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        int rotation = Mth.floor((180.0F + context.getRotation()) * 8.0F / 360.0F + 0.5F) & 7;
        return this.defaultBlockState()
                .setValue(ROTATION, rotation)
                .setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!level.isClientSide()) {
            boolean hasSignal = level.hasNeighborSignal(pos);
            if (hasSignal != state.getValue(POWERED)) {
                level.setBlock(pos, state.setValue(POWERED, hasSignal), 3);
            }
        }
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(ROTATION, rotation.rotate(state.getValue(ROTATION), 8));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION), 8));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(ROTATION)];
    }
    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_COLLIDERS[state.getValue(ROTATION)];
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new FilmProjectorBlockEntity(worldPosition, blockState);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {

        if (level.getBlockEntity(pos) instanceof FilmProjectorBlockEntity filmProjectorBlockEntity) {
            filmProjectorBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }

        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (level.getBlockEntity(pos) instanceof FilmProjectorBlockEntity filmProjectorBlockEntity) {
            player.openMenu(filmProjectorBlockEntity);
        }
        return InteractionResult.SUCCESS;
    }
}
