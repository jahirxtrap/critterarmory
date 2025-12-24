package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.ChickenArmorLayer;
import com.jahirtrap.critterarmory.util.RenderStates;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.world.entity.animal.chicken.Chicken;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenRenderer.class)
public abstract class ChickenRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        ChickenRenderer renderer = (ChickenRenderer) (Object) this;
        renderer.addLayer(new ChickenArmorLayer(renderer, context.getModelSet()));
    }

    @Inject(method = "createRenderState*", at = @At("RETURN"), cancellable = true)
    private void createRenderState(CallbackInfoReturnable<ChickenRenderState> cir) {
        cir.setReturnValue(new RenderStates.Chicken());
    }

    @Inject(method = "extractRenderState*", at = @At("HEAD"))
    private void extractRenderState(Chicken entity, ChickenRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof RenderStates.Chicken armorRenderState)
            armorRenderState.bodyArmorItem = entity.getBodyArmorItem();
    }
}
