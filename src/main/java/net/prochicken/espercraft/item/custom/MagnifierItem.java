package net.prochicken.espercraft.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class MagnifierItem extends Item {
    public static final float ZOOM_FOV_MODIFIER = 0.5f; // 2x Zoom

    public MagnifierItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.SPYGLASS;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        player.playSound(SoundEvents.SPYGLASS_USE, 1.0f, 1.0f);
        return ItemUtils.startUsingInstantly(level, player, hand);
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
        entity.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0f, 1.0f);
    }
}
