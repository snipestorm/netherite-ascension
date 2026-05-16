package net.adam.netheriteascension.util.loot;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class ModLootTableModifiers {
    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder, LootTableSource lootTableSource, HolderLookup.Provider provider) {
        if (key.identifier().equals(Identifier.withDefaultNamespace("entities/warden"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(1F))
                    .add(LootItem.lootTableItem(ModItems.DIVINE_NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)));

            builder.pool(poolBuilder.build());
        }

        if(BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR_POT.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.1F))
                    .add(LootItem.lootTableItem(ModItems.OMINOUS_TRIAL_KEY_RECIPE_ITEM))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)));

            builder.pool(poolBuilder.build());
        }

        if(BuiltInLootTables.SIMPLE_DUNGEON.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.2F))
                    .add(LootItem.lootTableItem(ModItems.ENCHANTED_GOLDEN_APPLE_RECIPE_ITEM))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)));

            builder.pool(poolBuilder.build());
        }

        if (BuiltInLootTables.END_CITY_TREASURE.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.15F))
                    .add(LootItem.lootTableItem(ModItems.DIVINE_NETHERITE_ELYTRA_RECIPE_ITEM))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)));

            builder.pool(poolBuilder.build());
        }

        if (BuiltInLootTables.BASTION_TREASURE.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.25F))
                    .add(LootItem.lootTableItem(ModItems.DIVINE_NETHERITE_INGOT_RECIPE_ITEM))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)));

            builder.pool(poolBuilder.build());
        }

        if (BuiltInLootTables.WOODLAND_MANSION.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.3F))
                    .add(LootItem.lootTableItem(ModItems.TOTEM_OF_UNDYING_RECIPE_ITEM))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)));

            builder.pool(poolBuilder.build());
        }
    }

    public static void load() {
        LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Loot Table Modifiers!");
    }
}
