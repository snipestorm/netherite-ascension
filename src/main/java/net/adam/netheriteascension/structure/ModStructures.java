package net.adam.netheriteascension.structure;

import net.adam.netheriteascension.NetheriteAscension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModStructures {

    public static final ResourceKey<Structure> ALTAR_TOWER = key("altar_tower");

    private static ResourceKey<Structure> key(String path) {
        return ResourceKey.create(
                Registries.STRUCTURE,
                Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, path)
        );
    }

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Structures!");
    }
}