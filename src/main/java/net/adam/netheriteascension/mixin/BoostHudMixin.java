package net.adam.netheriteascension.mixin;

import net.adam.netheriteascension.item.ModItems;
import net.adam.netheriteascension.util.BoostState;
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
    private void renderBoostHud(GuiGraphicsExtractor graphics,
                                DeltaTracker deltaTracker,
                                CallbackInfo ci) {

        Minecraft client = Minecraft.getInstance();
        var chest = client.player.getItemBySlot(EquipmentSlot.CHEST);
        if (client.player == null) return;

        if (!client.player.isFallFlying()) return;

        if (!client.player.getItemBySlot(EquipmentSlot.CHEST)
                .is(ModItems.DIVINE_NETHERITE_ELYTRA)) return;

        if(client.player.getCooldowns().isOnCooldown(chest.getItem().getDefaultInstance())) return;

        int screenWidth = graphics.guiWidth();
        int screenHeight = graphics.guiHeight();

        int barX = (screenWidth - BAR_WIDTH) / 2;
        int barY = screenHeight - HUD_OFFSET_Y;

        float charge = Math.max(0.0f, Math.min(1.0f, BoostState.boostCharge));

        graphics.nextStratum();

        // =====================================================
        // 🔥 HEAT COLOR (cyan → yellow → red)
        // =====================================================
        float t = charge;

        int r, g, b;

        if (t < 0.5f) {
            float p = t / 0.5f;

            // cyan → yellow
            r = (int)(255 * p);
            g = 255;
            b = (int)(255 * (1.0f - p));

        } else {
            float p = (t - 0.5f) / 0.5f;

            // yellow → red
            r = 255;
            g = (int)(255 * (1.0f - p));
            b = 0;
        }

        int fillColor = (0xFF << 24) | (r << 16) | (g << 8) | b;

        // =====================================================
        // BAR BACKGROUND
        // =====================================================
        graphics.fill(barX, barY, barX + BAR_WIDTH, barY + BAR_HEIGHT, 0xAA000000);

        graphics.fill(
                barX,
                barY,
                barX + (int)(BAR_WIDTH * charge),
                barY + BAR_HEIGHT,
                fillColor
        );

        boolean full = charge >= 1.0f;

        float time = client.player.tickCount
                + deltaTracker.getGameTimeDeltaPartialTick(false);

        float pulse = (float)(Math.sin(time * 0.35f) * 0.5f + 0.5f);

        // =====================================================
        // ✨ GLOWING BORDER (matches heat color)
        // =====================================================
        float glowStrength = 0.4f + (charge * 0.6f);
        float glow = glowStrength * (0.6f + 0.4f * pulse);

        int br = Math.min(255, (int)(r * glow + 40));
        int bg = Math.min(255, (int)(g * glow + 40));
        int bb = Math.min(255, (int)(b * glow + 40));

        int borderColor = (0xFF << 24) | (br << 16) | (bg << 8) | bb;

        graphics.fill(barX - 1, barY - 1, barX + BAR_WIDTH + 1, barY, borderColor);
        graphics.fill(barX - 1, barY + BAR_HEIGHT, barX + BAR_WIDTH + 1, barY + BAR_HEIGHT + 1, borderColor);
        graphics.fill(barX - 1, barY, barX, barY + BAR_HEIGHT, borderColor);
        graphics.fill(barX + BAR_WIDTH, barY, barX + BAR_WIDTH + 1, barY + BAR_HEIGHT, borderColor);

        // =====================================================
        // 💎 TITLE (cyan)
        // =====================================================
        String text = "🚀 BOOST 🚀";
        int textWidth = client.font.width(text);

        int titleX = screenWidth / 2 - textWidth / 2;
        int titleY = barY - TITLE_GAP;

        float c = charge;

        int tr, tg, tb;

        if (c < 0.5f) {
            float p = c / 0.5f;

            // cyan → yellow
            tr = (int)(255 * p);
            tg = 255;
            tb = (int)(255 * (1.0f - p));

        } else {
            float p = (c - 0.5f) / 0.5f;

            // yellow → red
            tr = 255;
            tg = (int)(255 * (1.0f - p));
            tb = 0;
        }

// slight glow boost for readability
        float titleGlow = 0.6f + (charge * 0.4f);

        int titleColor = (0xFF << 24) |
                (Math.min(255, (int)(tr * titleGlow)) << 16) |
                (Math.min(255, (int)(tg * titleGlow)) << 8) |
                (Math.min(255, (int)(tb * titleGlow)));

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