package net.adam.netheriteascension.util;

import net.adam.netheriteascension.NetheriteAscension;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.gamerules.GameRules;

public class GameRuleOverride {

    public static void load() {

            NetheriteAscension.LOGGER.info("Setting Elytra Movement Check To False!");

            ServerLifecycleEvents.SERVER_STARTED.register((MinecraftServer server) -> {

                for (ServerLevel world : server.getAllLevels()) {

                    world.getGameRules().set(
                            GameRules.ELYTRA_MOVEMENT_CHECK,
                            false,
                            server
                    );

                }

            });
        }

    }
