package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.world.entity.animal.MushroomCow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomCow.class)
public abstract class MushroomCowMixin {

    @Inject(method = "readyForShearing", at = @At("HEAD"), cancellable = true)
    public void readyForShearing(CallbackInfoReturnable<Boolean> cir) {
        var entity = (MushroomCow) (Object) this;
        if (entity.getBodyArmorItem().getItem() instanceof BaseAnimalArmorItem) cir.setReturnValue(false);
    }
}
