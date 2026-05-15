package net.adam.netheriteascension.networking;



// WE ARE ON THE SERVER! //

import net.adam.netheriteascension.item.ModItems;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class ServerBoundPackets {

    public static void handleBoostRequest(
            BoostPayloadC2S packet,
            ServerPlayNetworking.Context context,
            ServerPlayer player
    ) {

        // must be gliding
        if (!player.isFallFlying()) return;

        // must wear your elytra
        var chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!chest.is(ModItems.DIVINE_NETHERITE_ELYTRA)) {
            return;
        }

        // cooldown check
        if (player.getCooldowns().isOnCooldown(chest.getItem().getDefaultInstance())) {
            return;
        }

        player.getCooldowns().addCooldown(
                chest.getItem().getDefaultInstance(),
                200
        );

        // how long key was held
        int held = packet.heldTicks();

        // cap max charge
        held = Math.min(held, 60);

        // convert hold duration into boost strength
        double strength = 2 + (held * 1.0);

        spawnBoostRocket(player, strength);

        if (strength >= 55) {
            playSonicBoom(player);
            spawnExplosion(player);

        }
    }

    private static void playSonicBoom(ServerPlayer player) {
        player.level().playSound(
                null,
                player,
                SoundEvents.WARDEN_SONIC_BOOM,
                SoundSource.PLAYERS,
                3.0F,
                1.0F
        );
    }

    private static void spawnExplosion(ServerPlayer player) {
        ServerLevel level = (ServerLevel) player.level();
        level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, player.getX() + 0.5f, player.getY() + 1.2f, player.getZ() + 0.5f, 0, 0, 0, 0, 0.25f);
    }

    private static void spawnBoostRocket(
            ServerPlayer player,
            double forwardBoost
    ) {

        ServerLevel level = (ServerLevel) player.level();

        // create rocket
        ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);

        rocket.set(
                net.minecraft.core.component.DataComponents.FIREWORKS,
                new net.minecraft.world.item.component.Fireworks(
                        3,
                        java.util.List.of()
                )
        );

        // attach rocket to player
        FireworkRocketEntity firework = new FireworkRocketEntity(
                level,
                rocket,
                player
        );

        level.addFreshEntity(firework);

        // player movement
        Vec3 look = player.getLookAngle();
        Vec3 velocity = player.getDeltaMovement();

        double upwardBoost = 0.15;

        player.setDeltaMovement(
                velocity.add(
                        look.x * forwardBoost,
                        look.y * upwardBoost,
                        look.z * forwardBoost
                )
        );

        player.hurtMarked = true;
    }
}