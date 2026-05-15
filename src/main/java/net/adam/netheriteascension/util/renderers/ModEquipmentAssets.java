package net.adam.netheriteascension.util.renderers;

import net.adam.netheriteascension.NetheriteAscension;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class ModEquipmentAssets {



  public static final  ResourceKey<EquipmentAsset> DIVINE_ELYTRA =
            ResourceKey.create(
                    EquipmentAssets.ROOT_ID,
                    Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "divine_netherite_wings")
            );

}
