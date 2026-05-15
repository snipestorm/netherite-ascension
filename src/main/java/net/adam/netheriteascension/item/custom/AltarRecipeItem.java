package net.adam.netheriteascension.item.custom;

import net.adam.netheriteascension.NetheriteAscension;
import net.adam.netheriteascension.screen.CustomImageScreen;
import net.adam.netheriteascension.screen.ScreenData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class AltarRecipeItem extends Item {
    private final String screenType;

    public AltarRecipeItem(Properties properties, String screenType) {
        super(properties);
        this.screenType = screenType;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        builder.accept(Component.translatable("tooltip.netherite-ascension.recipe_item"));
    }


    @Override
    public @NotNull InteractionResult use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide()) {
            openScreen(stack);
        }

        return InteractionResult.SUCCESS;
    }

    private void openScreen(ItemStack stack) {
        Minecraft client = Minecraft.getInstance();

        client.setScreen(new CustomImageScreen(
                new ScreenData(
                        getTextureFor(stack),
                        192,
                        192
                )
        ));
    }

    private Identifier getTextureFor(ItemStack stack) {
        // Choose texture based on item type or registry name
        return switch (this.screenType) {
            case "divine_netherite_ingot" -> Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "textures/gui/divine_netherite_ingot.png");
            case "divine_netherite_elytra" -> Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "textures/gui/divine_netherite_elytra.png");
            case "enchanted_golden_apple" -> Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "textures/gui/enchanted_golden_apple.png");
            case "totem_of_undying" -> Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "textures/gui/totem_of_undying.png");
            case "ominous_trial_key" -> Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "textures/gui/ominous_trial_key.png");

            default -> throw new IllegalStateException("Unexpected value: " + this.screenType);
        };
    }
}
