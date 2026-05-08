package net.adam.netheriteascension.block.custom;

import net.adam.netheriteascension.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class DivineAltarBlock extends Block {

    public static final BooleanProperty lit = BlockStateProperties.LIT;
    public static final IntegerProperty PROGRESS = IntegerProperty.create("progress", 0, 100);
    public static final BooleanProperty CHARGED = BooleanProperty.create("charged");

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        tick(state, level, pos, random);
        if (!state.getValue(lit)) {
            level.scheduleTick(pos, this, 1);
        }
    }

    List<Vector3i> offsets = List.of(
            new Vector3i(4, 0, 0),
            new Vector3i(3, 0, 3),
            new Vector3i(0, 0, 4),
            new Vector3i(3, 0, -3),

            new Vector3i(-3, 0, 3),
            new Vector3i(-3, 0, -3),
            new Vector3i(0, 0, -4),
            new Vector3i(-4, 0, 0));


    public DivineAltarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(lit, false)
                        .setValue(PROGRESS, 0)
                        .setValue(CHARGED, false)
        );
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return this.defaultBlockState().setValue(lit, false);
    }
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide() && !state.getValue(lit)) {
            level.scheduleTick(pos, this, 20);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(lit, PROGRESS, CHARGED);

    }

    private void spawnVisualLightning(Level level, BlockPos pos) {
        EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, pos, EntitySpawnReason.TRIGGERED).setVisualOnly(true);
    }

    private void spawnExplosionParticles(ServerLevel level, BlockPos pos) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x + 0.5f, y + 1.2f, z + 0.5f, 0, 0, 0, 0, 0.25f);
    }

    // 🧱 structure
    public void setPedestals(BlockPos pos, BlockState blockState, ServerLevel level) {
        if (level.isClientSide()) return;
        boolean isLit = blockState.getValue(lit);
        if (!isLit) return;
        level.setBlock(pos, ModBlocks.RITUAL_ALTAR.defaultBlockState(), 3);
        for (Vector3i offset : offsets) {
            BlockPos targetPos = pos.offset(offset.x, offset.y, offset.z);
            level.setBlock(targetPos, ModBlocks.OBSIDIAN_ALTAR.defaultBlockState(), 3);
        }
    }
    // ⏱ progression tick
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        // =========================
        // ❌ NOT LIT → ambient lightning (rare)
        // =========================
        if (!state.getValue(lit)) {
            if (random.nextInt(10) == 0) {
                spawnVisualLightning(level, pos);
            }
            level.scheduleTick(pos, this, 20);
            return;
        }
        // =========================
        // 🧬 RITUAL PROGRESSION
        // =========================
        int progress = state.getValue(PROGRESS);
        // =========================
        // ⚡ 90% CHARGE MOMENT (ONCE)
        // =========================
        if (progress == 90 && !state.getValue(CHARGED)) {

            // 🔊 charge sound burst
            level.playSound(
                    null,
                    pos,
                    SoundEvents.RESPAWN_ANCHOR_CHARGE,
                    SoundSource.BLOCKS,
                    1.2f,
                    1.0f
            );
            level.playSound(
                    null,
                    pos,
                    SoundEvents.AMETHYST_BLOCK_CHIME,
                    SoundSource.BLOCKS,
                    1.5f,
                    0.7f + random.nextFloat() * 0.4f
            );
            // 🌪 particle surge
            level.sendParticles(
                    ParticleTypes.SOUL_FIRE_FLAME,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    80,
                    1.5, 1.0, 1.5,
                    0.05
            );
            level.sendParticles(
                    ParticleTypes.PORTAL,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5,
                    60,
                    1.2, 1.0, 1.2,
                    0.1
            );
            // 💾 mark as triggered (IMPORTANT)
            state = state.setValue(CHARGED, true);
            level.setBlock(pos, state, 3);
        }
        // =========================
        // 💥 FINAL STAGE (100)
        // =========================
        if (progress >= 100) {
            // 💥 FINAL BURST (ONLY ONCE)
            for (Vector3i offset : offsets) {
                BlockPos targetPos = pos.offset(offset.x, offset.y, offset.z);
                spawnExplosionParticles(level, targetPos);
                spawnVisualLightning(level, targetPos);
            }
            spawnExplosionParticles(level, pos);
            spawnVisualLightning(level, pos);
            setPedestals(pos, state, level);
            // ❗ STOP TICKING AFTER COMPLETION
            return;
        }
        // =========================
        // ⏱ CONTINUE RITUAL
        // =========================
        level.setBlock(pos, state.setValue(PROGRESS, progress + 1), 3);
        level.scheduleTick(pos, this, 1);
    }
    // ✨ particles
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double centerX = pos.getX() + 0.5;
        double centerZ = pos.getZ() + 0.5;
        // =========================
        // 🔽 UNLIT STATE
        // =========================
        if (!state.getValue(lit)) {
            for (int i = 0; i < 6; i++) {
                double x = centerX + (random.nextDouble() - 0.5) * 3.0;
                double z = centerZ + (random.nextDouble() - 0.5) * 3.0;
                double y = pos.getY() + 2.5 + random.nextDouble();
                double dx = (centerX - x) * 0.1;
                double dz = (centerZ - z) * 0.1;
                level.addParticle(
                        ParticleTypes.PORTAL,
                        x, y, z,
                        dx, -0.2, dz
                );
            }
            return;
        }
        // =========================
        // 🧬 LIT STATE
        // =========================
        int progress = state.getValue(PROGRESS);
        double radius = 2.5;
        double maxHeight = 4.0;
        double currentHeight = (progress / 100.0) * maxHeight;
        double time = progress * 0.2;
        int points = 12;
        // =========================
        // 🧬 DOUBLE HELIX
        // =========================
        for (int i = 0; i < points; i++) {
            double t = time + (i * (Math.PI * 2 / points));
            double x1 = Math.cos(t) * radius;
            double z1 = Math.sin(t) * radius;
            double x2 = Math.cos(t + Math.PI) * radius;
            double z2 = Math.sin(t + Math.PI) * radius;
            double y = pos.getY() + (i / (double) points) * currentHeight;
            level.addParticle(
                    ParticleTypes.SOUL_FIRE_FLAME,
                    centerX + x1,
                    y,
                    centerZ + z1,
                    0, 0.02, 0
            );
            level.addParticle(
                    ParticleTypes.SOUL,
                    centerX + x2,
                    y,
                    centerZ + z2,
                    0, 0.02, 0
            );
        }
        // =========================
        // 🔵 PEDESTAL RINGS (Vector3i)
        // =========================
        double rotation = progress * 0.2;
        int ringPoints = 6;
        double maxRadius = 0.6;
        double radiusScale =
                progress < 80
                        ? maxRadius
                        : maxRadius * (1.0 - ((progress - 80) / 20.0));
        for (Vector3i offset : offsets) {
            // include Y OFFSET NOW
            double baseX = pos.getX() + 0.5 + offset.x;
            double baseY = pos.getY() + offset.y; // 🔥 vertical pedestal offset
            double baseZ = pos.getZ() + 0.5 + offset.z;
            double ringHeight = baseY + 0.5 + (progress / 100.0) * 0.5;
            for (int i = 0; i < ringPoints; i++) {
                double angle = rotation + (i * (Math.PI * 2 / ringPoints));
                double x = baseX + Math.cos(angle) * radiusScale;
                double z = baseZ + Math.sin(angle) * radiusScale;
                level.addParticle(
                        ParticleTypes.SOUL_FIRE_FLAME,
                        x,
                        ringHeight,
                        z,
                        0, 0.01, 0
                );
            }
            // collapse pull
            if (progress >= 90) {
                double px = baseX + (random.nextDouble() - 0.5) * 0.3;
                double pz = baseZ + (random.nextDouble() - 0.5) * 0.3;
                double py = ringHeight;
                double dx = (baseX - px) * 0.2;
                double dz = (baseZ - pz) * 0.2;
                level.addParticle(
                        ParticleTypes.SOUL,
                        px, py, pz,
                        dx, -0.05, dz
                );
            }
        }
    }
}