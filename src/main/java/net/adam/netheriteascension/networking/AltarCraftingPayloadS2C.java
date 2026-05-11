package net.adam.netheriteascension.networking;

import net.adam.netheriteascension.NetheriteAscension;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public record AltarCraftingPayloadS2C(BlockPos blockEntityPosition, ItemStack itemStack) implements CustomPacketPayload {

    public static final Type<AltarCraftingPayloadS2C> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "altar_crafting_payload_s2c"));

    public static final StreamCodec<RegistryFriendlyByteBuf, AltarCraftingPayloadS2C> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            AltarCraftingPayloadS2C::blockEntityPosition,

            ItemStack.OPTIONAL_STREAM_CODEC,
            AltarCraftingPayloadS2C::itemStack,

            AltarCraftingPayloadS2C::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
