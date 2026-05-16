package net.adam.netheriteascension.block.entity.custom;

import net.adam.netheriteascension.advancement.ModAdvancements;
import net.adam.netheriteascension.block.ModBlocks;
import net.adam.netheriteascension.block.entity.ModBlockEntities;
import net.adam.netheriteascension.recipe.ModRecipes;
import net.adam.netheriteascension.recipe.custom.AltarRecipe;
import net.adam.netheriteascension.recipe.custom.AltarRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3i;

import java.util.Optional;

public class RitualAltarBlockEntity extends AltarBlockEntity {




    public RitualAltarBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.RITUAL_ALTAR_BE, worldPosition, blockState);
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {

        RandomSource random = RandomSource.create();
        ItemStack currentStack = this.inventory.get(0);
        Player player = getLastPlayer();

        // Detect crafted item removal
        if (hadCraftedItemLastTick && currentStack.isEmpty() && craftingComplete) {

            System.out.println("Crafted item removed!");

            setCount(0);
            ritualActive = true;
            craftingComplete = false;

        }

        if (ritualActive) {

            ritualCountUp();
            spawnRitualParticles(level, blockPos, random);

            if (ritualCountFinished()) {

                spawnExplosionParticles((ServerLevel) level);
                spawnVisualLightning(level, blockPos);

                setDivineAltar(blockPos, (ServerLevel) level);

                setRitualCount(0);
                ritualActive = false;
            }
        }


        // Update tracking AFTER checks
        hadCraftedItemLastTick = !currentStack.isEmpty();

        // Recipe crafting logic
        if (!hasRecipe())
            return;

        if(beginCraftingAudio()) {

        }


        if (countFinished()) {

            setCount(0);
            exchangeItemInRitualAltar();
            removeItemsFromObsidianAltars();

            spawnVisualLightning(level, blockPos);
            spawnExplosionParticles((ServerLevel) level);
            playCraftSound(level, blockPos, random);
           System.out.println(this.inventory.get(0).getItem().getName(this.inventory.get(0)).getString() + " Crafted! by " + getLastPlayer().getName().getString() );

            craftingComplete = true;
            craftingActive = false;
            if (getLastPlayer() != null) {
                ModAdvancements.altarCrafting((ServerPlayer) player);
            }


        } else {
            craftingActive = true;
            countUp();
            spawnCraftingParticles(level);

        }
    }

    public void setDivineAltar(BlockPos pos, ServerLevel level) {
        if (level.isClientSide()) return;
        level.setBlock(pos, ModBlocks.DIVINE_ALTAR.defaultBlockState(), 3);
        for (Vector3i offset : offsets) {
            BlockPos targetPos = pos.offset(offset.x, offset.y, offset.z);
            level.setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
        }
    }


    public void playCraftSound(Level level, BlockPos pos, RandomSource random) {
        level.playSound(null,pos,SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS,1.5f,
                0.7f + random.nextFloat() * 0.4f);
    }

    public void playCraftingSound(Level level, BlockPos pos, RandomSource random) {
        level.playSound(null,pos,SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS,1.5f,
                0.7f + random.nextFloat() * 0.4f);
    }

    public boolean hasRecipe() {
            Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();
            return recipe.isPresent();
    }

    private Optional<RecipeHolder<AltarRecipe>> getCurrentRecipe() {
        return ((ServerLevel) this.level).recipeAccess()
                .getRecipeFor(ModRecipes.ALTAR_TYPE, new AltarRecipeInput(
                        this.inventory.get(0),
                        offsets.stream().map(offset -> {
                            if (hasObsidianAltars())
                            {return ((ObsidianAltarBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, offset.y, offset.z)))
                                    .inventory.get(0);
                            } else {
                                return ItemStack.EMPTY;}}).toList()), level);
    }

    private boolean hasObsidianAltars() {
        return offsets.stream().allMatch(this::isObsidianAltar);
    }

    private boolean isObsidianAltar(Vector3i offset) {
        return level.getBlockState(this.getBlockPos().offset(offset.x, offset.y, offset.z)).is(ModBlocks.OBSIDIAN_ALTAR);
    }

    private void removeItemsFromObsidianAltars() {
        offsets.forEach(offset -> ((ObsidianAltarBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x,offset.y,offset.z))).clearContent());
    }

    private void exchangeItemInRitualAltar() {
        Optional<RecipeHolder<AltarRecipe>> recipe = getCurrentRecipe();

        recipe.ifPresent(altarRecipeHolder ->
                this.setTheItem(altarRecipeHolder.value().assemble(null)));
    }

    private void spawnVisualLightning(Level level, BlockPos pos) {
        EntityType.LIGHTNING_BOLT.spawn((ServerLevel) level, pos, EntitySpawnReason.TRIGGERED).setVisualOnly(true);
    }

    private void spawnExplosionParticles(ServerLevel level) {
        double x = this.getBlockPos().getX();
        double y = this.getBlockPos().getY();
        double z = this.getBlockPos().getZ();
        level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x + 0.5f, y + 1.2f, z + 0.5f, 0, 0, 0, 0, 0.25f);
    }


    private void spawnCraftingParticles(Level level) {
        offsets.forEach(offset -> {
            ItemStack stack = ((ObsidianAltarBlockEntity) level.getBlockEntity(this.getBlockPos().offset(offset.x, offset.y,offset.z))).inventory.get(0);
            double x = this.getBlockPos().offset(offset.x, offset.y, offset.z).getX();
            double y = this.getBlockPos().offset(offset.x, offset.y, offset.z).getY();
            double z = this.getBlockPos().offset(offset.x, offset.y, offset.z).getZ();

            BlockPos direction = getBlockPos().subtract(this.getBlockPos().offset(offset.x, offset.y, offset.z));

            ((ServerLevel) level).sendParticles(new ItemParticleOption
                    (ParticleTypes.ITEM, stack.getItem()), x + 0.5f, y + 1.4f, z + 0.5f, 0,direction.getX(), direction.getY(),direction.getZ(), 0.25f);
            });
        }
        
        private void spawnRitualParticles(Level level, BlockPos pos, RandomSource random) {
            double rotation = ritualCount * 0.2;
            int ringPoints = 6;
            double maxRadius = 0.6;
            double radiusScale =
                    ritualCount < 80
                            ? maxRadius
                            : maxRadius * (1.0 - ((ritualCount - 80) / 20.0));
            for (Vector3i offset : offsets) {
                // include Y OFFSET NOW
                double baseX = pos.getX() + 0.5 + offset.x;
                double baseY = pos.getY() + offset.y; // 🔥 vertical pedestal offset
                double baseZ = pos.getZ() + 0.5 + offset.z;
                double ringHeight = baseY + 0.5 + (ritualCount / 100.0) * 0.5;
                for (int i = 0; i < ringPoints; i++) {
                    double angle = rotation + (i * (Math.PI * 2 / ringPoints));
                    double x = baseX + Math.cos(angle) * radiusScale;
                    double z = baseZ + Math.sin(angle) * radiusScale;
                    ((ServerLevel) level).sendParticles(
                            ParticleTypes.SOUL_FIRE_FLAME,
                            x,
                            ringHeight,
                            z,1,
                            0, 0, 0,0.01
                    );
                }
                if (ritualCount == 90) {
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
                }
                // collapse pull
                if (ritualCount >= 90) {
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
            double centerX = pos.getX() + 0.5;
            double centerZ = pos.getZ() + 0.5;
            int progress = ritualCount;
            double radius = 2.5;
            double maxHeight = 4.0;
            double currentHeight = (progress / 100.0) * maxHeight;
            double time = progress * 0.2;
            int points = 12;
            for (int i = 0; i < points; i++) {
                double t = time + (i * (Math.PI * 2 / points));
                double x1 = Math.cos(t) * radius;
                double z1 = Math.sin(t) * radius;
                double x2 = Math.cos(t + Math.PI) * radius;
                double z2 = Math.sin(t + Math.PI) * radius;
                double y = pos.getY() + (i / (double) points) * currentHeight;
                ((ServerLevel) level).sendParticles(
                        ParticleTypes.SOUL_FIRE_FLAME,
                        centerX + x1,
                        y,
                        centerZ + z1,1,
                        0, 0, 0,0.2
                );
                ((ServerLevel) level).sendParticles(
                        ParticleTypes.SOUL,
                        centerX + x2,
                        y,
                        centerZ + z2,1,
                        0, 0, 0,0.2
                );
            }
        }

    private void countUp() {
        count++;
    }
        public void setCount(int count) {
        this.count = count;
    }
    public boolean countFinished() {
        return count >= maxCount;
    }
    private void ritualCountUp() {
        ritualCount++;
    }
    public void setRitualCount(int count) {
        this.ritualCount = count;
    }
    private boolean ritualCountFinished() {
        return ritualCount >= ritualMaxCount;
    }
    public boolean beginCraftingAudio() {
        return count == 1;
    }




}

