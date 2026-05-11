package net.adam.netheriteascension.datagen;

import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.item.ModArmorMaterials;
import net.adam.netheriteascension.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.DIVINE_NETHERITE_BLOCK);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ALTAR_STAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ICON_ITEM, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ALTAR_TOWER_ITEM, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_SPEAR, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.DIVINE_NETHERITE_NAUTILUS_ARMOR, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateTrimmableItem(ModItems.DIVINE_NETHERITE_HELMET, ModArmorMaterials.DIVINE_NETHERITE_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET,false);
        itemModelGenerators.generateTrimmableItem(ModItems.DIVINE_NETHERITE_CHESTPLATE, ModArmorMaterials.DIVINE_NETHERITE_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE,false);
        itemModelGenerators.generateTrimmableItem(ModItems.DIVINE_NETHERITE_LEGGINGS, ModArmorMaterials.DIVINE_NETHERITE_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS,false);
        itemModelGenerators.generateTrimmableItem(ModItems.DIVINE_NETHERITE_BOOTS, ModArmorMaterials.DIVINE_NETHERITE_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS,false);
    }
}
