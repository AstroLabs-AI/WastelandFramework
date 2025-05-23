package com.astrolabs.wastelandframework.core;

import net.minecraftforge.eventbus.api.IEventBus;

public interface IModule {
    String getId();
    String getName();
    ModuleCategory getCategory();
    boolean isEnabled();
    
    void onRegister(IEventBus modBus, IEventBus forgeBus);
    void onInitialize();
    void onClientInitialize();
    void onConfigReload();
    
    enum ModuleCategory {
        WORLDGEN("World Generation"),
        ENVIRONMENT("Environment & Hazards"),
        GAMEPLAY("Gameplay Mechanics"),
        ENTITY("Entities & NPCs"),
        CONTENT("Items & Blocks"),
        EVENT("Events & Anomalies");
        
        private final String displayName;
        
        ModuleCategory(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
}