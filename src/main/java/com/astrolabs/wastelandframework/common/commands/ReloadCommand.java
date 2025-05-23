package com.astrolabs.wastelandframework.common.commands;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ReloadCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("reload")
            .requires(source -> source.hasPermission(3))
            .executes(context -> {
                var source = context.getSource();
                
                source.sendSuccess(() -> Component.literal("Reloading Wasteland Framework configurations...")
                    .withStyle(ChatFormatting.YELLOW), true);
                
                try {
                    WastelandFramework.getInstance().getModuleManager().reloadConfigs();
                    
                    source.sendSuccess(() -> Component.literal("Successfully reloaded all configurations!")
                        .withStyle(ChatFormatting.GREEN), true);
                    
                    return 1;
                } catch (Exception e) {
                    source.sendFailure(Component.literal("Failed to reload configurations: " + e.getMessage())
                        .withStyle(ChatFormatting.RED));
                    return 0;
                }
            });
    }
}