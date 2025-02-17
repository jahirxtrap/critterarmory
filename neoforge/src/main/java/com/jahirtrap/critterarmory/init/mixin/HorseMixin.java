package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(Horse.class)
public abstract class HorseMixin {

    @Unique
    private static final UUID ARMOR_TOUGHNESS_MODIFIER_UUID = UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC");
    @Unique
    private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_UUID = UUID.fromString("7107DE5E-7CE8-4030-940E-514C1F160890");

    @Inject(method = "setArmorEquipment", at = @At("TAIL"))
    private void setArmorEquipment(ItemStack stack, CallbackInfo ci) {
        var entity = (Horse) (Object) this;
        if (!entity.level().isClientSide()) {
            entity.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(ARMOR_TOUGHNESS_MODIFIER_UUID);
            entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE).removeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID);
            if (stack.getItem() instanceof BaseAnimalArmorItem.Vanilla animalArmorItem) {
                if (animalArmorItem.getMaterial().getToughness() != 0)
                    entity.getAttribute(Attributes.ARMOR_TOUGHNESS).addTransientModifier(new AttributeModifier(ARMOR_TOUGHNESS_MODIFIER_UUID, "Animal toughness", animalArmorItem.getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
                if (animalArmorItem.getMaterial().getKnockbackResistance() != 0)
                    entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE).addTransientModifier(new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "Animal knockback resistance", animalArmorItem.getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
            }
        }
    }
}
