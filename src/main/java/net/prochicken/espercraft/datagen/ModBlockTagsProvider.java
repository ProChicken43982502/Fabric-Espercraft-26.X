package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RIFTSHARD_BLOCK)
                .add(ModBlocks.RIFT_CRYSTAL);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.RESEARCH_TABLE);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RIFTSHARD_BLOCK);

        valueLookupBuilder(ModTags.Blocks.SCANNABLE_WITH_MAGNIFIER)
                .add(ModBlocks.RIFTSHARD_BLOCK)
                .add(ModBlocks.RESEARCH_TABLE);
    }
}
