package net.adam.netheriteascension;

import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.block.entity.renderer.ObsidianAlterBlockEntityRenderer;
import net.adam.netheriteascension.block.entity.renderer.RitualAlterBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class NetheriteAscensionClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {

        BlockEntityRenderers.register(ModBlockEntities.RITUAL_ALTAR_BE, RitualAlterBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.OBSIDIAN_ALTAR_BE, ObsidianAlterBlockEntityRenderer::new);

    }
}
