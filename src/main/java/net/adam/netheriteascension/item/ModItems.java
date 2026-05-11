package net.adam.netheriteascension.item;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.item.custom.AltarStarItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

import java.util.function.Function;

public class ModItems {

    public static final Item DIVINE_NETHERITE_INGOT = registerItem("divine_netherite_ingot", properties -> new Item(properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_UPGRADE_SMITHING_TEMPLATE = registerItem("divine_netherite_upgrade_smithing_template", properties -> new Item(properties.rarity(Rarity.EPIC)));
    public static final Item ALTAR_STAR = registerItem("altar_star", properties -> new AltarStarItem(properties.rarity(Rarity.RARE)));
    public static final Item ICON_ITEM = registerItem("icon_item", properties -> new Item(properties.rarity(Rarity.UNCOMMON)));
    public static final Item ALTAR_TOWER_ITEM = registerItem("altar_tower_item", properties -> new Item(properties.rarity(Rarity.UNCOMMON)));

    public static final Item DIVINE_NETHERITE_SWORD = registerItem("divine_netherite_sword", properties -> new Item(properties.sword(ModToolMaterials.DIVINE_NETHERITE,3,-2.4f).rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_PICKAXE = registerItem("divine_netherite_pickaxe", properties -> new Item(properties.pickaxe(ModToolMaterials.DIVINE_NETHERITE,3,-2.8f).rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_SHOVEL = registerItem("divine_netherite_shovel", properties -> new ShovelItem(ModToolMaterials.DIVINE_NETHERITE,1.5f,-3.0f,properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_AXE = registerItem("divine_netherite_axe", properties -> new AxeItem(ModToolMaterials.DIVINE_NETHERITE,6,-3.2f,properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_HOE = registerItem("divine_netherite_hoe", properties -> new HoeItem(ModToolMaterials.DIVINE_NETHERITE,0,-3f,properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_SPEAR = registerItem("divine_netherite_spear", properties -> new Item(properties.spear(ModToolMaterials.DIVINE_NETHERITE,1.25F,1.375F,0.3F,2.0f,8.0F,4.5F,5.1F,7F,4.6F).fireResistant().rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name)))));
    }

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Items!");
    }
}
