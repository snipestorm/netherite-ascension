package net.adam.netheriteascension.util;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.entity.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class NetheriteAscensionHelper {

    public static final TagKey<Structure> ALTAR_STAR_STRUCTURE_KEY = TagKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "altar_star_target"));

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Helper!");
    }

    public static void runAll() {
        AltarStarThrownItemRenderer();
    }

    public static void AltarStarThrownItemRenderer() {
        EntityRendererRegistry.register(
                ModEntities.ALTAR_STAR_ENTITY_ENTITY_TYPE,
                context -> new ThrownItemRenderer<>(context)
        );
    }
}
