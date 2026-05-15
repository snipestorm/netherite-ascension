package net.adam.netheriteascension.datagen;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.item.ModArmorMaterials;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK,"equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(ModArmorMaterials.DIVINE_NETHERITE_KEY,
                EquipmentClientInfo.builder()
                        .addHumanoidLayers(Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "divine_netherite"))
                        .addLayers(EquipmentClientInfo.LayerType.NAUTILUS_BODY,new EquipmentClientInfo.Layer(Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "divine_netherite")))
                      // .addLayers(EquipmentClientInfo.LayerType.WINGS, new EquipmentClientInfo.Layer(Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "divine_netherite_wings")))
                        .build());
    }

    @Override
    public CompletableFuture<?> run(final CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    @Override
    public String getName() {
        return "Netherite Equipment Asset Definitions";
    }
}
