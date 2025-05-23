# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

WastelandFramework is a Minecraft Forge mod for version 1.20.1. The project is currently uninitialized and requires Forge MDK setup.

## Initial Setup Required

Before development can begin, initialize the Forge project structure:
1. Download Minecraft Forge MDK 1.20.1
2. Extract MDK files to project root
3. Run `./gradlew build` to verify setup

## Common Commands

### Build & Test
- `./gradlew build` - Build the mod
- `./gradlew runClient` - Launch Minecraft client with mod
- `./gradlew runServer` - Launch dedicated server with mod
- `./gradlew runData` - Generate data files
- `./gradlew test` - Run unit tests

### Development
- `./gradlew eclipse` - Generate Eclipse project files
- `./gradlew idea` - Generate IntelliJ IDEA project files
- `./gradlew clean` - Clean build artifacts

### In-Game Commands
- `/wasteland modules list` - List active modules
- `/wasteland debug info` - Show debug information (requires debug mode)
- `/wasteland reload` - Reload configurations
- `/wasteland hazard spawn <type>` - Spawn hazards
- `/wasteland structure spawn <type>` - Spawn structures

## Project Structure

```
src/main/java/com/astrolabs/wastelandframework/
├── WastelandFramework.java - Main mod class
├── core/
│   ├── IModule.java - Module interface
│   ├── ModuleManager.java - Module system
│   ├── config/ - Configuration classes
│   └── registry/ - Deferred registries
├── common/
│   └── commands/ - Command implementations
├── modules/
│   ├── world/ - World generation
│   ├── structure/ - Structure generation
│   ├── hazard/ - Environmental hazards
│   ├── atmosphere/ - Visual effects
│   ├── survival/ - Survival mechanics
│   ├── anomaly/ - Dynamic anomalies
│   ├── entity/ - Custom entities
│   ├── salvage/ - Salvage system
│   ├── lore/ - Lore system
│   └── event/ - Random events
└── api/ - Public API

src/main/resources/
├── META-INF/mods.toml - Mod metadata
├── assets/wastelandframework/
│   └── lang/en_us.json - Translations
└── data/wastelandframework/ - Data files
```

## Key Configuration Files

- `build.gradle` - Forge version, dependencies, mod metadata
- `gradle.properties` - Mod ID, version, Maven coordinates
- `src/main/resources/META-INF/mods.toml` - Mod display info

## Architecture Patterns

Minecraft Forge 1.20.1 uses:
- Event-driven architecture via MinecraftForge EVENT_BUS
- Registry system for blocks, items, entities
- Capability system for extensible behaviors
- Data generation for recipes, loot tables, models