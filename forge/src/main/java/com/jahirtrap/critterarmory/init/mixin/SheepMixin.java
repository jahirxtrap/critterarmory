package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public abstract class SheepMixin extends Animal {

    protected SheepMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        var entity = (Sheep) (Object) this;
        var stack = player.getItemInHand(hand);
        if ((stack.getItem() instanceof ShearsItem || stack.canPerformAction(ToolActions.SHEARS_HARVEST)) && entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof BaseAnimalArmorItem.Modded)
            cir.setReturnValue(super.mobInteract(player, hand));
    }
}
