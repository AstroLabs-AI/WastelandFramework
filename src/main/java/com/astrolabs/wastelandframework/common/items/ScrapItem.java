package com.astrolabs.wastelandframework.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ScrapItem extends Item {
    private final ScrapType scrapType;
    
    public ScrapItem(ScrapType type) {
        super(new Item.Properties().stacksTo(64));
        this.scrapType = type;
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.wastelandframework.scrap." + scrapType.name().toLowerCase() + ".desc")
            .withStyle(ChatFormatting.GRAY));
    }
    
    public ScrapType getScrapType() {
        return scrapType;
    }
    
    public enum ScrapType {
        METAL("Metal Scrap", "Common salvaged metal pieces"),
        ELECTRONIC("Electronic Scrap", "Salvaged circuits and wires"),
        CLOTH("Cloth Scrap", "Torn fabric and materials"),
        PLASTIC("Plastic Scrap", "Various plastic components"),
        MECHANICAL("Mechanical Parts", "Gears, springs, and mechanisms");
        
        private final String displayName;
        private final String description;
        
        ScrapType(String displayName, String description) {
            this.displayName = displayName;
            this.description = description;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public String getDescription() {
            return description;
        }
    }
}