package net.adam.netheriteascension.item;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.item.custom.AltarStarItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    public static final Item DIVINE_NETHERITE_INGOT = registerItem("divine_netherite_ingot", Item::new);
    public static final Item ALTAR_STAR = registerItem("altar_star", AltarStarItem::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name)))));
    }

    public static void registerModItems() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Items!");
    }
}
