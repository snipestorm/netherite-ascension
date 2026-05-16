package net.adam.netheriteascension.util.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.networking.BoostKeyHandler;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {

    public static final KeyMapping BOOST_KEY = KeyMappingHelper.registerKeyMapping(
            new KeyMapping("key.netherite-ascension.boost_key", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KeyMapping.Category.MISC));

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Keybinds!");
        BoostKeyHandler.load();
    }
}