package net.adam.netheriteascension.block.custom;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class ChiseledStoneAltarBlock extends Block {

    public static final BooleanProperty lit = BooleanProperty.create("lit");

    public ChiseledStoneAltarBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(lit,false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(lit,false);
    }
}
