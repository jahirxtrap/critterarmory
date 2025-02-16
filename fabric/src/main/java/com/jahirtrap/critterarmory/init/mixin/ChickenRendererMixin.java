package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.layer.ChickenArmorLayer;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickenRenderer.class)
public abstract class ChickenRendererMixin {

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
    private void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        ChickenRenderer renderer = (ChickenRenderer) (Object) this;
        renderer.addLayer(new ChickenArmorLayer(renderer, context.getModelSet()));
    }
}
