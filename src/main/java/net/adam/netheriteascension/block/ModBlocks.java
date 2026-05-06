package net.adam.netheriteascension.block;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.block.custom.DivineAltarBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Function;

public class ModBlocks {

    public static final Block DIVINE_ALTAR = registerBlock("divine_altar", properties -> new DivineAltarBlock(properties.strength(55f,12000f).sound(SoundType.DEEPSLATE).lightLevel(blockstate -> blockstate.getValue(BlockStateProperties.LIT) ? 15 : 5)));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID,name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID,name), toRegister);
    };

    public static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, name)))));
    }

    public static void load() {
        NetheriteAscension.LOGGER.info("Registering Netherite Ascension Blocks!");
    }
}
