package com.astrolabs.wastelandframework.modules.lore;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraftforge.eventbus.api.IEventBus;

public class LoreModule implements IModule {
    @Override
    public String getId() {
        return "lore";
    }
    
    @Override
    public String getName() {
        return "Lore & Interactivity";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.CONTENT;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_LORE_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Lore Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Lore Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // Register lore UI elements
    }
    
    @Override
    public void onConfigReload() {
        // Reload lore settings
    }
}