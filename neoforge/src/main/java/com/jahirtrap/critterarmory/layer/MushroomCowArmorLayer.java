package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@OnlyIn(Dist.CLIENT)
public class MushroomCowArmorLayer extends RenderLayer<MushroomCow, CowModel<MushroomCow>> {
    private final CowModel<MushroomCow> model;

    public MushroomCowArmorLayer(RenderLayerParent<MushroomCow, CowModel<MushroomCow>> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.model = new CowModel<>(modelSet.bakeLayer(ModModelLayers.COW_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, MushroomCow entity, float f, float g, float h, float j, float k, float l) {
        ItemStack stack = entity.getBodyArmorItem();
        if (stack.getItem() instanceof BaseAnimalArmorItem.Modded animalArmorItem) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entity, f, g, h);
            this.model.setupAnim(entity, f, g, j, k, l);
            renderArmor(ResourceLocation.parse(animalArmorItem.getMaterial().getRegisteredName()), model, stack, poseStack, bufferSource, i);
        }
    }
}
