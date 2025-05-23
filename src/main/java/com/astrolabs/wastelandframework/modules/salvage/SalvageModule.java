package com.astrolabs.wastelandframework.modules.salvage;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import com.astrolabs.wastelandframework.core.registry.WastelandRegistries;
import com.astrolabs.wastelandframework.common.items.ScrapItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class SalvageModule implements IModule {
    // Scrap items
    public static final RegistryObject<Item> METAL_SCRAP = WastelandRegistries.ITEMS.register("metal_scrap",
        () -> new ScrapItem(ScrapItem.ScrapType.METAL));
    
    public static final RegistryObject<Item> ELECTRONIC_SCRAP = WastelandRegistries.ITEMS.register("electronic_scrap",
        () -> new ScrapItem(ScrapItem.ScrapType.ELECTRONIC));
    
    public static final RegistryObject<Item> CLOTH_SCRAP = WastelandRegistries.ITEMS.register("cloth_scrap",
        () -> new ScrapItem(ScrapItem.ScrapType.CLOTH));
    
    public static final RegistryObject<Item> PLASTIC_SCRAP = WastelandRegistries.ITEMS.register("plastic_scrap",
        () -> new ScrapItem(ScrapItem.ScrapType.PLASTIC));
    
    public static final RegistryObject<Item> MECHANICAL_PARTS = WastelandRegistries.ITEMS.register("mechanical_parts",
        () -> new ScrapItem(ScrapItem.ScrapType.MECHANICAL));
    
    @Override
    public String getId() {
        return "salvage";
    }
    
    @Override
    public String getName() {
        return "Salvage & Crafting";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.CONTENT;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_SALVAGE_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        WastelandFramework.LOGGER.info("Salvage Module registered");
    }
    
    @Override
    public void onInitialize() {
        WastelandFramework.LOGGER.info("Salvage Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // Register salvage UI elements
    }
    
    @Override
    public void onConfigReload() {
        // Reload salvage settings
    }
}