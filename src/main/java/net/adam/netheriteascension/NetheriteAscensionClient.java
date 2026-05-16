package net.adam.netheriteascension;

import net.adam.netheriteascension.util.NetheriteAscensionClientHelper;
import net.fabricmc.api.ClientModInitializer;

public class NetheriteAscensionClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        NetheriteAscension.LOGGER.info("Loading Netherite Ascension Client");
        NetheriteAscensionClientHelper.loadAllClientRegistries();
    }
}
