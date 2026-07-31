package net.adam.netheriteascension.datagen;

import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.util.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    /**
     * Implement this method and then use {@link FabricTagsProvider#builder} to get and register new tag builders.
     *
     * @param registries
     */
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DIVINE_NETHERITE_BLOCK.properties().blockIdOrThrow())
                        .add(ModBlocks.DIVINE_ALTAR.properties().blockIdOrThrow());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.DIVINE_NETHERITE_BLOCK.properties().blockIdOrThrow());

        tag(ModTags.Blocks.NEEDS_DIVINE_NETHERITE_TOOL)
            .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.DIVINE_ALTAR.properties().blockIdOrThrow());
        tag(ModTags.Blocks.INCORRECT_FOR_DIVINE_NETHERITE_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);

    }
}
