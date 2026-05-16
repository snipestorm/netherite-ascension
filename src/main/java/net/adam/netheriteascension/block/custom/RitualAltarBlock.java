package net.adam.netheriteascension.block.custom;

import com.mojang.serialization.MapCodec;
import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.block.entity.custom.RitualAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class RitualAltarBlock extends AltarBlock {

    public static final MapCodec<RitualAltarBlock> CODEC = simpleCodec(RitualAltarBlock::new);

    public RitualAltarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new RitualAltarBlockEntity(worldPosition, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level plevel, BlockState pblockState, BlockEntityType<T> ptype) {

        if(plevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(ptype, ModBlockEntities.RITUAL_ALTAR_BE,
                (level,blockPos,blockState,ritualAltarBlockEntity) -> ritualAltarBlockEntity.tick(level, blockPos, blockState));
    }
}