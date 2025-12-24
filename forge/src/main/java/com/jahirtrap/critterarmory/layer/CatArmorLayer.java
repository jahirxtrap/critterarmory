package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.feline.CatModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@OnlyIn(Dist.CLIENT)
public class CatArmorLayer extends RenderLayer<CatRenderState, CatModel> {
    private final CatModel adultModel;
    private final CatModel babyModel;

    public CatArmorLayer(RenderLayerParent<CatRenderState, CatModel> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new CatModel(modelSet.bakeLayer(ModModelLayers.CAT_ARMOR));
        this.babyModel = new CatModel(modelSet.bakeLayer(ModModelLayers.CAT_BABY_ARMOR));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int i, CatRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Cat armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.assetId().isPresent()) {
                CatModel model = armorRenderState.isBaby ? this.babyModel : this.adultModel;
                renderArmor(equippable.assetId().get(), model, armorRenderState, stack, poseStack, collector, i);
            }
        }
    }
}
