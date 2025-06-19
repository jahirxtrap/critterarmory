package com.jahirtrap.critterarmory.init.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.jahirtrap.critterarmory.util.CommonUtils.*;

@Mixin(Animal.class)
public abstract class AnimalMixin {

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        var entity = (Animal) (Object) this;
        var stack = player.getItemInHand(hand);
        if (canFeed(entity) && tamable(player) && feedEntity(player, hand, entity))
            cir.setReturnValue(InteractionResult.SUCCESS_SERVER);

        if (canWearArmor(entity)) {
            if (entity.isEquippableInSlot(stack, EquipmentSlot.BODY) && !entity.isWearingBodyArmor() && !entity.isBaby()) {
                entity.setBodyArmorItem(stack.copyWithCount(1));
                stack.consume(1, player);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

    @Unique
    private boolean tamable(Player player) {
        return !((Animal) (Object) this instanceof TamableAnimal tamable) || tamable.isTame() && tamable.isOwnedBy(player);
    }
}
