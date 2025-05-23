package com.astrolabs.wastelandframework.modules.survival;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import com.astrolabs.wastelandframework.core.registry.WastelandRegistries;
import com.astrolabs.wastelandframework.common.items.ContaminatedWaterBottle;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class SurvivalModule implements IModule {
    // Survival items
    public static final RegistryObject<Item> CONTAMINATED_WATER = WastelandRegistries.ITEMS.register("contaminated_water",
        () -> new ContaminatedWaterBottle());
    
    @Override
    public String getId() {
        return "survival";
    }
    
    @Override
    public String getName() {
        return "Survival Mechanics";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.GAMEPLAY;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_SURVIVAL_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Survival Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Survival Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // No client-specific initialization needed
    }
    
    @Override
    public void onConfigReload() {
        // Reload survival settings
    }
}