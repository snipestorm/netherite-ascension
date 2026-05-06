package net.adam.netheriteascension.entity;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.item.custom.AltarStarEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> ALTAR_STAR_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "altar_star"));

    public static final EntityType<AltarStarEntity> ALTAR_STAR_ENTITY_ENTITY_TYPE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "altar_star"),
            EntityType.Builder.<AltarStarEntity>of(AltarStarEntity::new,MobCategory.MISC).noLootTable().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(4).build(ALTAR_STAR_KEY));

    public static void load() {
        NetheriteAscension.LOGGER.info("Loading Mod Entities");
    }
}
