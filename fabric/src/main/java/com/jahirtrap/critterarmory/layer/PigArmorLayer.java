package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PigRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@Environment(EnvType.CLIENT)
public class PigArmorLayer extends RenderLayer<PigRenderState, PigModel> {
    private final PigModel adultModel;
    private final PigModel babyModel;

    public PigArmorLayer(RenderLayerParent<PigRenderState, PigModel> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new PigModel(modelSet.bakeLayer(ModModelLayers.PIG_ARMOR));
        this.babyModel = new PigModel(modelSet.bakeLayer(ModModelLayers.PIG_BABY_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, PigRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Pig armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.assetId().isPresent()) {
                PigModel model = armorRenderState.isBaby ? this.babyModel : this.adultModel;
                model.setupAnim(armorRenderState);
                renderArmor(equippable.assetId().get(), model, stack, poseStack, bufferSource, i);
            }
        }
    }
}
