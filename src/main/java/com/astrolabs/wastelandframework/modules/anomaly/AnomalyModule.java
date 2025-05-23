package com.astrolabs.wastelandframework.modules.anomaly;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraftforge.eventbus.api.IEventBus;

public class AnomalyModule implements IModule {
    @Override
    public String getId() {
        return "anomaly";
    }
    
    @Override
    public String getName() {
        return "Anomalies & Dynamic World";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.EVENT;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_ANOMALY_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Anomaly Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Anomaly Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // Register anomaly renderers
    }
    
    @Override
    public void onConfigReload() {
        // Reload anomaly settings
    }
}