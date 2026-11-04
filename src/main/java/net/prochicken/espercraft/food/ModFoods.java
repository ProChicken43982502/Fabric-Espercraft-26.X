package net.prochicken.espercraft.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(1).saturationModifier(0.6f).build();
    public static final FoodProperties ROTTEN_TOMATO = new FoodProperties.Builder().nutrition(2).saturationModifier(0.6f).build();

    public static final Consumable TOMATO_CONSUMABLE = Consumables.defaultFood().build();
    public static final Consumable ROTTEN_TOMATO_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 100), 0.6f)).build();
}
