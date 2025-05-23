package com.astrolabs.wastelandframework.common.commands;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class DebugCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("debug")
            .requires(source -> source.hasPermission(2) && WastelandConfig.DEBUG_MODE.get())
            .then(Commands.literal("info")
                .executes(context -> {
                    var source = context.getSource();
                    source.sendSuccess(() -> Component.literal("=== Wasteland Framework Debug Info ===")
                        .withStyle(ChatFormatting.GOLD), false);
                    
                    source.sendSuccess(() -> Component.literal("Version: " + WastelandFramework.getInstance()
                        .getClass().getPackage().getImplementationVersion())
                        .withStyle(ChatFormatting.YELLOW), false);
                    
                    source.sendSuccess(() -> Component.literal("Debug Mode: " + WastelandConfig.DEBUG_MODE.get())
                        .withStyle(ChatFormatting.YELLOW), false);
                    
                    source.sendSuccess(() -> Component.literal("Performance Mode: " + 
                        WastelandConfig.PERFORMANCE_MODE.get())
                        .withStyle(ChatFormatting.YELLOW), false);
                    
                    return 1;
                })
            )
            .then(Commands.literal("toggle")
                .executes(context -> {
                    // This would require runtime config modification
                    context.getSource().sendFailure(
                        Component.literal("Debug mode can only be toggled in the config file"));
                    return 0;
                })
            );
    }
}