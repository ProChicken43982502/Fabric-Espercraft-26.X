package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        dropSelf(ModBlocks.RIFTSHARD_BLOCK);
        dropSelf(ModBlocks.RESEARCH_TABLE);

        add(ModBlocks.RIFT_CRYSTAL, block -> createSilkTouchDispatchTable(block,
                LootItem.lootTableItem(ModItems.RIFT_SHARD)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0f)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                .of(this.registries.lookupOrThrow(Registries.ITEM), ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(applyExplosionDecay(block,
                                LootItem.lootTableItem(ModItems.RIFT_SHARD)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0f)))))
        ));

    }
}
