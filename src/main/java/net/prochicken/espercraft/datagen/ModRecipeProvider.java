package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.prochicken.espercraft.block.ModBlocks;
import net.prochicken.espercraft.item.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RIFT_SHARD, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RIFTSHARD_BLOCK);
                shaped(RecipeCategory.TOOLS, ModItems.RADIO_SCANNER)
                        .pattern("  T")
                        .pattern("#C#")
                        .pattern("#R#")
                        .define('T', Items.REDSTONE_TORCH)
                        .define('#', Items.COPPER_INGOT)
                        .define('C', Items.COMPASS)
                        .define('R', ModItems.RIFT_SHARD)
                        .group("cft_radio_scanner")
                        .unlockedBy(getHasName(ModItems.RIFT_SHARD), has(ModItems.RIFT_SHARD))
                        .unlockedBy(getHasName(Items.COMPASS), has(Items.COMPASS))
                        .save(output);
                shaped(RecipeCategory.TOOLS, ModItems.MAGNIFIER)
                        .pattern("A")
                        .pattern("G")
                        .define('A', Items.AMETHYST_SHARD)
                        .define('G', Items.GOLD_INGOT)
                        .group("cft_magnifier")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "Espercraft Recipes";
    }
}
