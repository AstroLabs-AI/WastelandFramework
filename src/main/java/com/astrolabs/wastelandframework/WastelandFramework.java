package com.astrolabs.wastelandframework;

import com.astrolabs.wastelandframework.core.ModuleManager;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import com.astrolabs.wastelandframework.core.registry.WastelandRegistries;
import com.astrolabs.wastelandframework.common.commands.WastelandCommands;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WastelandFramework.MODID)
public class WastelandFramework {
    public static final String MODID = "wastelandframework";
    public static final Logger LOGGER = LogManager.getLogger();
    
    private static WastelandFramework instance;
    private final ModuleManager moduleManager;
    
    public WastelandFramework() {
        instance = this;
        
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        
        // Register config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WastelandConfig.SPEC);
        
        // Initialize registries
        WastelandRegistries.init(modEventBus);
        
        // Initialize module manager
        moduleManager = new ModuleManager(modEventBus, forgeEventBus);
        
        // Register setup events
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        
        // Register forge events
        forgeEventBus.register(this);
        
        LOGGER.info("Wasteland Framework initialized");
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            moduleManager.initializeModules();
            LOGGER.info("Wasteland Framework modules loaded");
        });
    }
    
    private void clientSetup(final FMLClientSetupEvent event) {
        if (FMLEnvironment.dist.isClient()) {
            moduleManager.initializeClientModules();
        }
    }
    
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        WastelandCommands.register(event.getDispatcher());
    }
    
    public static WastelandFramework getInstance() {
        return instance;
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
    
    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }
}