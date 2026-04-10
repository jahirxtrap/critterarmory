package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CowRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@Environment(EnvType.CLIENT)
public class CowArmorLayer extends RenderLayer<CowRenderState, CowModel> {
    private final CowModel adultModel;

    public CowArmorLayer(RenderLayerParent<CowRenderState, CowModel> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new CowModel(modelSet.bakeLayer(ModModelLayers.COW_ARMOR));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int i, CowRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Cow armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.assetId().isPresent()) {
                renderArmor(equippable.assetId().get(), this.adultModel, armorRenderState, stack, poseStack, collector, i);
            }
        }
    }
}
