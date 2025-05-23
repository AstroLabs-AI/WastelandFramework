package com.astrolabs.wastelandframework.common.commands;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.IModule;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

import java.util.concurrent.CompletableFuture;

public class ModuleArgument implements ArgumentType<IModule> {
    private static final DynamicCommandExceptionType MODULE_NOT_FOUND = 
        new DynamicCommandExceptionType(name -> 
            Component.literal("Module not found: " + name));
    
    public static ModuleArgument module() {
        return new ModuleArgument();
    }
    
    public static IModule getModule(CommandContext<CommandSourceStack> context, String name) {
        return context.getArgument(name, IModule.class);
    }
    
    @Override
    public IModule parse(StringReader reader) throws CommandSyntaxException {
        String moduleId = reader.readUnquotedString();
        return WastelandFramework.getInstance().getModuleManager()
            .getModule(moduleId)
            .orElseThrow(() -> MODULE_NOT_FOUND.create(moduleId));
    }
    
    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(
            WastelandFramework.getInstance().getModuleManager()
                .getActiveModules().stream()
                .map(IModule::getId),
            builder
        );
    }
}