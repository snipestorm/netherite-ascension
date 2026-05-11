package net.adam.netheriteascension.recipe.custom;

import net.adam.netheriteascension.recipe.ModRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public record AltarRecipeInput (ItemStack ritualAltarItem, List<ItemStack> obsidianAltarItems) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        if (index == 0) {
            return ritualAltarItem;
        }
        return obsidianAltarItems.get(index - 1);
    }

    @Override
    public int size() {
        return 9;
    }
}
