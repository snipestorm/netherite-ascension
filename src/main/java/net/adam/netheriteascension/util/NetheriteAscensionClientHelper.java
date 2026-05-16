package net.adam.netheriteascension.util;

import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.block.entity.renderer.ObsidianAlterBlockEntityRenderer;
import net.adam.netheriteascension.block.entity.renderer.RitualAlterBlockEntityRenderer;
import net.adam.netheriteascension.util.keybinds.ModKeybinds;
import net.adam.netheriteascension.util.renderers.ModRenderers;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class NetheriteAscensionClientHelper {

 public static void loadAllClientRegistries() {
     BlockEntityRenderers.register(ModBlockEntities.RITUAL_ALTAR_BE, RitualAlterBlockEntityRenderer::new);
     BlockEntityRenderers.register(ModBlockEntities.OBSIDIAN_ALTAR_BE, ObsidianAlterBlockEntityRenderer::new);
     ModRenderers.load();
     ModKeybinds.load();
 }


}
