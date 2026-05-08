package net.adam.netheriteascension.item.custom;

import net.adam.netheriteascension.advancement.*;
import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.block.custom.DivineAltarBlock;
import net.adam.netheriteascension.entity.AltarStarEntity;
import net.adam.netheriteascension.util.NetheriteAscensionHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class AltarStarItem extends Item {


    public AltarStarItem(Properties properties) {
        super(properties);

    }

    public boolean endmessageSent = false;
    public boolean overworldmessageSent = false;
    public boolean nethermessageSent = false;

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);

        if (Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.netherite-ascension.altar_star_launch"));
              } else {
            builder.accept(Component.translatable("tooltip.netherite-ascension.altar_star"));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // Check it's your altar and not already lit
        if (state.is(ModBlocks.DIVINE_ALTAR) &&
                !state.getValue(DivineAltarBlock.lit)) {

            if (!level.isClientSide()) {
                serverAltarStarPlace(level, pos, state, context);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private void serverAltarStarPlace(Level level, BlockPos pos, BlockState state, UseOnContext context) {
        Player player = context.getPlayer();
        ServerPlayer serverPlayer = (ServerPlayer) player;
        ServerLevel serverWorld = (ServerLevel) level;



        // Set altar to lit
        BlockState newState = state.setValue(DivineAltarBlock.lit, true);
        level.setBlock(pos, newState, 3);
        serverWorld.playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.END_PORTAL_SPAWN, SoundSource.NEUTRAL, 1.0F, 1.0F);
        // Consume item
        context.getItemInHand().shrink(1);
        // ⏱ Delay (40 ticks = 2 seconds)
        level.scheduleTick(pos, newState.getBlock(), 80);
        ModAdvancements.altarStarUsedOnAltar(serverPlayer);


    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        ResourceKey<Level> dimensions = level.dimension();
        if (hitResult.getType() == HitResult.Type.BLOCK && level.getBlockState(hitResult.getBlockPos()).is(ModBlocks.DIVINE_ALTAR)) {
            return InteractionResult.PASS;
        } else {
            player.startUsingItem(usedHand);
            if (dimensions == level.OVERWORLD && !level.isClientSide() && !overworldmessageSent) {
                player.sendSystemMessage(Component.literal("Seems to be an Ancient Star, It is trying to lead me somewhere, but it doesn't seem to work in The Overworld..."));
                overworldmessageSent = true;
                ModAdvancements.alterStarThrownOverworld((ServerPlayer) player);
            }
            if (dimensions == level.NETHER && !level.isClientSide() && !nethermessageSent) {
                player.sendSystemMessage(Component.literal("Seems to be an Ancient Star, It is trying to lead me somewhere, but it doesn't seem to work in The Nether..."));
                nethermessageSent = true;
                ModAdvancements.alterStarThrownNether((ServerPlayer) player);
            }
            if (dimensions == level.END && !level.isClientSide() && !endmessageSent) {
                player.sendSystemMessage(Component.literal("Seems to be an Ancient Star, It is working in The End, I wonder where it will take me..."));
                endmessageSent = true;
                ModAdvancements.alterStarThrownEnd((ServerPlayer) player);
            }
            //more irrelevant code below not related to query//
            if (level instanceof ServerLevel serverLevel) {

                BlockPos nearestMapFeature = serverLevel.findNearestMapStructure(NetheriteAscensionHelper.ALTAR_STAR_STRUCTURE_KEY, player.blockPosition(), 100, false);
                if (nearestMapFeature == null) {
                    return InteractionResult.CONSUME;
                }
                AltarStarEntity eyeOfEnder = new AltarStarEntity(level, player.getX(), player.getY(0.5), player.getZ());
                eyeOfEnder.setItem(itemStack);
                eyeOfEnder.signalTo(Vec3.atLowerCornerOf(nearestMapFeature));
                level.gameEvent(GameEvent.PROJECTILE_SHOOT, eyeOfEnder.position(), GameEvent.Context.of(player));
                level.addFreshEntity(eyeOfEnder);
                if (player instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.USED_ENDER_EYE.trigger(serverPlayer, nearestMapFeature);
                }

                float pitch = Mth.lerp(level.getRandom().nextFloat(), 0.33F, 0.5F);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 1.0F, pitch);
                itemStack.consume(1, player);
                player.awardStat(Stats.ITEM_USED.get(this));

            }
            return InteractionResult.SUCCESS_SERVER;
        }
    }

}
