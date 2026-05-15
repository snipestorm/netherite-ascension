package net.adam.netheriteascension.networking;

import net.adam.netheriteascension.NetheriteAscension;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record BoostPayloadC2S(int heldTicks) implements CustomPacketPayload {

    public static final Type<BoostPayloadC2S> TYPE =
            new Type<>(
                    Identifier.fromNamespaceAndPath(
                            NetheriteAscension.MOD_ID,
                            "boost"
                    )
            );

    public static final StreamCodec<
            RegistryFriendlyByteBuf,
            BoostPayloadC2S
            > STREAM_CODEC =
            StreamCodec.of(
                    (buf, payload) -> buf.writeInt(payload.heldTicks()),
                    buf -> new BoostPayloadC2S(buf.readInt())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

