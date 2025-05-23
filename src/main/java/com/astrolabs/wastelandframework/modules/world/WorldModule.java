package com.astrolabs.wastelandframework.modules.world;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import com.astrolabs.wastelandframework.core.registry.WastelandRegistries;
import com.astrolabs.wastelandframework.common.blocks.AshLayerBlock;
import com.astrolabs.wastelandframework.common.blocks.ContaminatedSoilBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WorldModule implements IModule {
    // Blocks - these static registrations are fine since they use the main registry
    public static final RegistryObject<Block> CONTAMINATED_SOIL = WastelandRegistries.BLOCKS.register("contaminated_soil",
        () -> new ContaminatedSoilBlock());
    
    public static final RegistryObject<Block> ASH_LAYER = WastelandRegistries.BLOCKS.register("ash_layer",
        () -> new AshLayerBlock());
    
    // Block Items
    public static final RegistryObject<Item> CONTAMINATED_SOIL_ITEM = WastelandRegistries.ITEMS.register("contaminated_soil",
        () -> new BlockItem(CONTAMINATED_SOIL.get(), new Item.Properties()));
    
    public static final RegistryObject<Item> ASH_LAYER_ITEM = WastelandRegistries.ITEMS.register("ash_layer",
        () -> new BlockItem(ASH_LAYER.get(), new Item.Properties()));
    
    private final Map<Block, List<Block>> surfaceReplacements = new HashMap<>();
    private final Random random = new Random();
    private WastelandSurfaceReplacer surfaceReplacer;
    
    @Override
    public String getId() {
        return "world";
    }
    
    @Override
    public String getName() {
        return "World Generation";
    }
    
    @Override
    public ModuleCategory getCategory() {
        return ModuleCategory.WORLDGEN;
    }
    
    @Override
    public boolean isEnabled() {
        return WastelandConfig.ENABLE_WORLD_MODULE.get();
    }
    
    @Override
    public void onRegister(IEventBus modBus, IEventBus forgeBus) {
        // For now, skip biome modifier registration as it's causing issues
        // TODO: Implement biome modification in a future update
        WastelandFramework.LOGGER.info("World Module registered");
    }
    
    @Override
    public void onInitialize() {
        loadSurfaceReplacements();
        surfaceReplacer = new WastelandSurfaceReplacer(this);
        WastelandFramework.LOGGER.info("World Module initialized");
    }
    
    @Override
    public void onClientInitialize() {
        // No client-specific initialization needed
    }
    
    @Override
    public void onConfigReload() {
        surfaceReplacements.clear();
        loadSurfaceReplacements();
    }
    
    private void loadSurfaceReplacements() {
        var config = WastelandConfig.WORLD;
        
        // Load grass replacements
        loadReplacementList(Blocks.GRASS_BLOCK, config.grassReplacements.get());
        
        // Load dirt replacements
        loadReplacementList(Blocks.DIRT, config.dirtReplacements.get());
        
        // Load stone replacements
        loadReplacementList(Blocks.STONE, config.stoneReplacements.get());
        
        // Load sand replacements
        loadReplacementList(Blocks.SAND, config.sandReplacements.get());
        
        // Load gravel replacements
        loadReplacementList(Blocks.GRAVEL, config.gravelReplacements.get());
    }
    
    private void loadReplacementList(Block original, List<? extends String> replacements) {
        List<Block> blocks = replacements.stream()
            .map(ResourceLocation::new)
            .map(ForgeRegistries.BLOCKS::getValue)
            .filter(block -> block != null && block != Blocks.AIR)
            .toList();
        
        if (!blocks.isEmpty()) {
            surfaceReplacements.put(original, blocks);
        }
    }
    
    public Block getRandomReplacement(Block original) {
        List<Block> replacements = surfaceReplacements.get(original);
        if (replacements == null || replacements.isEmpty()) {
            return original;
        }
        return replacements.get(random.nextInt(replacements.size()));
    }
    
    public boolean shouldTransformBiome(ResourceKey<Biome> biomeKey) {
        String biomeName = biomeKey.location().toString();
        
        if (WastelandConfig.WORLD.useWhitelist.get()) {
            return WastelandConfig.WORLD.biomesWhitelist.get().contains(biomeName);
        } else {
            return !WastelandConfig.WORLD.biomesBlacklist.get().contains(biomeName);
        }
    }
}