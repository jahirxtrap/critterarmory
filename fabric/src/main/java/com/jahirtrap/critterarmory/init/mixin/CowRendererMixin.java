package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.CowArmorLayer;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CowRenderer.class)
public abstract class CowRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        CowRenderer renderer = (CowRenderer) (Object) this;
        renderer.addLayer(new CowArmorLayer(renderer, context.getModelSet()));
    }
}
