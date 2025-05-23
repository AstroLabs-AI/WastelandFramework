package com.astrolabs.wastelandframework.modules.atmosphere;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraftforge.eventbus.api.IEventBus;

public class AtmosphereModule implements IModule {
    @Override
    public String getId() {
        return "atmosphere";
    }
    
    @Override
    public String getName() {
        return "Atmosphere & Ambience";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.ENVIRONMENT;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_ATMOSPHERE_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Atmosphere Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Atmosphere Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // Register fog renderers, sky colors, ambient sounds
    }
    
    @Override
    public void onConfigReload() {
        // Reload atmosphere settings
    }
}