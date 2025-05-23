package com.astrolabs.wastelandframework.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class HazardConfig {
    // Radiation
    public final BooleanValue enableRadiation;
    public final DoubleValue radiationDamagePerSecond;
    public final DoubleValue radiationZoneChance;
    public final IntValue radiationZoneRadius;
    public final BooleanValue radiationSpreadEnabled;
    
    // Acid Rain
    public final BooleanValue enableAcidRain;
    public final DoubleValue acidRainChance;
    public final DoubleValue acidRainDamagePerTick;
    public final IntValue acidRainDuration;
    public final BooleanValue acidRainCorrodesArmor;
    
    // Dust Storms
    public final BooleanValue enableDustStorms;
    public final DoubleValue dustStormChance;
    public final IntValue dustStormDuration;
    public final DoubleValue dustStormVisibilityReduction;
    public final DoubleValue dustStormMovementPenalty;
    
    // Ash Fall
    public final BooleanValue enableAshfall;
    public final DoubleValue ashfallChance;
    public final BooleanValue ashfallAccumulates;
    
    public HazardConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Environmental hazard settings").push("hazards");
        
        // Radiation settings
        builder.push("radiation");
        
        enableRadiation = builder
            .comment("Enable radiation zones and mechanics")
            .define("enableRadiation", true);
            
        radiationDamagePerSecond = builder
            .comment("Damage dealt per second in radiation zones")
            .defineInRange("radiationDamagePerSecond", 1.0, 0.1, 20.0);
            
        radiationZoneChance = builder
            .comment("Chance for radiation zones to spawn (0.0-1.0 per chunk)")
            .defineInRange("radiationZoneChance", 0.05, 0.0, 1.0);
            
        radiationZoneRadius = builder
            .comment("Default radius of radiation zones in blocks")
            .defineInRange("radiationZoneRadius", 32, 8, 128);
            
        radiationSpreadEnabled = builder
            .comment("Allow radiation zones to slowly spread")
            .define("radiationSpreadEnabled", false);
            
        builder.pop();
        
        // Acid rain settings
        builder.push("acidRain");
        
        enableAcidRain = builder
            .comment("Enable acid rain weather events")
            .define("enableAcidRain", true);
            
        acidRainChance = builder
            .comment("Daily chance for acid rain (0.0-1.0)")
            .defineInRange("acidRainChance", 0.3, 0.0, 1.0);
            
        acidRainDamagePerTick = builder
            .comment("Damage dealt per tick when exposed to acid rain")
            .defineInRange("acidRainDamagePerTick", 0.5, 0.1, 10.0);
            
        acidRainDuration = builder
            .comment("Average duration of acid rain in ticks")
            .defineInRange("acidRainDuration", 6000, 1200, 24000);
            
        acidRainCorrodesArmor = builder
            .comment("Acid rain damages armor durability")
            .define("acidRainCorrodesArmor", true);
            
        builder.pop();
        
        // Dust storm settings
        builder.push("dustStorms");
        
        enableDustStorms = builder
            .comment("Enable dust storm weather events")
            .define("enableDustStorms", true);
            
        dustStormChance = builder
            .comment("Daily chance for dust storms (0.0-1.0)")
            .defineInRange("dustStormChance", 0.2, 0.0, 1.0);
            
        dustStormDuration = builder
            .comment("Average duration of dust storms in ticks")
            .defineInRange("dustStormDuration", 4800, 1200, 12000);
            
        dustStormVisibilityReduction = builder
            .comment("Visibility reduction factor during dust storms (0.0-1.0)")
            .defineInRange("dustStormVisibilityReduction", 0.7, 0.0, 1.0);
            
        dustStormMovementPenalty = builder
            .comment("Movement speed penalty during dust storms (0.0-1.0)")
            .defineInRange("dustStormMovementPenalty", 0.3, 0.0, 0.9);
            
        builder.pop();
        
        // Ashfall settings
        builder.push("ashfall");
        
        enableAshfall = builder
            .comment("Enable ashfall weather events")
            .define("enableAshfall", true);
            
        ashfallChance = builder
            .comment("Daily chance for ashfall (0.0-1.0)")
            .defineInRange("ashfallChance", 0.4, 0.0, 1.0);
            
        ashfallAccumulates = builder
            .comment("Ash accumulates on the ground like snow")
            .define("ashfallAccumulates", true);
            
        builder.pop();
        builder.pop();
    }
}