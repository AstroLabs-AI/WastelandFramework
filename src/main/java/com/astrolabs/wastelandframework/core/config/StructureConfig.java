package com.astrolabs.wastelandframework.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class StructureConfig {
    public final IntValue ruinSpawnChance;
    public final IntValue vehicleWreckChance;
    public final IntValue bunkerSpawnChance;
    public final IntValue cellTowerChance;
    public final IntValue landmarkRarity;
    
    public final IntValue minStructureDistance;
    public final IntValue maxStructuresPerChunk;
    
    public final BooleanValue generateLoot;
    public final DoubleValue lootQualityMultiplier;
    public final BooleanValue spawnStructureMobs;
    
    public StructureConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Structure generation settings").push("structures");
        
        builder.push("spawnChances");
        
        ruinSpawnChance = builder
            .comment("Chance for ruins to spawn (1 in X chunks)")
            .defineInRange("ruinSpawnChance", 50, 1, 1000);
            
        vehicleWreckChance = builder
            .comment("Chance for vehicle wrecks to spawn (1 in X chunks)")
            .defineInRange("vehicleWreckChance", 100, 1, 1000);
            
        bunkerSpawnChance = builder
            .comment("Chance for bunkers to spawn (1 in X chunks)")
            .defineInRange("bunkerSpawnChance", 200, 1, 1000);
            
        cellTowerChance = builder
            .comment("Chance for cell towers to spawn (1 in X chunks)")
            .defineInRange("cellTowerChance", 150, 1, 1000);
            
        landmarkRarity = builder
            .comment("Rarity of special landmarks (1 in X structures)")
            .defineInRange("landmarkRarity", 10, 1, 100);
            
        builder.pop();
        
        minStructureDistance = builder
            .comment("Minimum distance between structures in blocks")
            .defineInRange("minStructureDistance", 128, 32, 1024);
            
        maxStructuresPerChunk = builder
            .comment("Maximum structures that can generate in a single chunk")
            .defineInRange("maxStructuresPerChunk", 1, 1, 5);
            
        generateLoot = builder
            .comment("Generate loot in structures")
            .define("generateLoot", true);
            
        lootQualityMultiplier = builder
            .comment("Multiplier for loot quality/quantity")
            .defineInRange("lootQualityMultiplier", 1.0, 0.1, 10.0);
            
        spawnStructureMobs = builder
            .comment("Spawn special mobs in structures")
            .define("spawnStructureMobs", true);
            
        builder.pop();
    }
}