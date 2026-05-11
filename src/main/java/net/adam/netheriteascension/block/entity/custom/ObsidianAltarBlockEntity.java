package net.adam.netheriteascension.block.entity.custom;

import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.networking.AltarCraftingPayloadS2C;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.joml.Vector3i;
import org.jspecify.annotations.Nullable;

public class ObsidianAltarBlockEntity extends AltarBlockEntity {


    public ObsidianAltarBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.OBSIDIAN_ALTAR_BE, worldPosition, blockState);
    }

    @Override
    public void clearContent() {
        inventory.set(0, ItemStack.EMPTY);
        setChanged();

        if(!level.isClientSide()) {
            for(ServerPlayer player : PlayerLookup.tracking((ServerLevel) level, getBlockPos())) {
                ServerPlayNetworking.send(player, new AltarCraftingPayloadS2C(this.worldPosition,inventory.get(0)));
            }
        }
    }

}

