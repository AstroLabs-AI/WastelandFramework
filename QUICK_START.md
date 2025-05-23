# Wasteland Framework - Quick Start Development Guide

## Step 1: Initialize Forge Project

1. Download Forge MDK 1.20.1-47.2.0 from https://files.minecraftforge.net/
2. Extract MDK files to project root
3. Update `build.gradle`:

```gradle
buildscript {
    repositories {
        maven { url = 'https://maven.minecraftforge.net' }
        mavenCentral()
    }
    dependencies {
        classpath group: 'net.minecraftforge.gradle', name: 'ForgeGradle', version: '6.0.+', changing: true
    }
}

apply plugin: 'net.minecraftforge.gradle'
apply plugin: 'eclipse'
apply plugin: 'idea'
apply plugin: 'maven-publish'

version = '1.0.0'
group = 'com.astrolabs.wastelandframework'
archivesBaseName = 'wastelandframework'

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

minecraft {
    mappings channel: 'official', version: '1.20.1'
    
    runs {
        client {
            workingDirectory project.file('run')
            property 'forge.logging.console.level', 'debug'
            mods {
                wastelandframework {
                    source sourceSets.main
                }
            }
        }
        
        server {
            workingDirectory project.file('run')
            property 'forge.logging.console.level', 'debug'
            mods {
                wastelandframework {
                    source sourceSets.main
                }
            }
        }
        
        data {
            workingDirectory project.file('run')
            property 'forge.logging.console.level', 'debug'
            args '--mod', 'wastelandframework', '--all', '--output', file('src/generated/resources/'), '--existing', file('src/main/resources/')
            mods {
                wastelandframework {
                    source sourceSets.main
                }
            }
        }
    }
}

sourceSets.main.resources { srcDir 'src/generated/resources' }

repositories {
    maven { url 'https://maven.blamejared.com' }
}

dependencies {
    minecraft 'net.minecraftforge:forge:1.20.1-47.2.0'
}
```

4. Update `gradle.properties`:

```properties
org.gradle.jvmargs=-Xmx3G
org.gradle.daemon=false

mod_id=wastelandframework
mod_name=Wasteland Framework
mod_license=MIT
mod_version=1.0.0
mod_group_id=com.astrolabs.wastelandframework
mod_authors=AstroLabs
mod_description=A fully configurable, modular Wasteland utility mod
```

## Step 2: Create Main Mod Class

Create `src/main/java/com/astrolabs/wastelandframework/WastelandFramework.java`:

```java
package com.astrolabs.wastelandframework;

import com.astrolabs.wastelandframework.core.ModuleManager;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        
        // Initialize module manager
        moduleManager = new ModuleManager(modEventBus, forgeEventBus);
        
        // Register setup
        modEventBus.addListener(this::commonSetup);
        
        LOGGER.info("Wasteland Framework initialized");
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            moduleManager.initializeModules();
            LOGGER.info("Wasteland Framework modules loaded");
        });
    }
    
    public static WastelandFramework getInstance() {
        return instance;
    }
    
    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}
```

## Step 3: Create Module System

Create `src/main/java/com/astrolabs/wastelandframework/core/IModule.java`:

```java
package com.astrolabs.wastelandframework.core;

import net.minecraftforge.eventbus.api.IEventBus;

public interface IModule {
    String getId();
    String getName();
    boolean isEnabled();
    void onRegister(IEventBus modBus, IEventBus forgeBus);
    void onInitialize();
    void onConfigReload();
}
```

## Step 4: Create Basic Config

Create `src/main/java/com/astrolabs/wastelandframework/core/config/WastelandConfig.java`:

```java
package com.astrolabs.wastelandframework.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class WastelandConfig {
    public static final ForgeConfigSpec SPEC;
    
    public static final BooleanValue DEBUG_MODE;
    public static final BooleanValue ENABLE_WORLD_MODULE;
    public static final BooleanValue ENABLE_STRUCTURE_MODULE;
    public static final BooleanValue ENABLE_HAZARD_MODULE;
    
    static {
        Builder builder = new Builder();
        
        builder.push("general");
        DEBUG_MODE = builder
            .comment("Enable debug mode for additional logging")
            .define("debugMode", false);
        builder.pop();
        
        builder.push("modules");
        ENABLE_WORLD_MODULE = builder
            .comment("Enable world generation modifications")
            .define("enableWorldModule", true);
        ENABLE_STRUCTURE_MODULE = builder
            .comment("Enable custom structure generation")
            .define("enableStructureModule", true);
        ENABLE_HAZARD_MODULE = builder
            .comment("Enable environmental hazards")
            .define("enableHazardModule", true);
        builder.pop();
        
        SPEC = builder.build();
    }
}
```

## Step 5: Create mods.toml

Create `src/main/resources/META-INF/mods.toml`:

```toml
modLoader="javafml"
loaderVersion="[47,)"
license="MIT"

[[mods]]
modId="wastelandframework"
version="${file.jarVersion}"
displayName="Wasteland Framework"
authors="AstroLabs"
description='''
A fully configurable, modular Wasteland utility mod with advanced, 
creative, and structure-supporting features for Minecraft 1.20.1
'''

[[dependencies.wastelandframework]]
    modId="forge"
    mandatory=true
    versionRange="[47,)"
    ordering="NONE"
    side="BOTH"

[[dependencies.wastelandframework]]
    modId="minecraft"
    mandatory=true
    versionRange="[1.20.1,1.21)"
    ordering="NONE"
    side="BOTH"
```

## Step 6: Initial Test Run

1. Run `./gradlew build` to verify setup
2. Run `./gradlew runClient` to test in-game
3. Check logs for "Wasteland Framework initialized"

## Step 7: Next Implementation Steps

### Week 1 Focus:
1. Implement ModuleManager class
2. Create first module (WorldModule) 
3. Set up basic biome modification
4. Add debug commands

### Week 2 Focus:
1. Structure generation system
2. Basic hazard framework
3. Config hot-reload
4. Data generation setup

## Development Commands

```bash
# Build mod
./gradlew build

# Run client
./gradlew runClient

# Run server
./gradlew runServer

# Generate data
./gradlew runData

# Clean project
./gradlew clean

# Setup IDE
./gradlew eclipse  # For Eclipse
./gradlew idea     # For IntelliJ
```

## Project Structure to Create

```
src/
├── main/
│   ├── java/com/astrolabs/wastelandframework/
│   │   ├── WastelandFramework.java
│   │   ├── core/
│   │   │   ├── ModuleManager.java
│   │   │   ├── IModule.java
│   │   │   └── config/
│   │   ├── common/
│   │   │   ├── blocks/
│   │   │   ├── items/
│   │   │   └── commands/
│   │   ├── modules/
│   │   │   ├── world/
│   │   │   ├── structure/
│   │   │   └── hazard/
│   │   └── client/
│   └── resources/
│       ├── META-INF/mods.toml
│       └── assets/wastelandframework/
│           └── lang/en_us.json
└── generated/resources/
```

## Useful Resources

- Forge Docs: https://docs.minecraftforge.net/
- Forge Community Wiki: https://forge.gemwire.uk/
- McJty Modding Tutorials: https://wiki.mcjty.eu/modding/
- Minecraft Wiki: https://minecraft.wiki/

Ready to start coding! Begin with Step 1 and work through each step. The IMPLEMENTATION_PLAN.md has the detailed timeline for features.