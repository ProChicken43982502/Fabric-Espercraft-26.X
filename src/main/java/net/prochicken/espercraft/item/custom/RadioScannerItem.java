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
    double scanning_radius = 50.0;

    public RadioScannerItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player player, @NonNull InteractionHand hand) {
        // Applies glowing effect to all entities within radius

        if (!level.isClientSide()) {

            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(scanning_radius),
                    entity -> entity != player && entity.distanceToSqr(player) <= scanning_radius * scanning_radius
            );

            for (LivingEntity target : targets) {
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200, 0));
            }

            ItemStack stack = player.getItemInHand(hand);
            stack.hurtWithoutBreaking(1, player);
            player.getCooldowns().addCooldown(stack, 100); // 5 seconds
            level.playSound(null, player.blockPosition(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0f, 1.5f);

            player.sendOverlayMessage(Component.literal("Scanning..."));
        }
        return InteractionResult.SUCCESS;
    }
}
