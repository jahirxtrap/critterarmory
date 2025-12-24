package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.PigArmorLayer;
import com.jahirtrap.critterarmory.util.RenderStates;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.world.entity.animal.pig.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PigRenderer.class)
public abstract class PigRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        PigRenderer renderer = (PigRenderer) (Object) this;
        renderer.addLayer(new PigArmorLayer(renderer, context.getModelSet()));
    }

    @Inject(method = "createRenderState*", at = @At("RETURN"), cancellable = true)
    private void createRenderState(CallbackInfoReturnable<PigRenderState> cir) {
        cir.setReturnValue(new RenderStates.Pig());
    }

    @Inject(method = "extractRenderState*", at = @At("HEAD"))
    private void extractRenderState(Pig entity, PigRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof RenderStates.Pig armorRenderState)
            armorRenderState.bodyArmorItem = entity.getBodyArmorItem();
    }
}
