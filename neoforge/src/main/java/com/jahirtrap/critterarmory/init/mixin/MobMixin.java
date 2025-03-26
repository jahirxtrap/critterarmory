package com.jahirtrap.critterarmory.init.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.jahirtrap.critterarmory.util.CommonUtils.canWearArmor;

@Mixin(Mob.class)
public abstract class MobMixin {

    @Inject(method = "canUseSlot", at = @At("HEAD"), cancellable = true)
    public void canUseSlot(EquipmentSlot slot, CallbackInfoReturnable<Boolean> cir) {
        if (canWearArmor((Mob) (Object) this) && slot == EquipmentSlot.BODY) cir.setReturnValue(true);
    }
}
