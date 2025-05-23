package com.astrolabs.wastelandframework.common.blocks;

import com.astrolabs.wastelandframework.modules.hazard.HazardModule;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ContaminatedSoilBlock extends Block {
    public ContaminatedSoilBlock() {
        super(Properties.of()
            .strength(0.6F)
            .sound(SoundType.GRAVEL)
            .randomTicks()
        );
    }
    
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            // Apply radiation effect when walking on contaminated soil
            if (!livingEntity.hasEffect(HazardModule.RADIATION.get())) {
                livingEntity.addEffect(new MobEffectInstance(HazardModule.RADIATION.get(), 100, 0));
            }
        }
    }
    
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Contamination can spread to nearby blocks
        if (random.nextFloat() < 0.01f) { // 1% chance per random tick
            BlockPos targetPos = pos.offset(
                random.nextInt(3) - 1,
                random.nextInt(3) - 1,
                random.nextInt(3) - 1
            );
            
            BlockState targetState = level.getBlockState(targetPos);
            // Could implement spreading logic here
        }
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Slightly lower than a full block to show it's degraded
        return Shapes.box(0.0, 0.0, 0.0, 1.0, 0.875, 1.0);
    }
}