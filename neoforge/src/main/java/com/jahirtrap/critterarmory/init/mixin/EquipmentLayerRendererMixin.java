package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModTags;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EquipmentLayerRenderer.class)
public abstract class EquipmentLayerRendererMixin {

    @Inject(method = "renderLayers(Lnet/minecraft/world/item/equipment/EquipmentModel$LayerType;Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/model/Model;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), cancellable = true)
    public void renderLayers(EquipmentModel.LayerType layerType, ResourceLocation location, Model model, ItemStack stack, PoseStack poseStack, MultiBufferSource bufferSource, int i, CallbackInfo ci) {
        if ((!ModConfig.renderArmors && (stack.is(ModTags.Items.HORSE_ARMOR) || stack.is(ModTags.Items.WOLF_ARMOR))) || (!ModConfig.renderHorseArmors && stack.is(ModTags.Items.HORSE_ARMOR)) || (!ModConfig.renderWolfArmors && stack.is(ModTags.Items.WOLF_ARMOR)))
            ci.cancel();
    }
}
