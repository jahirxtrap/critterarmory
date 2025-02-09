package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@OnlyIn(Dist.CLIENT)
public class ChickenArmorLayer extends RenderLayer<ChickenRenderState, ChickenModel> {
    private final ChickenModel adultModel;
    private final ChickenModel babyModel;

    public ChickenArmorLayer(RenderLayerParent<ChickenRenderState, ChickenModel> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new ChickenModel(modelSet.bakeLayer(ModModelLayers.CHICKEN_ARMOR));
        this.babyModel = new ChickenModel(modelSet.bakeLayer(ModModelLayers.CHICKEN_BABY_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, ChickenRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Chicken armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && !equippable.assetId().isEmpty()) {
                ChickenModel model = armorRenderState.isBaby ? this.babyModel : this.adultModel;
                model.setupAnim(armorRenderState);
                renderArmor(equippable.assetId().get(), model, stack, poseStack, bufferSource, i);
            }
        }
    }
}
