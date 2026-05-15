package net.adam.netheriteascension.networking;

import net.adam.netheriteascension.NetheriteAscension;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;

public class ModPackets {

    public static void registerClientbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        registry.register(AltarCraftingPayloadS2C.TYPE, AltarCraftingPayloadS2C.STREAM_CODEC);


        ClientPlayNetworking.registerGlobalReceiver(AltarCraftingPayloadS2C.TYPE, ClientBoundPackets::handleAltarCraftPayload);

    }

    public static void registerServerbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        registry.register(BoostPayloadC2S.TYPE, BoostPayloadC2S.STREAM_CODEC);

        registerServerReceivers();
    }

    public static void loadPackets() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Networking!");
        registerClientbound(PayloadTypeRegistry.clientboundPlay());
        registerServerbound(PayloadTypeRegistry.serverboundPlay());
    }

    public static void registerServerReceivers() {

        ServerPlayNetworking.registerGlobalReceiver(
                BoostPayloadC2S.TYPE,
                (payload, context) -> {

                    context.server().execute(() -> {

                        var player = context.player();

                        ServerBoundPackets.handleBoostRequest(payload,context,player);
                    });
                }
        );
    }

}
