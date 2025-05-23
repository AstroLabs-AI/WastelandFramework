package com.astrolabs.wastelandframework.common;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.registry.WastelandRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;

public class WastelandCreativeTab {
    public static final RegistryObject<CreativeModeTab> WASTELAND_TAB = 
        WastelandRegistries.CREATIVE_MODE_TABS.register("wasteland_tab", 
            () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.wastelandframework"))
                .icon(() -> new ItemStack(Items.DEAD_BUSH)) // Temporary icon
                .displayItems((parameters, output) -> {
                    // Add all registered items here
                    WastelandRegistries.ITEMS.getEntries().forEach(item -> 
                        output.accept(item.get())
                    );
                })
                .build()
        );
}