package net.adam.netheriteascension.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.adam.netheriteascension.item.ModItems;
import net.adam.netheriteascension.util.misc.BoostState;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class BoostHudMixin {

    private static final int BAR_WIDTH = 80;
    private static final int BAR_HEIGHT = 6;
    private static final int HUD_OFFSET_Y = 49;
    private static final int TITLE_GAP = 10;

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void renderBoostHud(
            DeltaTracker deltaTracker,
            boolean shouldRenderLevel,
            boolean resourcesLoaded,
            CallbackInfo ci,
            @Local GuiGraphicsExtractor graphics
    ) {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null)
            return;

        if (!client.player.isFallFlying())
            return;

        var chest = client.player.getItemBySlot(EquipmentSlot.CHEST);

        if (!chest.is(ModItems.DIVINE_NETHERITE_ELYTRA))
            return;

        boolean onCooldown = client.player.getCooldowns().isOnCooldown(chest.getItem().getDefaultInstance());
        if (onCooldown)
            return;

        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        int barX = (screenWidth - BAR_WIDTH) / 2;
        int barY = screenHeight - HUD_OFFSET_Y;

        float charge = Math.max(0.0F, Math.min(1.0F, BoostState.boostCharge));

        graphics.nextStratum();

        float t = charge;

        int r, g, b;

        if (t < 0.5F) {
            float p = t / 0.5F;
            r = (int)(255 * p);
            g = 255;
            b = (int)(255 * (1.0F - p));
        } else {
            float p = (t - 0.5F) / 0.5F;
            r = 255;
            g = (int)(255 * (1.0F - p));
            b = 0;
        }

        int fillColor = (0xFF << 24) | (r << 16) | (g << 8) | b;

        graphics.fill(barX, barY, barX + BAR_WIDTH, barY + BAR_HEIGHT, 0xAA000000);
        graphics.fill(
                barX,
                barY,
                barX + (int)(BAR_WIDTH * charge),
                barY + BAR_HEIGHT,
                fillColor
        );

        float time = client.player.tickCount +
                deltaTracker.getGameTimeDeltaPartialTick(false);

        float pulse = (float)(Math.sin(time * 0.35F) * 0.5F + 0.5F);

        float glowStrength = 0.4F + charge * 0.6F;
        float glow = glowStrength * (0.6F + 0.4F * pulse);

        int br = Math.min(255, (int)(r * glow + 40));
        int bg = Math.min(255, (int)(g * glow + 40));
        int bb = Math.min(255, (int)(b * glow + 40));

        int borderColor = (0xFF << 24) | (br << 16) | (bg << 8) | bb;

        graphics.fill(barX - 1, barY - 1, barX + BAR_WIDTH + 1, barY, borderColor);
        graphics.fill(barX - 1, barY + BAR_HEIGHT, barX + BAR_WIDTH + 1, barY + BAR_HEIGHT + 1, borderColor);
        graphics.fill(barX - 1, barY, barX, barY + BAR_HEIGHT, borderColor);
        graphics.fill(barX + BAR_WIDTH, barY, barX + BAR_WIDTH + 1, barY + BAR_HEIGHT, borderColor);

        String text = "🚀 BOOST 🚀";
        int textWidth = client.font.width(text);

        int titleX = screenWidth / 2 - textWidth / 2;
        int titleY = barY - TITLE_GAP;

        float c = charge;

        int tr, tg, tb;

        if (c < 0.5F) {
            float p = c / 0.5F;
            tr = (int)(255 * p);
            tg = 255;
            tb = (int)(255 * (1.0F - p));
        } else {
            float p = (c - 0.5F) / 0.5F;
            tr = 255;
            tg = (int)(255 * (1.0F - p));
            tb = 0;
        }

        float titleGlow = 0.6F + charge * 0.4F;

        int titleColor = (0xFF << 24)
                | (Math.min(255, (int)(tr * titleGlow)) << 16)
                | (Math.min(255, (int)(tg * titleGlow)) << 8)
                | Math.min(255, (int)(tb * titleGlow));

        graphics.textWithBackdrop(
                client.font,
                Component.literal(text),
                titleX,
                titleY,
                textWidth,
                titleColor
        );
    }
}