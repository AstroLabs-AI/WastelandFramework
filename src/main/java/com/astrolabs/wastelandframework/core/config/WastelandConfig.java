package com.astrolabs.wastelandframework.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;

public class WastelandConfig {
    public static final ForgeConfigSpec SPEC;
    
    // General
    public static final BooleanValue DEBUG_MODE;
    public static final EnumValue<PerformanceMode> PERFORMANCE_MODE;
    
    // Modules
    public static final BooleanValue ENABLE_WORLD_MODULE;
    public static final BooleanValue ENABLE_STRUCTURE_MODULE;
    public static final BooleanValue ENABLE_HAZARD_MODULE;
    public static final BooleanValue ENABLE_ATMOSPHERE_MODULE;
    public static final BooleanValue ENABLE_SURVIVAL_MODULE;
    public static final BooleanValue ENABLE_ANOMALY_MODULE;
    public static final BooleanValue ENABLE_ENTITY_MODULE;
    public static final BooleanValue ENABLE_SALVAGE_MODULE;
    public static final BooleanValue ENABLE_LORE_MODULE;
    public static final BooleanValue ENABLE_EVENT_MODULE;
    
    // World Module
    public static final WorldConfig WORLD;
    
    // Structure Module
    public static final StructureConfig STRUCTURE;
    
    // Hazard Module
    public static final HazardConfig HAZARD;
    
    // Atmosphere Module
    public static final AtmosphereConfig ATMOSPHERE;
    
    // Survival Module
    public static final SurvivalConfig SURVIVAL;
    
    static {
        Builder builder = new Builder();
        
        builder.comment("Wasteland Framework Configuration").push("general");
        
        DEBUG_MODE = builder
            .comment("Enable debug mode for additional logging and debug commands")
            .define("debugMode", false);
            
        PERFORMANCE_MODE = builder
            .comment("Performance mode setting",
                     "PERFORMANCE: Reduced effects, larger update intervals",
                     "BALANCED: Default settings",
                     "QUALITY: Maximum effects, may impact performance")
            .defineEnum("performanceMode", PerformanceMode.BALANCED);
            
        builder.pop();
        
        builder.comment("Module enable/disable settings").push("modules");
        
        ENABLE_WORLD_MODULE = builder
            .comment("Enable world generation modifications (biome changes, surface replacements)")
            .define("enableWorldModule", true);
            
        ENABLE_STRUCTURE_MODULE = builder
            .comment("Enable custom structure generation (ruins, wrecks, bunkers)")
            .define("enableStructureModule", true);
            
        ENABLE_HAZARD_MODULE = builder
            .comment("Enable environmental hazards (radiation, acid rain)")
            .define("enableHazardModule", true);
            
        ENABLE_ATMOSPHERE_MODULE = builder
            .comment("Enable atmospheric effects (fog, sky color, sounds)")
            .define("enableAtmosphereModule", true);
            
        ENABLE_SURVIVAL_MODULE = builder
            .comment("Enable survival mechanics (scarcity, contamination)")
            .define("enableSurvivalModule", true);
            
        ENABLE_ANOMALY_MODULE = builder
            .comment("Enable anomalies and dynamic world changes")
            .define("enableAnomalyModule", true);
            
        ENABLE_ENTITY_MODULE = builder
            .comment("Enable custom entities and NPCs")
            .define("enableEntityModule", true);
            
        ENABLE_SALVAGE_MODULE = builder
            .comment("Enable salvage and crafting mechanics")
            .define("enableSalvageModule", true);
            
        ENABLE_LORE_MODULE = builder
            .comment("Enable lore and interactive elements")
            .define("enableLoreModule", true);
            
        ENABLE_EVENT_MODULE = builder
            .comment("Enable random events and supply drops")
            .define("enableEventModule", true);
            
        builder.pop();
        
        // Module configs
        WORLD = new WorldConfig(builder);
        STRUCTURE = new StructureConfig(builder);
        HAZARD = new HazardConfig(builder);
        ATMOSPHERE = new AtmosphereConfig(builder);
        SURVIVAL = new SurvivalConfig(builder);
        
        SPEC = builder.build();
    }
    
    public enum PerformanceMode {
        PERFORMANCE,
        BALANCED,
        QUALITY
    }
}