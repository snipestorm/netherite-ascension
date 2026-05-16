package net.adam.netheriteascension.block.custom;

import com.mojang.serialization.MapCodec;
import net.adam.netheriteascension.block.entity.custom.ObsidianAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class ObsidianAltarBlock extends AltarBlock {
    public static final MapCodec<ObsidianAltarBlock> CODEC = simpleCodec(ObsidianAltarBlock::new);

    public ObsidianAltarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new ObsidianAltarBlockEntity (worldPosition, blockState);
    }

}