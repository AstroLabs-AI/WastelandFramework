package com.astrolabs.wastelandframework.common.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class StructureCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("structure")
            .then(Commands.literal("spawn")
                .then(Commands.argument("type", StringArgumentType.word())
                    .suggests((context, builder) -> {
                        builder.suggest("ruined_house");
                        builder.suggest("vehicle_wreck");
                        builder.suggest("bunker");
                        builder.suggest("cell_tower");
                        builder.suggest("crashed_ship");
                        builder.suggest("hidden_lab");
                        return builder.buildFuture();
                    })
                    .executes(context -> {
                        var source = context.getSource();
                        String type = StringArgumentType.getString(context, "type");
                        BlockPos pos = source.getPlayer() != null ? 
                            source.getPlayer().blockPosition() : BlockPos.ZERO;
                        
                        // TODO: Implement structure spawning
                        source.sendSuccess(() -> Component.literal(
                            String.format("Spawning %s at %s", type, pos.toShortString()))
                            .withStyle(ChatFormatting.GREEN), true);
                        
                        return 1;
                    })
                    .then(Commands.argument("pos", BlockPosArgument.blockPos())
                        .executes(context -> {
                            var source = context.getSource();
                            String type = StringArgumentType.getString(context, "type");
                            BlockPos pos = BlockPosArgument.getBlockPos(context, "pos");
                            
                            // TODO: Implement structure spawning at specific position
                            source.sendSuccess(() -> Component.literal(
                                String.format("Spawning %s at %s", type, pos.toShortString()))
                                .withStyle(ChatFormatting.GREEN), true);
                            
                            return 1;
                        })
                    )
                )
            );
    }
}