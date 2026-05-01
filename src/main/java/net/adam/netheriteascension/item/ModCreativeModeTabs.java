package net.adam.netheriteascension.item;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.block.ModBlocks;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab NETHERITE_ASCENSION = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "netherite_ascension"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIVINE_NETHERITE_INGOT))
                    .title(Component.translatable("creativetab.netherite-ascension.netherite_ascension"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.DIVINE_NETHERITE_INGOT);
                        output.accept(ModItems.ALTAR_STAR);
                        output.accept(ModBlocks.CHISELED_STONE_ALTAR);
                    }).build());


    public static void registerCreativeModeTabs() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Creative Mode Tabs!");
    }
}
