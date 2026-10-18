package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RIFT_SHARD, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RIFTSHARD_BLOCK);
                shapeless(RecipeCategory.MISC, ModItems.RIFT_SHARD, 9)
                        .requires(ModBlocks.RIFTSHARD_BLOCK)
                        .unlockedBy(getHasName(ModItems.RIFT_SHARD), has(ModItems.RIFT_SHARD))
                        .group("rift_shard")
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "Espercraft Recipes";
    }
}
