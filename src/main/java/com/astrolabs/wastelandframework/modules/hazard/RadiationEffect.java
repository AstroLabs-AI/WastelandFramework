package com.astrolabs.wastelandframework.modules.hazard;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RadiationEffect extends MobEffect {
    public RadiationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            // Apply radiation damage
            float damage = 1.0f * (amplifier + 1);
            entity.hurt(entity.damageSources().magic(), damage);
        }
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Apply effect every 20 ticks (1 second)
        return duration % 20 == 0;
    }
}