package net.prochicken.espercraft.registries;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.prochicken.espercraft.item.ModItems;

public class ModCompostables {

    public static void registerCompostables() {
        CompostableRegistry.INSTANCE.add(ModItems.TOMATO, 0.3f);
        CompostableRegistry.INSTANCE.add(ModItems.ROTTEN_TOMATO, 1.0f);
        CompostableRegistry.INSTANCE.add(ModItems.TOMATO_SEEDS, 0.3f);
    }
}
