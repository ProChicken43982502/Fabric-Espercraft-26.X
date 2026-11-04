package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.prochicken.espercraft.item.ModItems;
import net.prochicken.espercraft.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    // For vanilla items use .add(ItemIds.ITEM_NAME)
    // For espercraft items use .add(ModItems.getRK(ModItems.ITEM_NAME))

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTags.Items.CHARGES_RADIO_SCANNER)
                .add(ModItems.getRK(ModItems.RIFT_SHARD));
    }
}
