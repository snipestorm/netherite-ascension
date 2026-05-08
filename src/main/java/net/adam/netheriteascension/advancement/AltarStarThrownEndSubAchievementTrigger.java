package net.adam.netheriteascension.advancement;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.adam.netheriteascension.NetheriteAscension;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class AltarStarThrownEndSubAchievementTrigger extends SimpleCriterionTrigger<AltarStarThrownEndSubAchievementTrigger.Instance> {

    public static final Identifier ID =
            Identifier.fromNamespaceAndPath(NetheriteAscension.MOD_ID, "altar_star_end_trigger");

    public static final AltarStarThrownEndSubAchievementTrigger INSTANCE = new AltarStarThrownEndSubAchievementTrigger();

    // ✅ REQUIRED in modern versions
    public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ContextAwarePredicate.CODEC.optionalFieldOf("player")
                            .forGetter(i -> i.player)
            ).apply(instance, Instance::new)
    );

    @Override
    public Codec<Instance> codec() {
        return CODEC;
    }

    // 🔥 call this from your item
    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    // ─────────────────────────────
    // Criterion Instance
    // ─────────────────────────────
    public static class Instance implements SimpleInstance {

        private final Optional<ContextAwarePredicate> player;

        public Instance(Optional<ContextAwarePredicate> player) {
            this.player = player;
        }

        @Override
        public Optional<ContextAwarePredicate> player() {
            return player;
        }
    }
}