package com.astrolabs.wastelandframework.common.commands;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ModuleCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("modules")
            .then(Commands.literal("list")
                .executes(context -> {
                    var source = context.getSource();
                    var moduleManager = WastelandFramework.getInstance().getModuleManager();
                    
                    source.sendSuccess(() -> Component.literal("=== Active Wasteland Modules ===")
                        .withStyle(ChatFormatting.GOLD), false);
                    
                    moduleManager.getModulesByCategory().forEach((category, modules) -> {
                        source.sendSuccess(() -> Component.literal(category.getDisplayName() + ":")
                            .withStyle(ChatFormatting.YELLOW), false);
                        
                        modules.forEach(module -> {
                            source.sendSuccess(() -> Component.literal("  - " + module.getName() + 
                                " (" + module.getId() + ")")
                                .withStyle(ChatFormatting.GREEN), false);
                        });
                    });
                    
                    return 1;
                })
            )
            // Temporarily disable the info command that uses custom argument type
            /*.then(Commands.literal("info")
                .then(Commands.argument("module", ModuleArgument.module())
                    .executes(context -> {
                        var source = context.getSource();
                        var module = ModuleArgument.getModule(context, "module");
                        
                        source.sendSuccess(() -> Component.literal("=== Module Info: " + module.getName() + " ===")
                            .withStyle(ChatFormatting.GOLD), false);
                        
                        source.sendSuccess(() -> Component.literal("ID: " + module.getId())
                            .withStyle(ChatFormatting.YELLOW), false);
                        
                        source.sendSuccess(() -> Component.literal("Category: " + module.getCategory().getDisplayName())
                            .withStyle(ChatFormatting.YELLOW), false);
                        
                        source.sendSuccess(() -> Component.literal("Enabled: " + module.isEnabled())
                            .withStyle(module.isEnabled() ? ChatFormatting.GREEN : ChatFormatting.RED), false);
                        
                        return 1;
                    })
                )
            )*/;
    }
}