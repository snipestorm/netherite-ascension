package net.adam.netheriteascension.networking;


// WE ARE ON THE CLIENT!//

import net.adam.netheriteascension.block.entity.custom.ObsidianAltarBlockEntity;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientBoundPackets {

    public static void handleAltarCraftPayload(AltarCraftingPayloadS2C packet, ClientPlayNetworking.Context context) {
        if(context.player().level().getBlockEntity(packet.blockEntityPosition()) instanceof ObsidianAltarBlockEntity obsidianAltarBlockEntity) {
            obsidianAltarBlockEntity.setTheItem(packet.itemStack());
        }
    }
}
