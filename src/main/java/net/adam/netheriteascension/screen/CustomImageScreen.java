package net.adam.netheriteascension.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class CustomImageScreen extends Screen {

    private final ScreenData data;

    public CustomImageScreen(ScreenData data) {
        super(Component.empty());
        this.data = data;
    }

    @Override
    public void extractRenderState(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float tickDelta
    ) {

        // IMPORTANT: render AFTER pipeline is set up
        super.extractRenderState(graphics, mouseX, mouseY, tickDelta);

        int x = (this.width - data.width()) / 2;
        int y = (this.height - data.height()) / 2;

        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                data.texture(),
                x,
                y,
                0,
                0,
                data.width(),
                data.height(),
                data.width(),
                data.height()
        );
    }

    @Override
    public void extractBackground(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float tickDelta
    ) {
        // Intentionally empty:
        // keeps world rendering visible behind GUI
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean isInGameUi() {
        return true;
    }
}