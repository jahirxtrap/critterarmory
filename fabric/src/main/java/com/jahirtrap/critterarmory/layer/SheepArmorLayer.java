package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.ItemStack;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@Environment(EnvType.CLIENT)
public class SheepArmorLayer extends RenderLayer<Sheep, SheepModel<Sheep>> {
    private final SheepModel<Sheep> model;

    public SheepArmorLayer(RenderLayerParent<Sheep, SheepModel<Sheep>> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.model = new SheepModel<>(modelSet.bakeLayer(ModModelLayers.SHEEP_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, Sheep entity, float f, float g, float h, float j, float k, float l) {
        ItemStack stack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (stack.getItem() instanceof BaseAnimalArmorItem.Modded animalArmorItem) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entity, f, g, h);
            this.model.setupAnim(entity, f, g, j, k, l);
            renderArmor(animalArmorItem.getMaterial().getLocation(), model, stack, poseStack, bufferSource, i);
        }
    }
}
