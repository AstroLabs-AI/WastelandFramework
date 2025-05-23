package com.astrolabs.wastelandframework.modules.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class WastelandBiomeModifier implements BiomeModifier {
    public static final Codec<WastelandBiomeModifier> CODEC = Codec.unit(WastelandBiomeModifier::new);
    
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.MODIFY) {
            // TODO: Implement biome modifications
            // - Change grass color
            // - Change foliage color
            // - Reduce mob spawns
            // - Add wasteland features
        }
    }
    
    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}