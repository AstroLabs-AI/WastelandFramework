package com.astrolabs.wastelandframework.core;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.modules.world.WorldModule;
import com.astrolabs.wastelandframework.modules.structure.StructureModule;
import com.astrolabs.wastelandframework.modules.hazard.HazardModule;
import com.astrolabs.wastelandframework.modules.atmosphere.AtmosphereModule;
import com.astrolabs.wastelandframework.modules.survival.SurvivalModule;
import com.astrolabs.wastelandframework.modules.anomaly.AnomalyModule;
import com.astrolabs.wastelandframework.modules.entity.EntityModule;
import com.astrolabs.wastelandframework.modules.salvage.SalvageModule;
import com.astrolabs.wastelandframework.modules.lore.LoreModule;
import com.astrolabs.wastelandframework.modules.event.EventModule;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.*;

public class ModuleManager {
    private final Map<String, IModule> modules = new LinkedHashMap<>();
    private final IEventBus modBus;
    private final IEventBus forgeBus;
    
    public ModuleManager(IEventBus modBus, IEventBus forgeBus) {
        this.modBus = modBus;
        this.forgeBus = forgeBus;
        registerModules();
    }
    
    private void registerModules() {
        // Register all modules
        registerModule(new WorldModule());
        registerModule(new StructureModule());
        registerModule(new HazardModule());
        registerModule(new AtmosphereModule());
        registerModule(new SurvivalModule());
        registerModule(new AnomalyModule());
        registerModule(new EntityModule());
        registerModule(new SalvageModule());
        registerModule(new LoreModule());
        registerModule(new EventModule());
        
        WastelandFramework.LOGGER.info("Registered {} modules", modules.size());
    }
    
    private void registerModule(IModule module) {
        if (module.isEnabled()) {
            modules.put(module.getId(), module);
            module.onRegister(modBus, forgeBus);
            WastelandFramework.LOGGER.debug("Registered module: {} ({})", 
                module.getName(), module.getCategory().getDisplayName());
        } else {
            WastelandFramework.LOGGER.debug("Module disabled: {}", module.getName());
        }
    }
    
    public void initializeModules() {
        modules.values().forEach(module -> {
            try {
                module.onInitialize();
                WastelandFramework.LOGGER.debug("Initialized module: {}", module.getName());
            } catch (Exception e) {
                WastelandFramework.LOGGER.error("Failed to initialize module: {}", module.getName(), e);
            }
        });
    }
    
    public void initializeClientModules() {
        modules.values().forEach(module -> {
            try {
                module.onClientInitialize();
            } catch (Exception e) {
                WastelandFramework.LOGGER.error("Failed to initialize client module: {}", module.getName(), e);
            }
        });
    }
    
    public void reloadConfigs() {
        modules.values().forEach(module -> {
            try {
                module.onConfigReload();
                WastelandFramework.LOGGER.debug("Reloaded config for module: {}", module.getName());
            } catch (Exception e) {
                WastelandFramework.LOGGER.error("Failed to reload config for module: {}", module.getName(), e);
            }
        });
    }
    
    public Optional<IModule> getModule(String id) {
        return Optional.ofNullable(modules.get(id));
    }
    
    public Collection<IModule> getActiveModules() {
        return Collections.unmodifiableCollection(modules.values());
    }
    
    public Map<IModule.ModuleCategory, List<IModule>> getModulesByCategory() {
        Map<IModule.ModuleCategory, List<IModule>> categorized = new EnumMap<>(IModule.ModuleCategory.class);
        modules.values().forEach(module -> 
            categorized.computeIfAbsent(module.getCategory(), k -> new ArrayList<>()).add(module)
        );
        return categorized;
    }
}