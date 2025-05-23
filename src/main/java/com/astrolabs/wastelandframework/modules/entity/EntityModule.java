package com.astrolabs.wastelandframework.modules.entity;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraftforge.eventbus.api.IEventBus;

public class EntityModule implements IModule {
    @Override
    public String getId() {
        return "entity";
    }
    
    @Override
    public String getName() {
        return "Entities & NPCs";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.ENTITY;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_ENTITY_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Entity Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Entity Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // Register entity renderers
    }
    
    @Override
    public void onConfigReload() {
        // Reload entity settings
    }
}