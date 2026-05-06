package net.adam.netheriteascension.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelEventHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelEventHandler.class)
public class AltarStarEventMixin {

    @Inject(
            method = "levelEvent",
            at = @At("HEAD")
    )
    private void netheriteascension$altarStarEvent(int eventType, BlockPos pos, int data, CallbackInfo ci) {

        if (eventType != 9001) return;

        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;

        Vec3 center = Vec3.atCenterOf(pos);

        for (double angle = 0.0; angle < Math.PI * 2; angle += Math.PI / 20) {

            double dx = Math.cos(angle);
            double dz = Math.sin(angle);

            double px = center.x + dx * 5.0;
            double py = center.y - 0.4;
            double pz = center.z + dz * 5.0;

            // inner ring
            level.addParticle(
                    ParticleTypes.SOUL,
                    px, py, pz,
                    dx * -5.0,
                    0.0,
                    dz * -5.0
            );

            // outer ring
            level.addParticle(
                    ParticleTypes.SOUL,
                    px, py, pz,
                    dx * -7.0,
                    0.0,
                    dz * -7.0
            );
        }
    }
}