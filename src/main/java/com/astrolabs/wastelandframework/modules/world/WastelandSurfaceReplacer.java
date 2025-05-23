package com.astrolabs.wastelandframework.modules.world;

import com.astrolabs.wastelandframework.WastelandFramework;
import com.astrolabs.wastelandframework.core.config.WastelandConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Random;

public class WastelandSurfaceReplacer {
    private final WorldModule worldModule;
    private final Random random = new Random();
    
    public WastelandSurfaceReplacer(WorldModule worldModule) {
        this.worldModule = worldModule;
    }
    
    public void processSurface(WorldGenRegion region, ChunkAccess chunk) {
        if (!WastelandConfig.WORLD.replaceSurfaceBlocks.get()) {
            return;
        }
        
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        int chunkX = chunk.getPos().x << 4;
        int chunkZ = chunk.getPos().z << 4;
        
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkX + x;
                int worldZ = chunkZ + z;
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
                
                mutablePos.set(worldX, y, worldZ);
                
                // Process surface blocks
                for (int depth = 0; depth < 5; depth++) {
                    if (y - depth < region.getMinBuildHeight()) break;
                    
                    mutablePos.setY(y - depth);
                    BlockState currentState = chunk.getBlockState(mutablePos);
                    Block currentBlock = currentState.getBlock();
                    
                    // Replace surface blocks
                    BlockState replacement = getReplacementBlock(currentBlock, depth);
                    if (replacement != null && replacement != currentState) {
                        chunk.setBlockState(mutablePos, replacement, false);
                    }
                }
                
                // Remove vegetation
                if (WastelandConfig.WORLD.removeVegetation.get()) {
                    removeVegetation(chunk, mutablePos, worldX, y + 1, worldZ);
                }
                
                // Add ash layers
                if (WastelandConfig.WORLD.ashLayerChance.get() > 0 && 
                    random.nextDouble() < WastelandConfig.WORLD.ashLayerChance.get()) {
                    addAshLayer(chunk, mutablePos, worldX, y + 1, worldZ);
                }
            }
        }
    }
    
    private BlockState getReplacementBlock(Block block, int depth) {
        if (depth > 0) {
            // Subsurface blocks typically become coarse dirt or gravel
            if (block == Blocks.DIRT || block == Blocks.GRASS_BLOCK) {
                return Blocks.COARSE_DIRT.defaultBlockState();
            }
            return null;
        }
        
        // Surface replacements
        Block replacement = worldModule.getRandomReplacement(block);
        return replacement != block ? replacement.defaultBlockState() : null;
    }
    
    private void removeVegetation(ChunkAccess chunk, BlockPos.MutableBlockPos pos, int x, int y, int z) {
        pos.set(x, y, z);
        BlockState state = chunk.getBlockState(pos);
        Block block = state.getBlock();
        
        // Remove various vegetation
        if (block == Blocks.GRASS || 
            block == Blocks.TALL_GRASS || 
            block == Blocks.FERN || 
            block == Blocks.LARGE_FERN ||
            block == Blocks.DANDELION ||
            block == Blocks.POPPY ||
            block == Blocks.BLUE_ORCHID ||
            block == Blocks.ALLIUM ||
            block == Blocks.AZURE_BLUET ||
            block == Blocks.RED_TULIP ||
            block == Blocks.ORANGE_TULIP ||
            block == Blocks.WHITE_TULIP ||
            block == Blocks.PINK_TULIP ||
            block == Blocks.OXEYE_DAISY ||
            block == Blocks.CORNFLOWER ||
            block == Blocks.LILY_OF_THE_VALLEY ||
            block == Blocks.SUNFLOWER ||
            block == Blocks.LILAC ||
            block == Blocks.ROSE_BUSH ||
            block == Blocks.PEONY) {
            
            chunk.setBlockState(pos, Blocks.AIR.defaultBlockState(), false);
        }
    }
    
    private void addAshLayer(ChunkAccess chunk, BlockPos.MutableBlockPos pos, int x, int y, int z) {
        pos.set(x, y, z);
        BlockState state = chunk.getBlockState(pos);
        
        // Only place ash on solid blocks
        if (state.isAir() && chunk.getBlockState(pos.below()).isSolidRender(chunk, pos.below())) {
            // Use snow layers as ash for now
            chunk.setBlockState(pos, Blocks.SNOW.defaultBlockState(), false);
        }
    }
}