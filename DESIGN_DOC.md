# Wasteland Framework - Technical Design Document

## Core Architecture

### 1. Module System Design

The module system is the heart of Wasteland Framework, allowing users to enable/disable features.

```java
public interface IWastelandModule {
    String getModuleId();
    void onRegister();
    void onInitialize();
    void onConfigReload();
    boolean isEnabled();
    ModuleCategory getCategory();
}
```

**Module Categories:**
- `WORLDGEN` - Biome and structure generation
- `ENVIRONMENT` - Weather, hazards, atmosphere
- `GAMEPLAY` - Survival mechanics, salvage
- `ENTITY` - Mobs, NPCs, factions
- `CONTENT` - Items, blocks, crafting
- `EVENT` - Dynamic events, anomalies

### 2. Configuration Architecture

```toml
[modules]
    [modules.world]
    enabled = true
    replace_biomes = ["minecraft:plains", "minecraft:forest"]
    surface_replacements = {
        "minecraft:grass_block" = "minecraft:coarse_dirt",
        "minecraft:dirt" = "minecraft:gravel"
    }
    
    [modules.hazards]
    enabled = true
        [modules.hazards.radiation]
        enabled = true
        damage_per_second = 1.0
        zone_spawn_chance = 0.05
        
        [modules.hazards.acid_rain]
        enabled = true
        chance_per_day = 0.3
        damage_per_tick = 0.5
```

### 3. Data-Driven Systems

#### Structure Pool Format
```json
{
  "name": "wasteland:ruined_house",
  "weight": 10,
  "min_distance": 500,
  "biomes": ["#wasteland:wasteland_biomes"],
  "processors": ["wasteland:decay_processor"],
  "loot_tables": {
    "chest": "wasteland:ruined_house_loot",
    "barrel": "wasteland:scavenger_supplies"
  }
}
```

#### Hazard Definition Format
```json
{
  "type": "wasteland:radiation_zone",
  "radius": 32,
  "strength": 1.5,
  "effects": [
    {
      "effect": "minecraft:poison",
      "duration": 200,
      "amplifier": 0
    }
  ],
  "particles": "wasteland:radiation_particles"
}
```

### 4. Event System

```java
// Custom Forge Events
public class WastelandWeatherEvent extends Event {
    private final WeatherType weatherType;
    private final Level level;
    private final int duration;
}

public class AnomalySpawnEvent extends Event {
    private final AnomalyType type;
    private final BlockPos position;
}
```

### 5. Capability System

```java
// Player radiation exposure
public interface IRadiationCapability {
    float getRadiationLevel();
    void addRadiation(float amount);
    void tick();
}

// Chunk contamination
public interface IContaminationCapability {
    ContaminationType getContamination();
    float getIntensity();
}
```

## Implementation Patterns

### 1. Registry Pattern
```java
public class WastelandRegistries {
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    
    public static final DeferredRegister<AnomalyType> ANOMALIES = 
        DeferredRegister.create(ANOMALY_REGISTRY, MODID);
}
```

### 2. Factory Pattern for Structures
```java
public interface IStructureFactory {
    StructureTemplate create(ResourceLocation id);
    void registerProcessors();
    LootTable getLootTable(String marker);
}
```

### 3. Strategy Pattern for Hazards
```java
public interface IHazardBehavior {
    void applyToEntity(LivingEntity entity, float intensity);
    void applyToBlock(BlockPos pos, Level level);
    void renderEffects(ClientLevel level, BlockPos pos);
}
```

## Performance Optimizations

### 1. Chunk-Based Processing
- Hazards calculated per chunk, cached
- Structure generation uses chunk boundaries
- Weather effects use chunk-based LOD

### 2. Lazy Module Loading
```java
public class ModuleManager {
    private final Map<String, Supplier<IWastelandModule>> moduleSuppliers;
    
    public void loadModule(String id) {
        if (isEnabled(id)) {
            modules.put(id, moduleSuppliers.get(id).get());
        }
    }
}
```

### 3. Event Batching
- Combine multiple weather updates
- Batch particle spawning
- Group sound updates

## Integration Points

### 1. Forge Events Used
- `BiomeLoadingEvent` - Modify biomes
- `StructureSpawnListGatherEvent` - Add structure spawns
- `LivingHurtEvent` - Apply hazard damage
- `RenderLevelStageEvent` - Atmospheric effects

### 2. Vanilla Systems Extended
- Loot tables via Global Loot Modifiers
- Recipes via custom recipe types
- World generation via configured features

### 3. Mod Compatibility
- IMC (Inter-Mod Communication) for structure registration
- Tags for biome/block/entity compatibility
- Optional soft dependencies (JEI, TOP, etc.)

## API Design

### Public API Package
```java
package com.astrolabs.wastelandframework.api;

public interface IWastelandAPI {
    void registerHazard(ResourceLocation id, IHazardBehavior hazard);
    void registerAnomaly(ResourceLocation id, IAnomalyType anomaly);
    void registerStructureProcessor(IStructureProcessor processor);
    
    // Event hooks
    void onWeatherChange(Consumer<WastelandWeatherEvent> listener);
    void onAnomalySpawn(Consumer<AnomalySpawnEvent> listener);
}
```

## Testing Strategy

### 1. Unit Tests
- Module enable/disable logic
- Configuration parsing
- Registry systems

### 2. Integration Tests
- Module interactions
- Event firing order
- Data loading

### 3. Game Tests
```java
@GameTest
public class StructureSpawnTest {
    @GameTestTemplate("wasteland:structure_test")
    public static void testRuinSpawning(GameTestHelper helper) {
        // Test structure generation
    }
}
```

## Debug Tools

### Commands
- `/wasteland debug modules` - List active modules
- `/wasteland debug hazards` - Show hazard zones
- `/wasteland debug structures` - Force spawn structures
- `/wasteland reload` - Reload configs/datapacks

### F3 Debug Info
- Current hazard levels
- Active weather effects
- Module performance metrics