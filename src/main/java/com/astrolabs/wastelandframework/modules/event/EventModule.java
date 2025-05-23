package com.astrolabs.wastelandframework.modules.event;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraftforge.eventbus.api.IEventBus;

public class EventModule implements IModule {
    @Override
    public String getId() {
        return "event";
    }
    
    @Override
    public String getName() {
        return "Random Events";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.EVENT;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_EVENT_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Event Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Event Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // Register event UI notifications
    }
    
    @Override
    public void onConfigReload() {
        // Reload event settings
    }
}