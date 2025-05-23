package com.astrolabs.wastelandframework.common.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class WastelandDamageSources {
    public static final ResourceKey<DamageType> RADIATION = ResourceKey.create(Registries.DAMAGE_TYPE,
        new ResourceLocation("wastelandframework", "radiation"));
    
    public static final ResourceKey<DamageType> ACID_RAIN = ResourceKey.create(Registries.DAMAGE_TYPE,
        new ResourceLocation("wastelandframework", "acid_rain"));
    
    public static DamageSource radiation(Level level) {
        return new DamageSource(level.registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolderOrThrow(RADIATION));
    }
    
    public static DamageSource acidRain(Level level) {
        return new DamageSource(level.registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolderOrThrow(ACID_RAIN));
    }
    
    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(RADIATION, new DamageType("radiation", 0.1F));
        context.register(ACID_RAIN, new DamageType("acid_rain", 0.1F));
    }
}