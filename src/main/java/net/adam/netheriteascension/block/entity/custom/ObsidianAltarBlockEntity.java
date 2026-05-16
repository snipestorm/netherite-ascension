package net.adam.netheriteascension.block.entity.custom;

import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.networking.AltarCraftingPayloadS2C;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

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

