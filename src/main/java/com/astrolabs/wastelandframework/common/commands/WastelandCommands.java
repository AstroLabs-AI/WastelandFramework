package com.astrolabs.wastelandframework.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class WastelandCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("wasteland")
                .requires(source -> source.hasPermission(2))
                .then(DebugCommand.register())
                .then(ModuleCommand.register())
                .then(HazardCommand.register())
                .then(StructureCommand.register())
                .then(ReloadCommand.register())
        );
    }
}