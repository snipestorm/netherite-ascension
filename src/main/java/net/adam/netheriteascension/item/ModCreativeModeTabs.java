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
                        output.accept(ModBlocks.DIVINE_NETHERITE_BLOCK);
                        output.accept(ModItems.DIVINE_NETHERITE_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(ModItems.DIVINE_NETHERITE_AXE);
                        output.accept(ModItems.DIVINE_NETHERITE_PICKAXE);
                        output.accept(ModItems.DIVINE_NETHERITE_HOE);
                        output.accept(ModItems.DIVINE_NETHERITE_SHOVEL);
                        output.accept(ModItems.DIVINE_NETHERITE_SWORD);
                        output.accept(ModItems.DIVINE_NETHERITE_SPEAR);
                        output.accept(ModItems.DIVINE_NETHERITE_HELMET);
                        output.accept(ModItems.DIVINE_NETHERITE_CHESTPLATE);
                        output.accept(ModItems.DIVINE_NETHERITE_LEGGINGS);
                        output.accept(ModItems.DIVINE_NETHERITE_BOOTS);
                        output.accept(ModItems.DIVINE_NETHERITE_ELYTRA);
                        output.accept(ModItems.DIVINE_NETHERITE_NAUTILUS_ARMOR);
                        output.accept(ModItems.DIVINE_NETHERITE_INGOT_RECIPE_ITEM);
                        output.accept(ModItems.DIVINE_NETHERITE_ELYTRA_RECIPE_ITEM);
                        output.accept(ModItems.ENCHANTED_GOLDEN_APPLE_RECIPE_ITEM);
                        output.accept(ModItems.TOTEM_OF_UNDYING_RECIPE_ITEM);
                        output.accept(ModItems.OMINOUS_TRIAL_KEY_RECIPE_ITEM);
                        output.accept(ModBlocks.DIVINE_ALTAR);
                        output.accept(ModBlocks.OBSIDIAN_ALTAR);
                        output.accept(ModBlocks.RITUAL_ALTAR);
                        output.accept(ModItems.ALTAR_STAR);
                    }).build());


    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Creative Mode Tabs!");
    }
}
