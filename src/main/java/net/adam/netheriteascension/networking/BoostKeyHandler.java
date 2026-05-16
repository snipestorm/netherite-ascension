package net.adam.netheriteascension.networking;

import net.adam.netheriteascension.item.ModItems;
import net.adam.netheriteascension.util.misc.BoostState;
import net.adam.netheriteascension.util.keybinds.ModKeybinds;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.EquipmentSlot;

public class BoostKeyHandler {

    private static int holdTicks = 0;
    private static boolean wasDown = false;
    private static final int MAX_CHARGE_TICKS = 60;

    public static void load() {



        ClientTickEvents.END_CLIENT_TICK.register(client -> {



            if (client.player == null) return;
            if (client.getConnection() == null) return;

            boolean wearingBoostElytra =
                    client.player.getItemBySlot(EquipmentSlot.CHEST)
                            .is(ModItems.DIVINE_NETHERITE_ELYTRA);

            boolean elytraOnCooldown = client.player.getCooldowns().isOnCooldown(
                    client.player.getItemBySlot(EquipmentSlot.CHEST).getItem().getDefaultInstance()
            );

            boolean down = ModKeybinds.BOOST_KEY.isDown()&& wearingBoostElytra
                    && !elytraOnCooldown;
            BoostState.boosting = down;



            // KEY DOWN
            if (down) {
                holdTicks = Math.min(MAX_CHARGE_TICKS, holdTicks + 1);
            } else {
                holdTicks = Math.max(0, holdTicks - 2); // decay feel (optional)
            }

            BoostState.boostCharge = holdTicks / (float) MAX_CHARGE_TICKS;
            // KEY RELEASED
            if (!down && wasDown) {

                ClientPlayNetworking.send(
                        new BoostPayloadC2S(holdTicks)
                );

                holdTicks = 0;
                BoostState.boostCharge = 0.0F;
            }

            wasDown = down;
        });
    }

    public boolean canBoost() {
        return ModKeybinds.BOOST_KEY.isDown();
    }


}