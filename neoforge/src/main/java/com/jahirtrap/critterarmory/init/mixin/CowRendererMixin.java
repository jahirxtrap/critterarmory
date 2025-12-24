package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.CowArmorLayer;
import com.jahirtrap.critterarmory.util.RenderStates;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CowRenderState;
import net.minecraft.world.entity.animal.cow.Cow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CowRenderer.class)
public abstract class CowRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        CowRenderer renderer = (CowRenderer) (Object) this;
        renderer.addLayer(new CowArmorLayer(renderer, context.getModelSet()));
    }

    @Inject(method = "createRenderState*", at = @At("RETURN"), cancellable = true)
    private void createRenderState(CallbackInfoReturnable<CowRenderState> cir) {
        cir.setReturnValue(new RenderStates.Cow());
    }

    @Inject(method = "extractRenderState*", at = @At("HEAD"))
    private void extractRenderState(Cow entity, CowRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof RenderStates.Cow armorRenderState)
            armorRenderState.bodyArmorItem = entity.getBodyArmorItem();
    }
}
