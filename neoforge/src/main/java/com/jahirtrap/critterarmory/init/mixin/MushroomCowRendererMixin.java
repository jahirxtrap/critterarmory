package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.MushroomCowArmorLayer;
import com.jahirtrap.critterarmory.util.RenderStates;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MushroomCowRenderer;
import net.minecraft.client.renderer.entity.state.MushroomCowRenderState;
import net.minecraft.world.entity.animal.MushroomCow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomCowRenderer.class)
public abstract class MushroomCowRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        MushroomCowRenderer renderer = (MushroomCowRenderer) (Object) this;
        renderer.addLayer(new MushroomCowArmorLayer(renderer, context.getModelSet()));
    }

    @Inject(method = "createRenderState*", at = @At("RETURN"), cancellable = true)
    private void createRenderState(CallbackInfoReturnable<MushroomCowRenderState> cir) {
        cir.setReturnValue(new RenderStates.MushroomCow());
    }

    @Inject(method = "extractRenderState*", at = @At("HEAD"))
    private void extractRenderState(MushroomCow entity, MushroomCowRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof RenderStates.MushroomCow armorRenderState)
            armorRenderState.bodyArmorItem = entity.getBodyArmorItem();
    }
}
