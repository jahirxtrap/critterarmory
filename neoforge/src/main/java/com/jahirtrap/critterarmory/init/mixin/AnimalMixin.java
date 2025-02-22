package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModContent;
import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.jahirtrap.critterarmory.util.CommonUtils.canWearArmor;

@Mixin(Animal.class)
public abstract class AnimalMixin {

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        var entity = (Animal) (Object) this;
        if (canWearArmor(entity)) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(ModContent.BALANCED_FEED.get()) && entity.getHealth() < entity.getMaxHealth()) {
                entity.usePlayerItem(player, hand, stack);
                entity.heal(ModConfig.healAmount);
                cir.setReturnValue(InteractionResult.SUCCESS);
            } else if (!entity.level().isClientSide() && stack.is(ModContent.VITALITY_FEED.get())) {
                int healthLimit = ModConfig.healthIncreaseLimit;
                if (entity.getMaxHealth() < healthLimit || entity.getHealth() < entity.getMaxHealth()) {
                    entity.usePlayerItem(player, hand, stack);
                    if (entity.getMaxHealth() < healthLimit) {
                        entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Math.min(entity.getMaxHealth() + ModConfig.healthIncreaseAmount, healthLimit));
                        entity.playSound(SoundEvents.PLAYER_LEVELUP);
                    }
                    entity.setHealth(entity.getMaxHealth());
                    cir.setReturnValue(InteractionResult.SUCCESS);
                }
            } else {
                if (isEquippableInSlot(stack, EquipmentSlot.BODY) && !entity.isWearingBodyArmor() && !entity.isBaby()) {
                    entity.setBodyArmorItem(stack.copyWithCount(1));
                    stack.consume(1, player);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                } else if (shearable() && (stack.getItem() instanceof ShearsItem || stack.canPerformAction(ToolActions.SHEARS_HARVEST)) && entity.getBodyArmorItem().getItem() instanceof BaseAnimalArmorItem.Modded && !(EnchantmentHelper.hasBindingCurse(entity.getBodyArmorItem()) && !player.isCreative())) {
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                    entity.playSound(SoundEvents.ARMOR_UNEQUIP_WOLF);
                    ItemStack armor = entity.getBodyArmorItem();
                    entity.setBodyArmorItem(ItemStack.EMPTY);
                    entity.spawnAtLocation(armor);
                    cir.setReturnValue(InteractionResult.SUCCESS);
                }
            }
        }
    }

    @Unique
    private boolean isEquippableInSlot(ItemStack stack, EquipmentSlot slot) {
        var entity = (Animal) (Object) this;
        if (entity.canUseSlot(slot) && stack.getItem() instanceof BaseAnimalArmorItem.Modded animalArmorItem)
            return animalArmorItem.getType().getSlot() == slot && animalArmorItem.getAllowedEntities().contains(entity.getType().builtInRegistryHolder());
        else return false;
    }

    @Unique
    private boolean shearable() {
        var entity = (Animal) (Object) this;
        if (entity instanceof Shearable shearable) return !shearable.readyForShearing();
        else return true;
    }
}
