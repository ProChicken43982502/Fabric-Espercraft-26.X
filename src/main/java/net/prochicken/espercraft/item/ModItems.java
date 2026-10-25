package net.prochicken.espercraft.item;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.prochicken.espercraft.Espercraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.prochicken.espercraft.data.ModDataComponents;
import net.prochicken.espercraft.food.ModFoods;
import net.prochicken.espercraft.item.custom.MagnifierItem;
import net.prochicken.espercraft.item.custom.RadioScannerItem;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {

    public static final Item RIFT_SHARD = registerItem("rift_shard", Item::new);
    public static final Item RADIO_SCANNER = registerItem("radio_scanner", properties -> new RadioScannerItem(properties
            .durability(32)) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            if (Minecraft.getInstance().hasShiftDown()) {
                builder.accept(Component.translatable("tooltip.espercraft.radio_scanner"));
            } else {
                builder.accept(Component.translatable("tooltip.espercraft.shift_for_more_info"));
            }
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }
    });
    public static final Item MAGNIFIER = registerItem("magnifier", properties -> new MagnifierItem(properties
            .stacksTo(1)) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            if (Minecraft.getInstance().hasShiftDown()) {
                builder.accept(Component.translatable("tooltip.espercraft.magnifier_1"));
                builder.accept(Component.translatable("tooltip.espercraft.magnifier_2"));
            } else {
                builder.accept(Component.translatable("tooltip.espercraft.shift_for_more_info"));
            }
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }});
    public static final Item TOMATO = registerItem("tomato", properties -> new Item(properties
            .food(ModFoods.TOMATO, ModFoods.TOMATO_CONSUMABLE)));
    public static final Item ROTTEN_TOMATO = registerItem("rotten_tomato", properties -> new Item(properties
            .food(ModFoods.ROTTEN_TOMATO, ModFoods.ROTTEN_TOMATO_CONSUMABLE)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, name),
            function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Espercraft.LOGGER.info("Registering Mod Items for " + Espercraft.MOD_ID);
    }
}
