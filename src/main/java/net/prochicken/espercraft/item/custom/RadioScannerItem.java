package net.prochicken.espercraft.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class RadioScannerItem extends Item {
    public static final int SCANNING_RADIUS = 50;   // scanning radius (50 blocks)
    public static final int USE_DURATION = 1200;   // max hold time (60s)
    public static final int CHARGE_TICKS = 30;     // time needed to charge (1.5s)

    public RadioScannerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.nextDamageWillBreak()) {
            return InteractionResult.FAIL;   // dead, can't be used
        }
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return USE_DURATION;
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int remainingUseDuration) {
        if (!(entity instanceof Player player)) return false;

        int chargedTicks = getUseDuration(stack, entity) - remainingUseDuration;

        if (!level.isClientSide()) {
            if (chargedTicks < CHARGE_TICKS) {
                player.sendOverlayMessage(Component.literal("Scan cancelled"));
                return false;
            }

            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(SCANNING_RADIUS),
                    e -> e != player && e.distanceToSqr(player) <= SCANNING_RADIUS * SCANNING_RADIUS
            );

            for (LivingEntity target : targets) {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
            }

            stack.hurtWithoutBreaking(1, player);
            player.getCooldowns().addCooldown(stack, 100);
            level.playSound(null, player.blockPosition(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0f, 1.5f);
            player.sendOverlayMessage(Component.literal("Scan complete"));
        }
        return true;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        int chargedTicks = getUseDuration(stack, entity) - remainingUseDuration;
        if (chargedTicks == CHARGE_TICKS && !level.isClientSide()) {
            level.playSound(null, entity.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0f, 1.2f);
        }
    }

}
