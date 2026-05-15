package net.adam.netheriteascension.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.adam.netheriteascension.recipe.ModRecipes;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public record AltarRecipe(List<Ingredient> ingredients, ItemStackTemplate output) implements Recipe<AltarRecipeInput> {


    public static final MapCodec<AltarRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(AltarRecipe::ingredients),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(AltarRecipe::output)).apply(inst, AltarRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, AltarRecipe> STREAM_CODEC =
            StreamCodec.composite(Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), AltarRecipe::ingredients,
                    ItemStackTemplate.STREAM_CODEC, AltarRecipe::output, AltarRecipe::new);


    @Override
    public boolean matches(AltarRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        if (!ingredients.get(0).test(input.ritualAltarItem())) {
            return false;
        }
        for (int i = 0; i < input.obsidianAltarItems().size(); i++) {
            if (!ingredients.get(i + 1).test(input.obsidianAltarItems().get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(AltarRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "altar";
    }

    @Override
    public RecipeSerializer<? extends Recipe<AltarRecipeInput>> getSerializer() {
        return ModRecipes.ALTAR_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<AltarRecipeInput>> getType() {
        return ModRecipes.ALTAR_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
