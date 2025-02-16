package com.jahirtrap.critterarmory.layer;

import com.jahirtrap.critterarmory.init.ModModelLayers;
import com.jahirtrap.critterarmory.util.RenderStates;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.jahirtrap.critterarmory.util.CommonUtils.renderArmor;

@OnlyIn(Dist.CLIENT)
public class CowArmorLayer extends RenderLayer<LivingEntityRenderState, CowModel> {
    private final CowModel adultModel;
    private final CowModel babyModel;

    public CowArmorLayer(RenderLayerParent<LivingEntityRenderState, CowModel> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.adultModel = new CowModel(modelSet.bakeLayer(ModModelLayers.COW_ARMOR));
        this.babyModel = new CowModel(modelSet.bakeLayer(ModModelLayers.COW_BABY_ARMOR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int i, LivingEntityRenderState renderState, float f, float g) {
        if (renderState instanceof RenderStates.Cow armorRenderState) {
            ItemStack stack = armorRenderState.bodyArmorItem;
            Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
            if (equippable != null && equippable.model().isPresent()) {
                CowModel model = armorRenderState.isBaby ? this.babyModel : this.adultModel;
                model.setupAnim(armorRenderState);
                renderArmor(equippable.model().get(), model, stack, poseStack, bufferSource, i);
            }
        }
    }
}
