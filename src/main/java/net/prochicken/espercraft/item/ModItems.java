package net.prochicken.espercraft.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.prochicken.espercraft.Espercraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.prochicken.espercraft.item.custom.RadioScannerItem;

import java.util.function.Function;

public class ModItems {

    public static final Item RIFT_SHARD = registerItem("rift_shard", Item::new);
    public static final Item RIFT_CRYSTAL = registerItem("rift_crystal", Item::new);
    public static final Item RADIO_SCANNER = registerItem("radio_scanner", properties -> new RadioScannerItem(properties.durability(32)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, name),
            function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Espercraft.LOGGER.info("Registering Mod Items for " + Espercraft.MOD_ID);
    }
}
