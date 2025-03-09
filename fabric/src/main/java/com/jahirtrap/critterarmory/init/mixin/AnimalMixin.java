package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

import static com.jahirtrap.critterarmory.util.CommonUtils.*;

@Mixin(Animal.class)
public abstract class AnimalMixin {

    @Unique
    private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    @Unique
    private static final UUID ARMOR_TOUGHNESS_MODIFIER_UUID = UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC");
    @Unique
    private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_UUID = UUID.fromString("7107DE5E-7CE8-4030-940E-514C1F160890");

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        var entity = (Animal) (Object) this;
        var stack = player.getItemInHand(hand);
        if (canFeed(entity) && tamable(player) && feedEntity(player, hand, entity))
            cir.setReturnValue(InteractionResult.SUCCESS);

        if (canWearArmor(entity)) {
            if (isEquippable(stack) && entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && !entity.isBaby()) {
                ItemStack armor = stack.copy();
                armor.setCount(1);
                setArmorEquipment(armor);
                if (stack.getItem() instanceof BaseAnimalArmorItem.Modded animalArmorItem)
                    entity.playSound(animalArmorItem.getMaterial().getEquipSound(), 1, 1);
                if (!player.getAbilities().instabuild) stack.shrink(1);
                cir.setReturnValue(InteractionResult.SUCCESS);
            } else if (shearable() && stack.getItem() instanceof ShearsItem && entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof BaseAnimalArmorItem.Modded && !(EnchantmentHelper.hasBindingCurse(entity.getItemBySlot(EquipmentSlot.CHEST)) && !player.isCreative())) {
                stack.hurtAndBreak(1, player, a -> a.broadcastBreakEvent(hand));
                entity.playSound(SoundEvents.SHEEP_SHEAR, 1, 1);
                ItemStack armor = entity.getItemBySlot(EquipmentSlot.CHEST);
                setArmorEquipment(ItemStack.EMPTY);
                entity.spawnAtLocation(armor);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

    @Unique
    private boolean isEquippable(ItemStack stack) {
        var entity = (Animal) (Object) this;
        return stack.getItem() instanceof BaseAnimalArmorItem.Modded animalArmorItem && animalArmorItem.getAllowedEntities().contains(entity.getType().builtInRegistryHolder());
    }

    @Unique
    private boolean shearable() {
        return !((Animal) (Object) this instanceof Shearable shearable) || !shearable.readyForShearing();
    }

    @Unique
    private boolean tamable(Player player) {
        return !((Animal) (Object) this instanceof TamableAnimal tamable) || tamable.isTame() && tamable.isOwnedBy(player);
    }

    @Unique
    private void setArmorEquipment(ItemStack stack) {
        var entity = (Animal) (Object) this;
        if (!entity.getLevel().isClientSide()) {
            entity.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
            entity.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            entity.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(ARMOR_TOUGHNESS_MODIFIER_UUID);
            entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID);
            entity.setDropChance(EquipmentSlot.CHEST, 0);
            if (stack.getItem() instanceof BaseAnimalArmorItem.Modded animalArmorItem) {
                entity.setItemSlot(EquipmentSlot.CHEST, stack);
                if (animalArmorItem.getMaterial().getDefense() != 0)
                    entity.getAttribute(Attributes.ARMOR).addPermanentModifier(new AttributeModifier(ARMOR_MODIFIER_UUID, "Animal armor bonus", animalArmorItem.getMaterial().getDefense(), AttributeModifier.Operation.ADDITION));
                if (animalArmorItem.getMaterial().getToughness() != 0)
                    entity.getAttribute(Attributes.ARMOR_TOUGHNESS).addPermanentModifier(new AttributeModifier(ARMOR_TOUGHNESS_MODIFIER_UUID, "Animal toughness", animalArmorItem.getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
                if (animalArmorItem.getMaterial().getKnockbackResistance() != 0)
                    entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addPermanentModifier(new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "Animal knockback resistance", animalArmorItem.getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
                entity.setGuaranteedDrop(EquipmentSlot.CHEST);
            }
        }
    }
}
