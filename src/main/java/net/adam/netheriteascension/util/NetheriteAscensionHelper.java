package net.adam.netheriteascension.util;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.advancement.*;
import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.entity.ModEntities;
import net.adam.netheriteascension.item.ModCreativeModeTabs;
import net.adam.netheriteascension.item.ModItems;
import net.adam.netheriteascension.networking.ModPackets;
import net.adam.netheriteascension.recipe.ModRecipes;
import net.adam.netheriteascension.structure.ModStructures;
import net.adam.netheriteascension.util.loot.ModLootTableModifiers;
import net.adam.netheriteascension.util.renderers.ModRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class NetheriteAscensionHelper {

    public static final TagKey<Structure> ALTAR_STAR_STRUCTURE_KEY = TagKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "altar_star_target"));

    public static void initialiseMod() {
        NetheriteAscension.LOGGER.info("Initializing Netherite Ascension!");
    }

    public static void loadAllRegistries() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Helper!");
        ModItems.load();
        ModBlocks.load();
        ModStructures.load();
        ModEntities.load();
        ModBlockEntities.load();
        ModRecipes.load();
    }

    public static void loadAllUtilities() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Utilities!");
        ModPackets.loadPackets();
        ModRenderers.load();
        ModCreativeModeTabs.load();
        ModAdvancements.load();
        ModLootTableModifiers.load();
    }


}
