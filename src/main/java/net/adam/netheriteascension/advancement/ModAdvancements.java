package net.adam.netheriteascension.advancement;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.advancement.custom.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;

public class ModAdvancements {

    public static void altarStarUsedOnAltar(ServerPlayer serverPlayer) {
        AltarStarUsedOnAltar.INSTANCE.trigger(serverPlayer);
    }

    public static void altarCrafting(ServerPlayer serverPlayer) {
        AltarCrafting.INSTANCE.trigger(serverPlayer);
    }

    public static void alterStarThrownOverworld(ServerPlayer serverPlayer) {
        AltarStarThrownOverworld.INSTANCE.trigger(serverPlayer);
        AltarStarThrownOverworldSubAchievementTrigger.INSTANCE.trigger(serverPlayer);
    }

    public static void alterStarThrownNether(ServerPlayer serverPlayer) {
        AltarStarThrownNether.INSTANCE.trigger(serverPlayer);
        AltarStarThrownNetherSubAchievementTrigger.INSTANCE.trigger(serverPlayer);
    }

    public static void alterStarThrownEnd(ServerPlayer serverPlayer) {
        AltarStarThrownEnd.INSTANCE.trigger(serverPlayer);
        AltarStarThrownEndSubAchievementTrigger.INSTANCE.trigger(serverPlayer);
    }

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Advancements!");
        registerAchievementTriggers();
    }

    public static void registerAchievementTriggers() {

        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarStarThrownOverworld.ID,
                AltarStarThrownOverworld.INSTANCE
        );
        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarStarThrownOverworldSubAchievementTrigger.ID,
                AltarStarThrownOverworldSubAchievementTrigger.INSTANCE
        );
        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarStarThrownNether.ID,
                AltarStarThrownNether.INSTANCE
        );
        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarStarThrownNetherSubAchievementTrigger.ID,
                AltarStarThrownNetherSubAchievementTrigger.INSTANCE
        );
        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarStarThrownEnd.ID,
                AltarStarThrownEnd.INSTANCE
        );
        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarStarThrownEndSubAchievementTrigger.ID,
                AltarStarThrownEndSubAchievementTrigger.INSTANCE
        );
        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarStarUsedOnAltar.ID,
                AltarStarUsedOnAltar.INSTANCE
        );
        Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                AltarCrafting.ID,
                AltarCrafting.INSTANCE
        );
    }
}