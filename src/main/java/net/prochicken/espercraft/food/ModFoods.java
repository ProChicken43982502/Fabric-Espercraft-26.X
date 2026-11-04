package net.prochicken.espercraft.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(1).saturationModifier(0.6f).build();
    public static final FoodProperties ROTTEN_TOMATO = new FoodProperties.Builder().nutrition(2).saturationModifier(0.6f).build();
    public static final FoodProperties TOMATO_SOUP = new FoodProperties.Builder().nutrition(6).saturationModifier(3.6f).build();
    public static final FoodProperties TOMATO_JUICE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.6f).alwaysEdible().build();
    public static final FoodProperties TOMATO_JELLY = new FoodProperties.Builder().nutrition(2).saturationModifier(1.2f).alwaysEdible().build();

    public static final Consumable TOMATO_CONSUMABLE = Consumables.defaultFood().build();
    public static final Consumable ROTTEN_TOMATO_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 100), 0.6f)).build();
    public static final Consumable TOMATO_SOUP_CONSUMABLE = Consumables.defaultFood().build();
    public static final Consumable TOMATO_JUICE_CONSUMABLE = Consumables.defaultDrink().build();
    public static final Consumable TOMATO_JELLY_CONSUMABLE = Consumables.defaultDrink()
            .onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.SLOWNESS)).build();
}
