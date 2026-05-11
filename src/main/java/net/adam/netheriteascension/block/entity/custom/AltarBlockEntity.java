package net.adam.netheriteascension.block.entity.custom;

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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public abstract class AltarBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {
    public  NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    public int count = 0;
    public int maxCount = 60;
    public int ritualCount = 0;
    public int ritualMaxCount = 100;
    public boolean ritualActive = false;
    public  boolean craftingActive = false;
    public boolean hadCraftedItemLastTick = false;
    public boolean craftingComplete = false;

    @Nullable
    private UUID lastInteractingPlayer;

    public AltarBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        super(type, worldPosition, blockState);
    }
    public void setLastPlayer(Player player) {
        this.lastInteractingPlayer = player.getUUID();
    }

    @Nullable
    public ServerPlayer getLastPlayer() {
        if (level instanceof ServerLevel serverLevel &&
                lastInteractingPlayer != null) {

            return serverLevel.getServer()
                    .getPlayerList()
                    .getPlayer(lastInteractingPlayer);
        }

        return null;
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return inventory.getFirst();
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
            setChanged();
            inventory.set(0, itemStack.copyWithCount(1));
        }


    @Override
    public void clearContent() {
        inventory.set(0, ItemStack.EMPTY);
    }

    public void drops() {
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.inventory);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (!level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}