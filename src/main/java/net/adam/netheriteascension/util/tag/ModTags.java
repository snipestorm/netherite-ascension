package net.adam.netheriteascension.util.tag;

import net.adam.netheriteascension.NetheriteAscension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> NEEDS_DIVINE_NETHERITE_TOOL = createTag("needs_divine_netherite_tool");
        public static final TagKey<Block> INCORRECT_FOR_DIVINE_NETHERITE_TOOL = createTag("incorrect_for_divine_netherite_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name));
        }
    }
    public static class Items {

        public static final TagKey<Item> DIVINE_NETHERITE_REPAIRABLE = createTag("divine_netherite_repairable");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name));
        }

    }


}
