package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.tags.ModTags;

import java.util.concurrent.CompletableFuture;


// For vanilla blocks use .add(BlockIds.BLOCK_NAME)
// For espercraft blocks use .add(ModBlocks.getRK(ModBlocks.BLOCK_NAME))

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.RIFTSHARD_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.RIFT_CRYSTAL));

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.getRK(ModBlocks.RESEARCH_TABLE));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.RIFTSHARD_BLOCK));

        tag(ModTags.Blocks.SCANNABLE_WITH_MAGNIFIER)
                .add(ModBlocks.getRK(ModBlocks.RIFTSHARD_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.RESEARCH_TABLE));
    }
}
