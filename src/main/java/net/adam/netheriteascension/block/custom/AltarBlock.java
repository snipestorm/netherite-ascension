package net.adam.netheriteascension.block.custom;

import net.adam.netheriteascension.block.entity.custom.AltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public abstract class AltarBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Block.box(2,0,2,14,16,14);


    public AltarBlock(Properties properties) {
        super(properties);
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }


    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (level.getBlockEntity(pos) instanceof AltarBlockEntity altarBlockEntity) {
            altarBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {


        if (level.getBlockEntity(pos) instanceof AltarBlockEntity altarBlockEntity) {
            boolean isAltarEmpty = altarBlockEntity.isEmpty();
            altarBlockEntity.setLastPlayer(player);


            if(isAltarEmpty && !itemStack.isEmpty()) {
                altarBlockEntity.setTheItem(itemStack);
                itemStack.shrink(1);
                level.playSound(player,pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS,1f,2f);
            } else if  (!isAltarEmpty ) {
                ItemStack stackOnAltar = altarBlockEntity.getTheItem();
                altarBlockEntity.clearContent();

                if (!player.getInventory().add(stackOnAltar)) {
                    player.drop(stackOnAltar, false);
                }
                level.playSound(player,pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS,1f,1f);
            }
        }
        return InteractionResult.SUCCESS;

    }
}