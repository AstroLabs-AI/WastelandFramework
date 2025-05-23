# Wasteland Framework

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green.svg)
![Forge](https://img.shields.io/badge/Forge-47.2.0+-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)
![Version](https://img.shields.io/badge/Version-1.0.1-orange.svg)

A fully modular post-apocalyptic transformation mod for Minecraft Forge 1.20.1. Transform your world into a desolate wasteland with configurable hazards, scarce resources, and survival challenges.

## 🌟 Features

### 🏗️ Modular Architecture
- **10 Independent Modules** - Enable or disable any feature
- **Hot-Reload Configuration** - Change settings without restarting
- **Performance Optimized** - Disabled modules don't impact performance

### 🎮 Current Content

#### Items (7)
- **Metal Scrap** - Common salvaged metal pieces
- **Electronic Scrap** - Salvaged circuits and wires
- **Cloth Scrap** - Torn fabric and materials
- **Plastic Scrap** - Various plastic components
- **Mechanical Parts** - Gears, springs, and mechanisms
- **Contaminated Water** - Harmful water that needs purification
- **Block Items** - For placing contaminated soil and ash

#### Blocks (2)
- **Contaminated Soil** - Radioactive ground that damages entities
- **Ash Layer** - Accumulating ash (like snow, up to 8 layers)

#### Effects (1)
- **Radiation** - Damage over time from exposure to hazards

#### Recipes (3)
- **Water Purification** - Contaminated Water + Charcoal → Clean Water
- **Scrap Salvaging** - 9 Metal Scrap → 2 Iron Nuggets
- **Makeshift Tools** - Metal Scrap + Sticks → Stone Pickaxe

## 📦 Modules

### 1. World Module 🌍
- Adds contaminated blocks and ash layers
- Surface replacement system (coming soon)
- Biome transformation (coming soon)

### 2. Structure Module 🏚️
- Framework for wasteland structures
- Commands to spawn ruins (implementation coming)
- Loot integration ready

### 3. Hazard Module ☢️
- Radiation system with damage effects
- Contaminated blocks that harm entities
- Framework for acid rain and dust storms

### 4. Atmosphere Module 🌫️
- Configuration for fog and sky colors
- Ambient sound system (coming soon)
- Screen effects framework

### 5. Survival Module 🥤
- Contaminated water mechanics
- Resource scarcity configuration
- Food spoilage system (coming soon)

### 6. Anomaly Module 🌀
- Framework for dynamic world events
- Blight spreading system (coming soon)
- Reality distortions planned

### 7. Entity Module 🧟
- Framework for mutant mobs
- NPC faction system planned
- Custom AI behaviors ready

### 8. Salvage Module 🔧
- Scrap collection items
- Salvage crafting recipes
- Jury-rigged tools system

### 9. Lore Module 📜
- Framework for story elements
- Journal system planned
- Interactive props ready

### 10. Event Module 🎲
- Random event framework
- Supply drop system planned
- World events ready

## 🛠️ Installation

1. **Install Minecraft Forge**
   - Minecraft 1.20.1
   - Forge 47.2.0 or later

2. **Download the Mod**
   - Get the latest release from [Releases](https://github.com/AstroLabs-AI/WastelandFramework/releases)
   - Download `wastelandframework-1.0.0.jar`

3. **Install**
   - Place the JAR file in your `mods` folder
   - Launch Minecraft with Forge

## ⚙️ Configuration

All features can be configured in:
```
config/wastelandframework-common.toml
```

### Example Configuration
```toml
[general]
debugMode = false
performanceMode = "BALANCED"

[modules]
enableWorldModule = true
enableHazardModule = true
enableSurvivalModule = true

[hazards.radiation]
enabled = true
damagePerSecond = 1.0
zoneChance = 0.05

[survival]
globalResourceMultiplier = 0.5
contaminatedWaterChance = 0.7
```

## 📋 Commands

All commands require operator permissions (level 2+):

- `/wasteland modules list` - List all active modules
- `/wasteland modules info <module>` - Get detailed module information
- `/wasteland reload` - Reload all configurations
- `/wasteland debug info` - Show debug information (requires debug mode)
- `/wasteland hazard spawn radiation <pos> <radius> <strength>` - Spawn radiation zone
- `/wasteland structure spawn <type> [pos]` - Spawn a structure

## 🔧 For Developers

### Building from Source
```bash
git clone https://github.com/AstroLabs-AI/WastelandFramework.git
cd WastelandFramework
./gradlew build
```

### API Usage
```java
// Get the module manager
ModuleManager manager = WastelandFramework.getInstance().getModuleManager();

// Check if a module is enabled
if (manager.getModule("hazard").map(IModule::isEnabled).orElse(false)) {
    // Hazard module is active
}
```

### Adding Custom Modules
Implement the `IModule` interface:
```java
public class CustomModule implements IModule {
    @Override
    public String getId() { return "custom"; }
    
    @Override
    public String getName() { return "Custom Module"; }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        // Register your content
    }
}
```

## 🚀 Roadmap

### Version 1.1.0
- [ ] Full biome transformation
- [ ] Working wasteland structures
- [ ] Environmental weather effects

### Version 1.2.0
- [ ] Mutant entities
- [ ] NPC factions
- [ ] Trading systems

### Version 1.3.0
- [ ] Anomaly implementation
- [ ] Random world events
- [ ] Story progression

## 🤝 Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.txt) file for details.

## 🙏 Credits

- **AstroLabs** - Initial development
- **Minecraft Forge Team** - For the amazing modding platform
- **Community Contributors** - For feedback and suggestions

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/AstroLabs-AI/WastelandFramework/issues)
- **Discord**: Coming soon
- **Wiki**: Coming soon

## 🔗 Links

- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/wasteland-framework) (Coming soon)
- [Modrinth](https://modrinth.com/mod/wasteland-framework) (Coming soon)
- [GitHub](https://github.com/AstroLabs-AI/WastelandFramework)

---

Made with ❤️ for the Minecraft modding community