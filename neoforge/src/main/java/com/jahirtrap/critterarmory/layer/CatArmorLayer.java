package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.feline.AbstractFelineModel;
import net.minecraft.client.model.animal.feline.AdultCatModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@OnlyIn(Dist.CLIENT)
public class CatArmorLayer extends RenderLayer<CatRenderState, AbstractFelineModel<CatRenderState>> {
    private final AdultCatModel adultModel;

    public CatArmorLayer(RenderLayerParent<CatRenderState, AbstractFelineModel<CatRenderState>> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new AdultCatModel(modelSet.bakeLayer(ModModelLayers.CAT_ARMOR));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int i, CatRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Cat armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.assetId().isPresent()) {
                renderArmor(equippable.assetId().get(), this.adultModel, armorRenderState, stack, poseStack, collector, i);
            }
        }
    }
}
