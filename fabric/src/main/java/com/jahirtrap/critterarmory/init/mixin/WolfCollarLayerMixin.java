package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.init.ModConfig;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

@Mixin(WolfCollarLayer.class)
public abstract class WolfCollarLayerMixin {

    @Final
    @Shadow
    private static ResourceLocation WOLF_COLLAR_LOCATION;

    @Redirect(method = "render*", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/layers/WolfCollarLayer;WOLF_COLLAR_LOCATION:Lnet/minecraft/resources/ResourceLocation;"))
    private ResourceLocation redirectWolfCollarTexture() {
        if (!ModConfig.modernWolfCollar)
            return ResourceLocation.fromNamespaceAndPath(MODID, "textures/entity/wolf/wolf_collar.png");
        else return WOLF_COLLAR_LOCATION;
    }
}
