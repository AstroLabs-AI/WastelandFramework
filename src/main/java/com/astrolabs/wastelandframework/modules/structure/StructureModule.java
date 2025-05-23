package com.astrolabs.wastelandframework.modules.structure;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraftforge.eventbus.api.IEventBus;

public class StructureModule implements IModule {
    // TODO: Structure implementation will be added in a future update
    // For now, the module provides the framework and commands
    
    @Override
    public String getId() {
        return "structure";
    }
    
    @Override
    public String getName() {
        return "Structure Generation";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.WORLDGEN;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_STRUCTURE_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Structure Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Structure Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // No client-specific initialization needed
    }
    
    @Override
    public void onConfigReload() {
        // TODO: Reload structure configuration
    }
}