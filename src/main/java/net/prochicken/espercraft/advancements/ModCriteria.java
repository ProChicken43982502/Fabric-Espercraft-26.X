// --- WARNING: Contains AI-generated code ---

package net.prochicken.espercraft.advancements;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.prochicken.espercraft.Espercraft;
import net.prochicken.espercraft.advancements.triggers.ScanBlockTrigger;

import java.util.Optional;

public class ModCriteria {
    public static final ScanBlockTrigger SCAN_BLOCK = Registry.register(
            BuiltInRegistries.TRIGGER_TYPES,
            Espercraft.id("scan_block"),
            new ScanBlockTrigger());

    public static Criterion<ScanBlockTrigger.TriggerInstance> scanned(Block block) {
        return SCAN_BLOCK.createCriterion(new ScanBlockTrigger.TriggerInstance(
                Optional.empty(),
                Optional.of(HolderSet.direct(block.builtInRegistryHolder()))));
    }

    public static void registerCriteria() {
        Espercraft.LOGGER.info("Registering criteria for " + Espercraft.MOD_ID);
    }
}