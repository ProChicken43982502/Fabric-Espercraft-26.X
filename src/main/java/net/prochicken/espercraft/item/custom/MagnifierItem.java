package net.prochicken.espercraft.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.data.ModDataComponents;
import net.prochicken.espercraft.tags.ModTags;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MagnifierItem extends Item {
    public static final float ZOOM_FOV_MODIFIER = 0.5f; // 2x Zoom
    public static final double SCAN_RANGE = 10.0; // Things can be scanned from up to 10 blocks away.
    public static final int SCAN_TICKS = 40; // 2 seconds

    private static final Map<UUID, ScanProgress> PROGRESS = new ConcurrentHashMap<>();

    public MagnifierItem(Properties properties) {
        super(properties);
    }

    private static class ScanProgress {
        final BlockPos pos;
        int ticks = 0;
        ScanProgress(BlockPos pos) { this.pos = pos; }
    }

    public static BlockPos findScannableTarget(LivingEntity entity) {
        HitResult hit = entity.pick(SCAN_RANGE, 1.0f, false);
        if (hit.getType() != HitResult.Type.BLOCK) return null;

        BlockPos pos = ((BlockHitResult) hit).getBlockPos().immutable();
        BlockState state = entity.level().getBlockState(pos);
        return state.is(ModTags.Blocks.SCANNABLE_WITH_MAGNIFIER) ? pos : null;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.SPYGLASS;
    }


    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.getMainHandItem().remove(ModDataComponents.COORDINATES);

        player.playSound(SoundEvents.SPYGLASS_USE, 1.0f, 1.0f);
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int ticksRemaining) {
        if (level.isClientSide()) return;

        BlockPos target = findScannableTarget(entity);

        // Not looking at a scannable block, or it's already the saved one: nothing to scan
        if (target == null || target.equals(stack.get(ModDataComponents.COORDINATES))) {
            PROGRESS.remove(entity.getUUID());
            return;
        }

        ScanProgress progress = PROGRESS.get(entity.getUUID());
        if (progress == null || !progress.pos.equals(target)) {
            progress = new ScanProgress(target);   // new block: start over
            PROGRESS.put(entity.getUUID(), progress);
        }

        progress.ticks++;
        if (progress.ticks >= SCAN_TICKS) {
            stack.set(ModDataComponents.COORDINATES, target);
            level.playSound(null, entity.blockPosition(), SoundEvents.NOTE_BLOCK_BELL.value(), SoundSource.PLAYERS, 1.0f, 1.5f);
            PROGRESS.remove(entity.getUUID());
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level,  LivingEntity entity) {
        this.stopUsing(entity);
        return itemStack;
    }

    @Override
    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingTime) {
        this.stopUsing(entity);
        return true;
    }

    private void stopUsing(LivingEntity entity) {
        PROGRESS.remove(entity.getUUID());
        entity.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0f, 1.0f);
    }
}
