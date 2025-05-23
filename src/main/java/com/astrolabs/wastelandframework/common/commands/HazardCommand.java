package com.astrolabs.wastelandframework.common.commands;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class HazardCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("hazard")
            .then(Commands.literal("spawn")
                .then(Commands.literal("radiation")
                    .then(Commands.argument("pos", BlockPosArgument.blockPos())
                        .then(Commands.argument("radius", IntegerArgumentType.integer(1, 128))
                            .then(Commands.argument("strength", FloatArgumentType.floatArg(0.1f, 10.0f))
                                .executes(context -> {
                                    var source = context.getSource();
                                    BlockPos pos = BlockPosArgument.getBlockPos(context, "pos");
                                    int radius = IntegerArgumentType.getInteger(context, "radius");
                                    float strength = FloatArgumentType.getFloat(context, "strength");
                                    
                                    // TODO: Implement radiation zone spawning
                                    source.sendSuccess(() -> Component.literal(
                                        String.format("Spawned radiation zone at %s with radius %d and strength %.1f",
                                            pos.toShortString(), radius, strength))
                                        .withStyle(ChatFormatting.GREEN), true);
                                    
                                    return 1;
                                })
                            )
                        )
                    )
                )
                .then(Commands.literal("acidrain")
                    .executes(context -> {
                        var source = context.getSource();
                        
                        // TODO: Implement acid rain triggering
                        source.sendSuccess(() -> Component.literal("Started acid rain event")
                            .withStyle(ChatFormatting.GREEN), true);
                        
                        return 1;
                    })
                )
                .then(Commands.literal("duststorm")
                    .executes(context -> {
                        var source = context.getSource();
                        
                        // TODO: Implement dust storm triggering
                        source.sendSuccess(() -> Component.literal("Started dust storm event")
                            .withStyle(ChatFormatting.GREEN), true);
                        
                        return 1;
                    })
                )
            )
            .then(Commands.literal("clear")
                .executes(context -> {
                    var source = context.getSource();
                    
                    // TODO: Implement hazard clearing
                    source.sendSuccess(() -> Component.literal("Cleared all active hazards")
                        .withStyle(ChatFormatting.YELLOW), true);
                    
                    return 1;
                })
            );
    }
}