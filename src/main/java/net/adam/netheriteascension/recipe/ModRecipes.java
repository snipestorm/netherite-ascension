package net.adam.netheriteascension.recipe;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.recipe.custom.AltarRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {

    public static final RecipeSerializer<AltarRecipe> ALTAR_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "altar_crafting"),
            new RecipeSerializer<>(AltarRecipe.CODEC,AltarRecipe.STREAM_CODEC));

    public static final RecipeType<AltarRecipe> ALTAR_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID,"altar_crafting"),
            new RecipeType<AltarRecipe>() {
                @Override
                public String toString() {
                    return "altar_crafting";
                }
            });


    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Recipes!");
    }
}
