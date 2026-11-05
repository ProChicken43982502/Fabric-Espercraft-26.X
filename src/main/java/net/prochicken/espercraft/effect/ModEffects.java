package net.prochicken.espercraft.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.prochicken.espercraft.Espercraft;

public class ModEffects {

    public static final Holder<MobEffect> HEXED = registerMobEffect("hexed",
            new HexedEffect(MobEffectCategory.HARMFUL, 0xE7566A));

    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        Espercraft.LOGGER.info("Registering effects for " + Espercraft.MOD_ID);
    }
}
