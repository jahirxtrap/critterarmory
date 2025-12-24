package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.init.ModConfig;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.resources.Identifier;
import org.objectweb.asm.Opcodes;
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
    private static Identifier WOLF_COLLAR_LOCATION;

    @Redirect(method = "submit*", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/layers/WolfCollarLayer;WOLF_COLLAR_LOCATION:Lnet/minecraft/resources/Identifier;", opcode = Opcodes.GETSTATIC))
    private Identifier redirectWolfCollarTexture() {
        if (!ModConfig.modernWolfCollar)
            return Identifier.fromNamespaceAndPath(MODID, "textures/entity/wolf/wolf_collar.png");
        else return WOLF_COLLAR_LOCATION;
    }
}
