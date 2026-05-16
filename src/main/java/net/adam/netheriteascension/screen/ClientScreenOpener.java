package net.adam.netheriteascension.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

public class ClientScreenOpener {

    public static void openRecipeScreen(Identifier texture) {

        Minecraft.getInstance().setScreen(
                new CustomImageScreen(
                        new ScreenData(texture, 192, 192)
                )
        );
    }
}