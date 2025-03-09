package com.jahirtrap.critterarmory.init.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.jahirtrap.critterarmory.util.CommonUtils.feedEntity;

@Mixin(Horse.class)
public abstract class HorseMixin {

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        var entity = (Horse) (Object) this;
        if (entity.getType() == EntityType.HORSE && !entity.isVehicle() && entity.isTamed() && !player.isSecondaryUseActive() && feedEntity(player, hand, entity))
            cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
    }
}
