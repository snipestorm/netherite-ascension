package net.adam.netheriteascension.datagen;

import net.adam.netheriteascension.item.ModItems;
import net.adam.netheriteascension.util.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.Nullable;

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
        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.DIVINE_NETHERITE_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES).add(ModItems.DIVINE_NETHERITE_PICKAXE);
        valueLookupBuilder(ItemTags.AXES).add(ModItems.DIVINE_NETHERITE_AXE);
        valueLookupBuilder(ItemTags.SHOVELS).add(ModItems.DIVINE_NETHERITE_SHOVEL);
        valueLookupBuilder(ItemTags.HOES).add(ModItems.DIVINE_NETHERITE_HOE);
        valueLookupBuilder(ItemTags.SPEARS).add(ModItems.DIVINE_NETHERITE_SPEAR);

        valueLookupBuilder(ModTags.Items.DIVINE_NETHERITE_REPAIRABLE)
                .add(ModItems.DIVINE_NETHERITE_INGOT);

    }
}
