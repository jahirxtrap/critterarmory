package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@Environment(EnvType.CLIENT)
public class ChickenArmorLayer extends RenderLayer<ChickenRenderState, ChickenModel> {
    private final ChickenModel adultModel;
    private final ChickenModel babyModel;

    public ChickenArmorLayer(RenderLayerParent<ChickenRenderState, ChickenModel> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new ChickenModel(modelSet.bakeLayer(ModModelLayers.CHICKEN_ARMOR));
        this.babyModel = new ChickenModel(modelSet.bakeLayer(ModModelLayers.CHICKEN_BABY_ARMOR));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int i, ChickenRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Chicken armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.assetId().isPresent()) {
                ChickenModel model = armorRenderState.isBaby ? this.babyModel : this.adultModel;
                renderArmor(equippable.assetId().get(), model, armorRenderState, stack, poseStack, collector, i);
            }
        }
    }
}
