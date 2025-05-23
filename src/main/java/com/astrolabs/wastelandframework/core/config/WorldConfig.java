package com.astrolabs.wastelandframework.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

import java.util.Arrays;
import java.util.List;

public class WorldConfig {
    public final BooleanValue replaceSurfaceBlocks;
    public final IntValue wastelandBiomeWeight;
    public final ConfigValue<List<? extends String>> biomesBlacklist;
    public final ConfigValue<List<? extends String>> biomesWhitelist;
    public final BooleanValue useWhitelist;
    
    // Surface replacements
    public final ConfigValue<List<? extends String>> grassReplacements;
    public final ConfigValue<List<? extends String>> dirtReplacements;
    public final ConfigValue<List<? extends String>> stoneReplacements;
    public final ConfigValue<List<? extends String>> sandReplacements;
    public final ConfigValue<List<? extends String>> gravelReplacements;
    
    public final DoubleValue deadTreeChance;
    public final DoubleValue ashLayerChance;
    public final BooleanValue removeVegetation;
    
    public WorldConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("World generation settings").push("world");
        
        replaceSurfaceBlocks = builder
            .comment("Replace surface blocks with wasteland variants")
            .define("replaceSurfaceBlocks", true);
            
        wastelandBiomeWeight = builder
            .comment("Weight for wasteland biome generation (higher = more common)")
            .defineInRange("wastelandBiomeWeight", 10, 0, 100);
            
        biomesBlacklist = builder
            .comment("List of biomes to exclude from wasteland transformation",
                     "Example: [\"minecraft:mushroom_fields\", \"minecraft:deep_ocean\"]")
            .defineListAllowEmpty(Arrays.asList("biomesBlacklist"), 
                () -> Arrays.asList("minecraft:mushroom_fields", "minecraft:the_void"),
                obj -> obj instanceof String);
                
        biomesWhitelist = builder
            .comment("List of biomes to include in wasteland transformation (if useWhitelist is true)")
            .defineListAllowEmpty(Arrays.asList("biomesWhitelist"), 
                () -> Arrays.asList("minecraft:plains", "minecraft:forest", "minecraft:desert"),
                obj -> obj instanceof String);
                
        useWhitelist = builder
            .comment("Use whitelist instead of blacklist for biome filtering")
            .define("useWhitelist", false);
            
        builder.push("surfaceReplacements");
        
        grassReplacements = builder
            .comment("Blocks that grass will be replaced with (randomly selected)")
            .defineListAllowEmpty(Arrays.asList("grassReplacements"),
                () -> Arrays.asList("minecraft:coarse_dirt", "minecraft:dirt", "minecraft:gravel"),
                obj -> obj instanceof String);
                
        dirtReplacements = builder
            .comment("Blocks that dirt will be replaced with")
            .defineListAllowEmpty(Arrays.asList("dirtReplacements"),
                () -> Arrays.asList("minecraft:coarse_dirt", "minecraft:gravel"),
                obj -> obj instanceof String);
                
        stoneReplacements = builder
            .comment("Blocks that stone will be replaced with")
            .defineListAllowEmpty(Arrays.asList("stoneReplacements"),
                () -> Arrays.asList("minecraft:cobblestone", "minecraft:andesite"),
                obj -> obj instanceof String);
                
        sandReplacements = builder
            .comment("Blocks that sand will be replaced with")
            .defineListAllowEmpty(Arrays.asList("sandReplacements"),
                () -> Arrays.asList("minecraft:red_sand", "minecraft:gravel"),
                obj -> obj instanceof String);
                
        gravelReplacements = builder
            .comment("Blocks that gravel will be replaced with")
            .defineListAllowEmpty(Arrays.asList("gravelReplacements"),
                () -> List.of("minecraft:coarse_dirt"),
                obj -> obj instanceof String);
                
        builder.pop();
        
        deadTreeChance = builder
            .comment("Chance for trees to be dead/leafless (0.0-1.0)")
            .defineInRange("deadTreeChance", 0.7, 0.0, 1.0);
            
        ashLayerChance = builder
            .comment("Chance for ash layers to generate on surfaces (0.0-1.0)")
            .defineInRange("ashLayerChance", 0.3, 0.0, 1.0);
            
        removeVegetation = builder
            .comment("Remove most vegetation (flowers, tall grass, etc)")
            .define("removeVegetation", true);
            
        builder.pop();
    }
}