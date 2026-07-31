package net.adam.netheriteascension.datagen;

import net.adam.netheriteascension.item.ModItems;
import net.adam.netheriteascension.util.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    /**
     * Implement this method and then use {@link FabricTagsProvider#builder} to get and register new tag builders.
     *
     * @param registries
     */
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.DIVINE_NETHERITE_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.DIVINE_NETHERITE_PICKAXE));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.DIVINE_NETHERITE_AXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.DIVINE_NETHERITE_SHOVEL));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.DIVINE_NETHERITE_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.DIVINE_NETHERITE_SPEAR));

        tag(ItemTags.HEAD_ARMOR)
        .add(ModItems.getRK(ModItems.DIVINE_NETHERITE_HELMET));
        tag(ItemTags.CHEST_ARMOR)
        .add(ModItems.getRK(ModItems.DIVINE_NETHERITE_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR)
        .add(ModItems.getRK(ModItems.DIVINE_NETHERITE_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR)
        .add(ModItems.getRK(ModItems.DIVINE_NETHERITE_BOOTS));




        tag(ModTags.Items.DIVINE_NETHERITE_REPAIRABLE)
                .add(ModItems.getRK(ModItems.DIVINE_NETHERITE_INGOT));

    }
}
