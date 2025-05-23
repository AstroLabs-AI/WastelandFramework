package com.astrolabs.wastelandframework.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ContaminatedWaterBottle extends Item {
    public ContaminatedWaterBottle() {
        super(new Item.Properties()
            .stacksTo(16)
            .craftRemainder(Items.GLASS_BOTTLE));
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }
        
        if (!level.isClientSide) {
            // Apply negative effects
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 1));
        }
        
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            stack.shrink(1);
            player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
        }
        
        return stack;
    }
    
    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }
    
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.wastelandframework.contaminated_water.warning")
            .withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("item.wastelandframework.contaminated_water.desc")
            .withStyle(ChatFormatting.GRAY));
    }
}