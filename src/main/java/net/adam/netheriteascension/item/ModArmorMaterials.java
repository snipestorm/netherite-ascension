package net.adam.netheriteascension.item;

import com.google.common.collect.Maps;
import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.util.tag.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class ModArmorMaterials {

    static ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.parse("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> DIVINE_NETHERITE_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID,"divine_netherite"));

    public static final ArmorMaterial DIVINE_NETHERITE_ARMOR_MATERIAL = new ArmorMaterial(41,makeDefense(4,7,9,4,25),
            20, SoundEvents.ARMOR_EQUIP_NETHERITE,4.0F,0.15F, ModTags.Items.DIVINE_NETHERITE_REPAIRABLE,DIVINE_NETHERITE_KEY);

    static Map<ArmorType, Integer> makeDefense(final int boots, final int legs, final int chest, final int helm, final int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }

}
