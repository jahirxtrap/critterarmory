package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Wolf.class)
public abstract class WolfMixin {
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        var entity = (Wolf) (Object) this;
        var stack = player.getItemInHand(hand);
        if (entity.isTame() && isBodyArmorItem(stack) && entity.isOwnedBy(player) && !entity.isWearingBodyArmor() && !entity.isBaby()) {
            entity.setBodyArmorItem(stack.copyWithCount(1));
            stack.consume(1, player);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    @Inject(method = "hasArmor", at = @At("HEAD"), cancellable = true)
    public void hasArmor(CallbackInfoReturnable<Boolean> cir) {
        var entity = (Wolf) (Object) this;
        if (isBodyArmorItem(entity.getBodyArmorItem())) cir.setReturnValue(true);
    }

    @Unique
    public boolean isBodyArmorItem(ItemStack stack) {
        if (stack.getItem() instanceof BaseAnimalArmorItem.Vanilla animalArmorItem)
            return animalArmorItem.getBodyType() == AnimalArmorItem.BodyType.CANINE;
        else return false;
    }
}
