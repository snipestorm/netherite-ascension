package net.adam.netheriteascension.util.renderers;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.entity.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ModRenderers {

    public static void altarStarThrownItemRenderer() {
        EntityRendererRegistry.register(
                ModEntities.ALTAR_STAR_ENTITY_ENTITY_TYPE,
                ThrownItemRenderer::new
        );
    }

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Renderers!");
        altarStarThrownItemRenderer();
    }
}
