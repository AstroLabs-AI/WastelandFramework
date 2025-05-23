package com.astrolabs.wastelandframework.core.registry;

import com.astrolabs.wastelandframework.WastelandFramework;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WastelandRegistries {
    // Vanilla registries
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, WastelandFramework.MODID);
        
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, WastelandFramework.MODID);
        
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WastelandFramework.MODID);
        
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WastelandFramework.MODID);
        
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = 
        DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, WastelandFramework.MODID);
        
    public static final DeferredRegister<Feature<?>> FEATURES = 
        DeferredRegister.create(ForgeRegistries.FEATURES, WastelandFramework.MODID);
        
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = 
        DeferredRegister.create(Registries.STRUCTURE_TYPE, WastelandFramework.MODID);
        
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WastelandFramework.MODID);
    
    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        ENTITIES.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);
        FEATURES.register(modEventBus);
        STRUCTURE_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        
        WastelandFramework.LOGGER.info("Wasteland Framework registries initialized");
    }
}