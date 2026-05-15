package net.adam.netheriteascension.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class DivineNetheriteElytra extends Item {
    public DivineNetheriteElytra(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);

        if (Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.netherite-ascension.divine_elytra_shift"));
        } else {
            builder.accept(Component.translatable("tooltip.netherite-ascension.divine_elytra"));
        }
    }
}
