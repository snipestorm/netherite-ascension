package net.adam.netheriteascension.block.entity;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.block.entity.custom.ObsidianAltarBlockEntity;
import net.adam.netheriteascension.block.entity.custom.RitualAltarBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<RitualAltarBlockEntity> RITUAL_ALTAR_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID,"ritual_altar_be"),
                    FabricBlockEntityTypeBuilder.create(RitualAltarBlockEntity::new, ModBlocks.RITUAL_ALTAR).build());

    public static final BlockEntityType<ObsidianAltarBlockEntity> OBSIDIAN_ALTAR_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID,"obsidian_altar_be"),
                    FabricBlockEntityTypeBuilder.create(ObsidianAltarBlockEntity::new, ModBlocks.OBSIDIAN_ALTAR).build());


    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Block Entities!");
    }
}