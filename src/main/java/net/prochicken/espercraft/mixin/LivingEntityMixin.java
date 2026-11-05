package net.prochicken.espercraft.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.prochicken.espercraft.datagen.ModDamageTypes;
import net.prochicken.espercraft.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @WrapMethod(method = "hurtServer")
    private boolean espercraft$modifyDamage(
            ServerLevel level,
            DamageSource source,
            float damage,
            Operation<Boolean> original
    ) {
        LivingEntity mob = (LivingEntity) (Object) this;

        // Check if the mob has Hexed
        if (mob.hasEffect(ModEffects.HEXED) && (source.is(ModDamageTypes.ANIMA) || source.is(ModDamageTypes.INCANTATION))
                ) {

            MobEffectInstance effect = mob.getEffect(ModEffects.HEXED);

            if (effect != null) {
                int amplifier = effect.getAmplifier();

                // Increase incoming damage
                damage *= 1.0F + (0.25F * (amplifier + 1));
            }
        }

        // Apply the modified damage normally
        return original.call(level, source, damage);
    }
}