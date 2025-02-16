package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@OnlyIn(Dist.CLIENT)
public class SheepArmorLayer extends RenderLayer<SheepRenderState, SheepModel> {
    private final SheepModel adultModel;
    private final SheepModel babyModel;

    public SheepArmorLayer(RenderLayerParent<SheepRenderState, SheepModel> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new SheepModel(modelSet.bakeLayer(ModModelLayers.SHEEP_ARMOR));
        this.babyModel = new SheepModel(modelSet.bakeLayer(ModModelLayers.SHEEP_BABY_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, SheepRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Sheep armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.model().isPresent()) {
                SheepModel model = armorRenderState.isBaby ? this.babyModel : this.adultModel;
                model.setupAnim(armorRenderState);
                renderArmor(equippable.model().get(), model, stack, poseStack, bufferSource, i);
            }
        }
    }
}
