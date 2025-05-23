package com.astrolabs.wastelandframework.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class SurvivalConfig {
    public final DoubleValue globalResourceMultiplier;
    public final DoubleValue oreSpawnMultiplier;
    public final DoubleValue foodDropMultiplier;
    public final DoubleValue mobSpawnMultiplier;
    
    public final BooleanValue enableWaterContamination;
    public final DoubleValue contaminatedWaterChance;
    public final BooleanValue waterPurificationRequired;
    
    public final BooleanValue enableFoodSpoilage;
    public final IntValue foodSpoilageTime;
    public final BooleanValue spoiledFoodPoisons;
    
    public final BooleanValue enableFatigue;
    public final DoubleValue fatigueRate;
    public final DoubleValue fatigueMovementPenalty;
    
    public final BooleanValue enableEncumbrance;
    public final DoubleValue encumbranceThreshold;
    public final DoubleValue encumbrancePenalty;
    
    public SurvivalConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Survival and scarcity settings").push("survival");
        
        // Resource scarcity
        builder.push("scarcity");
        
        globalResourceMultiplier = builder
            .comment("Global multiplier for all resource generation")
            .defineInRange("globalResourceMultiplier", 0.5, 0.01, 2.0);
            
        oreSpawnMultiplier = builder
            .comment("Multiplier for ore spawn rates")
            .defineInRange("oreSpawnMultiplier", 0.6, 0.01, 2.0);
            
        foodDropMultiplier = builder
            .comment("Multiplier for food drops from mobs and crops")
            .defineInRange("foodDropMultiplier", 0.4, 0.01, 2.0);
            
        mobSpawnMultiplier = builder
            .comment("Multiplier for passive mob spawning")
            .defineInRange("mobSpawnMultiplier", 0.3, 0.01, 2.0);
            
        builder.pop();
        
        // Water contamination
        builder.push("water");
        
        enableWaterContamination = builder
            .comment("Enable water contamination mechanics")
            .define("enableWaterContamination", true);
            
        contaminatedWaterChance = builder
            .comment("Chance for water sources to be contaminated")
            .defineInRange("contaminatedWaterChance", 0.7, 0.0, 1.0);
            
        waterPurificationRequired = builder
            .comment("Require water purification before drinking")
            .define("waterPurificationRequired", true);
            
        builder.pop();
        
        // Food spoilage
        builder.push("food");
        
        enableFoodSpoilage = builder
            .comment("Enable food spoilage over time")
            .define("enableFoodSpoilage", true);
            
        foodSpoilageTime = builder
            .comment("Time in ticks before food starts to spoil")
            .defineInRange("foodSpoilageTime", 24000, 1200, 168000);
            
        spoiledFoodPoisons = builder
            .comment("Eating spoiled food causes poison effect")
            .define("spoiledFoodPoisons", true);
            
        builder.pop();
        
        // Fatigue
        builder.push("fatigue");
        
        enableFatigue = builder
            .comment("Enable fatigue system")
            .define("enableFatigue", false);
            
        fatigueRate = builder
            .comment("Rate at which fatigue accumulates")
            .defineInRange("fatigueRate", 0.001, 0.0001, 0.1);
            
        fatigueMovementPenalty = builder
            .comment("Movement speed penalty when fatigued")
            .defineInRange("fatigueMovementPenalty", 0.3, 0.0, 0.9);
            
        builder.pop();
        
        // Encumbrance
        builder.push("encumbrance");
        
        enableEncumbrance = builder
            .comment("Enable inventory weight/encumbrance system")
            .define("enableEncumbrance", false);
            
        encumbranceThreshold = builder
            .comment("Percentage of inventory before encumbrance penalties")
            .defineInRange("encumbranceThreshold", 0.75, 0.1, 1.0);
            
        encumbrancePenalty = builder
            .comment("Movement penalty when encumbered")
            .defineInRange("encumbrancePenalty", 0.4, 0.0, 0.9);
            
        builder.pop();
        builder.pop();
    }
}