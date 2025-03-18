package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.CatArmorLayer;
import com.jahirtrap.critterarmory.util.RenderStates;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatRenderer.class)
public abstract class CatRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        CatRenderer renderer = (CatRenderer) (Object) this;
        renderer.addLayer(new CatArmorLayer(renderer, context.getModelSet()));
    }

    @Inject(method = "createRenderState*", at = @At("RETURN"), cancellable = true)
    private void createRenderState(CallbackInfoReturnable<CatRenderState> cir) {
        cir.setReturnValue(new RenderStates.Cat());
    }

    @Inject(method = "extractRenderState*", at = @At("HEAD"))
    private void extractRenderState(Cat entity, CatRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof RenderStates.Cat armorRenderState)
            armorRenderState.bodyArmorItem = entity.getBodyArmorItem();
    }
}
