package net.adam.netheriteascension.datagen;

import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

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
                shaped(RecipeCategory.MISC, ModItems.ALTAR_STAR,1)
                        .pattern("DDD")
                        .pattern("DSD")
                        .pattern("DDD")
                        .define('S', Items.NETHER_STAR)
                        .define('D', Items.DIAMOND)
                        .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                        .save(output);

                copySmithingTemplate(ModItems.DIVINE_NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS,ModItems.DIVINE_NETHERITE_INGOT,RecipeCategory.BUILDING_BLOCKS,ModBlocks.DIVINE_NETHERITE_BLOCK);
            }
        };
    }

    @Override
    public String getName() {
        return "Netherite Ascension Recipes";
    }
}
