package net.prochicken.espercraft.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.prochicken.espercraft.Espercraft;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.item.ModItems;

public class ModCreativeModeTabs {
    public static final CreativeModeTab ESPERCRAFT_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Espercraft.MOD_ID, "espercraft_creative_mode_tab"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RIFT_SHARD))
                    .title(Component.translatable("creativemodetab.espercraft.espercraft_creative_mode_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.RIFT_SHARD);
                        output.accept(ModItems.RIFT_CRYSTAL);
                        output.accept(ModBlocks.RIFTSHARD_BLOCK);
                        output.accept(ModItems.RADIO_SCANNER);
                        output.accept(ModItems.MAGNIFIER);
                        output.accept(ModBlocks.RESEARCH_TABLE);
                        output.accept(ModItems.TOMATO);
                        output.accept(ModItems.ROTTEN_TOMATO);
                    }).build());

    public static void registerModCreativeModeTabs() {
        Espercraft.LOGGER.info("Registering Creative Mode Tabs for " + Espercraft.MOD_ID);
    }
}
