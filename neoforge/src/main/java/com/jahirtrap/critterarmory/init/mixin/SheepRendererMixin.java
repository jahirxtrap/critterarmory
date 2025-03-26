package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.SheepArmorLayer;
import com.jahirtrap.critterarmory.util.RenderStates;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.world.entity.animal.sheep.Sheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepRenderer.class)
public abstract class SheepRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        SheepRenderer renderer = (SheepRenderer) (Object) this;
        renderer.addLayer(new SheepArmorLayer(renderer, context.getModelSet()));
    }

    @Inject(method = "createRenderState*", at = @At("RETURN"), cancellable = true)
    private void createRenderState(CallbackInfoReturnable<SheepRenderState> cir) {
        cir.setReturnValue(new RenderStates.Sheep());
    }

    @Inject(method = "extractRenderState*", at = @At("HEAD"))
    private void extractRenderState(Sheep entity, SheepRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof RenderStates.Sheep armorRenderState)
            armorRenderState.bodyArmorItem = entity.getBodyArmorItem();
    }
}
