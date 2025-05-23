package com.astrolabs.wastelandframework.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class AtmosphereConfig {
    public final BooleanValue enableCustomFog;
    public final DoubleValue fogDensity;
    public final IntValue fogColorR;
    public final IntValue fogColorG;
    public final IntValue fogColorB;
    
    public final BooleanValue enableCustomSky;
    public final IntValue skyColorR;
    public final IntValue skyColorG;
    public final IntValue skyColorB;
    
    public final BooleanValue permanentTwilight;
    public final DoubleValue sunBrightness;
    
    public final BooleanValue enableAmbientSounds;
    public final DoubleValue ambientSoundVolume;
    public final IntValue ambientSoundFrequency;
    
    public final BooleanValue enableScreenEffects;
    public final BooleanValue enableVignette;
    public final BooleanValue enableStaticNoise;
    public final DoubleValue effectIntensity;
    
    public AtmosphereConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Atmospheric and visual settings").push("atmosphere");
        
        // Fog settings
        builder.push("fog");
        
        enableCustomFog = builder
            .comment("Enable custom fog rendering")
            .define("enableCustomFog", true);
            
        fogDensity = builder
            .comment("Fog density multiplier")
            .defineInRange("fogDensity", 0.7, 0.1, 2.0);
            
        fogColorR = builder
            .comment("Fog color red component (0-255)")
            .defineInRange("fogColorR", 150, 0, 255);
            
        fogColorG = builder
            .comment("Fog color green component (0-255)")
            .defineInRange("fogColorG", 140, 0, 255);
            
        fogColorB = builder
            .comment("Fog color blue component (0-255)")
            .defineInRange("fogColorB", 110, 0, 255);
            
        builder.pop();
        
        // Sky settings
        builder.push("sky");
        
        enableCustomSky = builder
            .comment("Enable custom sky colors")
            .define("enableCustomSky", true);
            
        skyColorR = builder
            .comment("Sky color red component (0-255)")
            .defineInRange("skyColorR", 180, 0, 255);
            
        skyColorG = builder
            .comment("Sky color green component (0-255)")
            .defineInRange("skyColorG", 160, 0, 255);
            
        skyColorB = builder
            .comment("Sky color blue component (0-255)")
            .defineInRange("skyColorB", 120, 0, 255);
            
        permanentTwilight = builder
            .comment("Keep the world in permanent twilight")
            .define("permanentTwilight", false);
            
        sunBrightness = builder
            .comment("Sun brightness multiplier")
            .defineInRange("sunBrightness", 0.6, 0.1, 1.0);
            
        builder.pop();
        
        // Sound settings
        builder.push("sounds");
        
        enableAmbientSounds = builder
            .comment("Enable ambient wasteland sounds")
            .define("enableAmbientSounds", true);
            
        ambientSoundVolume = builder
            .comment("Volume of ambient sounds")
            .defineInRange("ambientSoundVolume", 0.5, 0.0, 1.0);
            
        ambientSoundFrequency = builder
            .comment("Frequency of ambient sounds in ticks")
            .defineInRange("ambientSoundFrequency", 200, 20, 6000);
            
        builder.pop();
        
        // Screen effects
        builder.push("screenEffects");
        
        enableScreenEffects = builder
            .comment("Enable screen overlay effects")
            .define("enableScreenEffects", true);
            
        enableVignette = builder
            .comment("Enable vignette overlay")
            .define("enableVignette", true);
            
        enableStaticNoise = builder
            .comment("Enable static noise effect")
            .define("enableStaticNoise", true);
            
        effectIntensity = builder
            .comment("Overall intensity of screen effects")
            .defineInRange("effectIntensity", 0.5, 0.0, 1.0);
            
        builder.pop();
        builder.pop();
    }
}