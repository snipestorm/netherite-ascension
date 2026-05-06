package net.adam.netheriteascension.util;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.block.custom.DivineAltarBlock;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AltarActivationManager {

    private static int tickCounter = 0;

    public static void load() {

        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Altar Activation!");

        ServerTickEvents.END_SERVER_TICK.register(server -> {


            tickCounter++;
            if (tickCounter % 20 != 0) return;

            ServerLevel end = server.getLevel(Level.END);

            // ✅ If The End is not loaded, do nothing
            if (end == null) return;

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {

                ServerLevel level = player.level();
                BlockPos playerPos = player.blockPosition();

                int centerChunkX = playerPos.getX() >> 4;
                int centerChunkZ = playerPos.getZ() >> 4;

                int chunkRadius = 8;

                for (int dx = -chunkRadius; dx <= chunkRadius; dx++) {
                    for (int dz = -chunkRadius; dz <= chunkRadius; dz++) {

                        int chunkX = centerChunkX + dx;
                        int chunkZ = centerChunkZ + dz;

                        int baseX = chunkX << 4;
                        int baseZ = chunkZ << 4;

                        for (int y = -64; y <= 320; y += 16) {

                            BlockPos pos = new BlockPos(baseX, y, baseZ);

                            BlockState state = level.getBlockState(pos);

                            if (state.getBlock() instanceof DivineAltarBlock altar) {

                                if (!state.getValue(DivineAltarBlock.lit)) {
                                    level.scheduleTick(pos, altar, 1);
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}