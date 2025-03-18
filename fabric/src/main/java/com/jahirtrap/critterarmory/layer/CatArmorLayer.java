package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.ItemStack;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@Environment(EnvType.CLIENT)
public class CatArmorLayer extends RenderLayer<Cat, CatModel<Cat>> {
    private final CatModel<Cat> model;

    public CatArmorLayer(RenderLayerParent<Cat, CatModel<Cat>> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.model = new CatModel<>(modelSet.bakeLayer(ModModelLayers.CAT_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, Cat entity, float f, float g, float h, float j, float k, float l) {
        ItemStack stack = entity.getBodyArmorItem();
        if (stack.getItem() instanceof BaseAnimalArmorItem.Modded animalArmorItem) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entity, f, g, h);
            this.model.setupAnim(entity, f, g, j, k, l);
            renderArmor(new ResourceLocation(animalArmorItem.getMaterial().getRegisteredName()), model, stack, poseStack, bufferSource, i);
        }
    }
}
