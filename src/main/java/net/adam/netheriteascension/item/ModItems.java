package net.adam.netheriteascension.item;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.datagen.ModEquipmentAssetProvider;
import net.adam.netheriteascension.item.custom.AltarRecipeItem;
import net.adam.netheriteascension.item.custom.AltarStarItem;
import net.adam.netheriteascension.item.custom.DivineNetheriteElytra;
import net.adam.netheriteascension.util.renderers.ModEquipmentAssets;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;

import java.util.function.Function;

public class ModItems {

    public static final Item DIVINE_NETHERITE_INGOT = registerItem("divine_netherite_ingot", properties -> new Item(properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_UPGRADE_SMITHING_TEMPLATE = registerItem("divine_netherite_upgrade_smithing_template", properties -> new Item(properties.rarity(Rarity.EPIC)));
    public static final Item ALTAR_STAR = registerItem("altar_star", properties -> new AltarStarItem(properties.rarity(Rarity.RARE)));
    public static final Item ICON_ITEM = registerItem("icon_item", properties -> new Item(properties.rarity(Rarity.UNCOMMON)));
    public static final Item ALTAR_TOWER_ITEM = registerItem("altar_tower_item", properties -> new Item(properties.rarity(Rarity.UNCOMMON)));

    public static final Item DIVINE_NETHERITE_INGOT_RECIPE_ITEM = registerItem("divine_netherite_ingot_recipe_item", properties -> new AltarRecipeItem(properties.stacksTo(1).rarity(Rarity.RARE), "divine_netherite_ingot"));
    public static final Item ENCHANTED_GOLDEN_APPLE_RECIPE_ITEM = registerItem("enchanted_golden_apple_recipe_item", properties -> new AltarRecipeItem(properties.stacksTo(1).rarity(Rarity.RARE), "enchanted_golden_apple"));
    public static final Item DIVINE_NETHERITE_ELYTRA_RECIPE_ITEM = registerItem("divine_netherite_elytra_recipe_item", properties -> new AltarRecipeItem(properties.stacksTo(1).rarity(Rarity.RARE), "divine_netherite_elytra"));
    public static final Item TOTEM_OF_UNDYING_RECIPE_ITEM = registerItem("totem_of_undying_recipe_item", properties -> new AltarRecipeItem(properties.stacksTo(1).rarity(Rarity.RARE), "totem_of_undying"));
    public static final Item OMINOUS_TRIAL_KEY_RECIPE_ITEM = registerItem("ominous_trial_key_recipe_item", properties -> new AltarRecipeItem(properties.stacksTo(1).rarity(Rarity.RARE), "ominous_trial_key"));

    public static final Item DIVINE_NETHERITE_SWORD = registerItem("divine_netherite_sword", properties -> new Item(properties.sword(ModToolMaterials.DIVINE_NETHERITE,3,-2.4f).rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_PICKAXE = registerItem("divine_netherite_pickaxe", properties -> new Item(properties.pickaxe(ModToolMaterials.DIVINE_NETHERITE,3,-2.8f).rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_SHOVEL = registerItem("divine_netherite_shovel", properties -> new ShovelItem(ModToolMaterials.DIVINE_NETHERITE,1.5f,-3.0f,properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_AXE = registerItem("divine_netherite_axe", properties -> new AxeItem(ModToolMaterials.DIVINE_NETHERITE,6,-3.2f,properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_HOE = registerItem("divine_netherite_hoe", properties -> new HoeItem(ModToolMaterials.DIVINE_NETHERITE,0,-3f,properties.rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_SPEAR = registerItem("divine_netherite_spear", properties -> new Item(properties.spear(ModToolMaterials.DIVINE_NETHERITE,1.25F,1.375F,0.3F,2.0f,8.0F,4.5F,5.1F,7F,4.6F).fireResistant().rarity(Rarity.EPIC)));

    public static final Item DIVINE_NETHERITE_HELMET =registerItem("divine_netherite_helmet", properties -> new Item(properties.humanoidArmor(ModArmorMaterials.DIVINE_NETHERITE_ARMOR_MATERIAL, ArmorType.HELMET).rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_CHESTPLATE =registerItem("divine_netherite_chestplate", properties -> new Item(properties.humanoidArmor(ModArmorMaterials.DIVINE_NETHERITE_ARMOR_MATERIAL, ArmorType.CHESTPLATE).rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_LEGGINGS =registerItem("divine_netherite_leggings", properties -> new Item(properties.humanoidArmor(ModArmorMaterials.DIVINE_NETHERITE_ARMOR_MATERIAL, ArmorType.LEGGINGS).rarity(Rarity.EPIC)));
    public static final Item DIVINE_NETHERITE_BOOTS =registerItem("divine_netherite_boots", properties -> new Item(properties.humanoidArmor(ModArmorMaterials.DIVINE_NETHERITE_ARMOR_MATERIAL, ArmorType.BOOTS).rarity(Rarity.EPIC)));

    public static final Item DIVINE_NETHERITE_ELYTRA =registerItem("divine_netherite_elytra", properties -> new DivineNetheriteElytra(properties.durability(999).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST)
            .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA).setAsset(ModEquipmentAssets.DIVINE_ELYTRA).build()).component(DataComponents.GLIDER, Unit.INSTANCE).rarity(Rarity.EPIC)));

    public static final Item DIVINE_NETHERITE_NAUTILUS_ARMOR = registerItem("divine_netherite_nautilus_armor", properties -> new Item(properties.nautilusArmor(ModArmorMaterials.DIVINE_NETHERITE_ARMOR_MATERIAL).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name)))));
    }

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Items!");
    }
}
