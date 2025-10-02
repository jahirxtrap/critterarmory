package com.jahirtrap.critterarmory.init.mixin;

import com.jahirtrap.critterarmory.init.ModConfig;
import com.jahirtrap.critterarmory.init.ModTags;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EquipmentLayerRenderer.class)
public abstract class EquipmentLayerRendererMixin {

    @Inject(method = "renderLayers(Lnet/minecraft/client/resources/model/EquipmentClientInfo$LayerType;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/resources/ResourceLocation;II)V", at = @At("HEAD"), cancellable = true)
    public <S> void renderLayers(EquipmentClientInfo.LayerType type, ResourceKey<EquipmentAsset> resourceKey, Model<? super S> model, S object, ItemStack stack, PoseStack poseStack, SubmitNodeCollector collector, int i, ResourceLocation location, int j, int k, CallbackInfo ci) {
        if ((!ModConfig.renderArmors && (stack.is(ModTags.Items.HORSE_ARMOR) || stack.is(ModTags.Items.WOLF_ARMOR))) || (!ModConfig.renderHorseArmors && stack.is(ModTags.Items.HORSE_ARMOR)) || (!ModConfig.renderWolfArmors && stack.is(ModTags.Items.WOLF_ARMOR)))
            ci.cancel();
    }
}
