package net.prochicken.espercraft.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.prochicken.espercraft.Espercraft;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> ANIMA = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, "anima"));
    public static final ResourceKey<DamageType> INCANTATION = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, "incantation"));

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(ANIMA, new DamageType("anima", 0.1F, DamageEffects.HURT));
        context.register(INCANTATION, new DamageType("incantation", 0.1F, DamageEffects.HURT));
    }

    public static DamageSource create(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
