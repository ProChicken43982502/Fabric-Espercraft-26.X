package net.prochicken.espercraft.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.prochicken.espercraft.Espercraft;
import net.prochicken.espercraft.advancements.ModCriteria;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends AdvancementProvider {
    public ModAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new EspercraftAdvancements()));
    }

    public static class EspercraftAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);
            var blocks = registries.lookupOrThrow(Registries.BLOCK);

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            ModItems.RIFT_SHARD,
                            Component.translatable("advancements.espercraft.root.title"),
                            Component.translatable("advancements.espercraft.root.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false
                    )
                    .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                    .save(output, Espercraft.MOD_ID + ":espercraft/root");
            AdvancementHolder scan_research_table = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.MAGNIFIER,
                            Component.translatable("advancements.espercraft.ologiology.title"),
                            Component.translatable("advancements.espercraft.ologiology.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("scan_research_table", ModCriteria.scanned(ModBlocks.RESEARCH_TABLE))
                    .save(output, Espercraft.MOD_ID + ":espercraft/ologiology");
        }
    }
}
