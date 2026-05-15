package net.adam.netheriteascension.util;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.advancement.ModAdvancements;
import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.block.entity.renderer.ObsidianAlterBlockEntityRenderer;
import net.adam.netheriteascension.block.entity.renderer.RitualAlterBlockEntityRenderer;
import net.adam.netheriteascension.entity.ModEntities;
import net.adam.netheriteascension.item.ModCreativeModeTabs;
import net.adam.netheriteascension.item.ModItems;
import net.adam.netheriteascension.networking.ModPackets;
import net.adam.netheriteascension.recipe.ModRecipes;
import net.adam.netheriteascension.structure.ModStructures;
import net.adam.netheriteascension.util.keybinds.ModKeybinds;
import net.adam.netheriteascension.util.loot.ModLootTableModifiers;
import net.adam.netheriteascension.util.renderers.ModRenderers;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class NetheriteAscensionClientHelper {

 public static void loadAllClientRegistries() {
     BlockEntityRenderers.register(ModBlockEntities.RITUAL_ALTAR_BE, RitualAlterBlockEntityRenderer::new);
     BlockEntityRenderers.register(ModBlockEntities.OBSIDIAN_ALTAR_BE, ObsidianAlterBlockEntityRenderer::new);
     ModRenderers.load();
     ModKeybinds.load();
 }


}
