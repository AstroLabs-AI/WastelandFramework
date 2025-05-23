package com.astrolabs.wastelandframework.modules.hazard;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import com.astrolabs.wastelandframework.core.registry.WastelandRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

public class HazardModule implements IModule {
    public static RegistryObject<MobEffect> RADIATION = WastelandRegistries.MOB_EFFECTS.register("radiation",
        () -> new RadiationEffect(MobEffectCategory.HARMFUL, 0x7FFF00));
    
    @Override
    public String getId() {
        return "hazard";
    }
    
    @Override
    public String getName() {
        return "Environmental Hazards";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.ENVIRONMENT;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_HAZARD_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        forgeBus.register(this);
        WastelandFramework.LOGGER.info("Hazard Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Hazard Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // Register client-side hazard renderers
    }
    
    @Override
    public void onConfigReload() {
        // Reload hazard configurations
    }
    
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (!event.getEntity().level().isClientSide) {
            checkRadiationExposure(event.getEntity());
        }
    }
    
    private void checkRadiationExposure(LivingEntity entity) {
        // TODO: Check if entity is in radiation zone and apply effects
    }
}