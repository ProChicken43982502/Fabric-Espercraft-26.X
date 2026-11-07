package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.block.custom.TomatoCropBlock;
import net.prochicken.espercraft.item.ModItems;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.RIFTSHARD_BLOCK);

        blockModelGenerators.createAmethystCluster(ModBlocks.RIFT_CRYSTAL);

        blockModelGenerators.createHorizontallyRotatedBlock(ModBlocks.RESEARCH_TABLE, TexturedModel.ORIENTABLE);

        blockModelGenerators.createCropBlock(ModBlocks.TOMATO_CROP, TomatoCropBlock.AGE, 0, 1, 2, 3, 4);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.FILM_SPOOL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RIFT_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TOMATO, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ROTTEN_TOMATO, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TOMATO_SOUP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TOMATO_JUICE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TOMATO_JELLY, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.declareCustomModelItem(ModBlocks.RIFT_CRYSTAL.asItem());
    }
}
