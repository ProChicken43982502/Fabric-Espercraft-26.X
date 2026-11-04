package net.prochicken.espercraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
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
                        .group("radio_scanner")
                        .unlockedBy(getHasName(ModItems.RIFT_SHARD), has(ModItems.RIFT_SHARD))
                        .unlockedBy(getHasName(Items.COMPASS), has(Items.COMPASS))
                        .save(output, "cft_radio_scanner");
                shaped(RecipeCategory.TOOLS, ModItems.MAGNIFIER)
                        .pattern("A")
                        .pattern("G")
                        .define('A', Items.AMETHYST_SHARD)
                        .define('G', Items.GOLD_INGOT)
                        .group("magnifier")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .unlockedBy(getHasName(Items.AMETHYST_SHARD), has(Items.AMETHYST_SHARD))
                        .save(output, "cft_magnifier");
                shaped(RecipeCategory.MISC, ModBlocks.RESEARCH_TABLE)
                        .pattern("BB")
                        .pattern("##")
                        .pattern("##")
                        .define('B', Items.BOOK)
                        .define('#', ItemTags.WOODEN_TOOL_MATERIALS)
                        .group("research_table")
                        .unlockedBy(getHasName(ModItems.MAGNIFIER), has(ModItems.MAGNIFIER))
                        .save(output, "cft_research_table");
                shaped(RecipeCategory.MISC, ModItems.TOMATO_SOUP)
                        .pattern("###")
                        .pattern("###")
                        .pattern(" B ")
                        .define('B', Items.BOWL)
                        .define('#', ModItems.TOMATO)
                        .group("tomato_soup")
                        .unlockedBy(getHasName(ModItems.TOMATO), has(ModItems.TOMATO))
                        .save(output, "cft_tomato_soup");
                shapeless(RecipeCategory.MISC, ModItems.TOMATO_JUICE)
                        .requires(Items.GLASS_BOTTLE, 1)
                        .requires(ModItems.TOMATO, 3)
                        .group("tomato_juice")
                        .unlockedBy(getHasName(ModItems.TOMATO), has(ModItems.TOMATO))
                        .save(output, "cft_tomato_juice");
                shapeless(RecipeCategory.MISC, ModItems.TOMATO_JELLY)
                        .requires(Items.GLASS_BOTTLE, 1)
                        .requires(Items.SUGAR, 1)
                        .requires(ModItems.TOMATO, 3)
                        .group("tomato_jelly")
                        .unlockedBy(getHasName(ModItems.TOMATO), has(ModItems.TOMATO))
                        .save(output, "cft_tomato_jelly");
                shapeless(RecipeCategory.MISC, ModItems.TOMATO_JELLY)
                        .requires(Items.SUGAR, 1)
                        .requires(ModItems.TOMATO_JUICE, 1)
                        .group("tomato_jelly")
                        .unlockedBy(getHasName(ModItems.TOMATO), has(ModItems.TOMATO))
                        .save(output, "cft_tomato_jelly_from_tomato_juice");
                shapeless(RecipeCategory.MISC, ModItems.TOMATO_SEEDS)
                        .requires(ModItems.TOMATO, 1)
                        .group("tomato_seeds")
                        .unlockedBy(getHasName(ModItems.TOMATO), has(ModItems.TOMATO))
                        .save(output, "cft_tomato_seeds");
            }
        };
    }



    @Override
    public @NonNull String getName() {
        return "Espercraft Recipes";
    }
}
