package com.jahirtrap.critterarmory.init.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.jahirtrap.critterarmory.init.ModModelLayers.LAYERS;

@Mixin(LayerDefinitions.class)
public abstract class LayerDefinitionsMixin {

    @ModifyVariable(method = "createRoots", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"))
    private static ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> createRoots(ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder) {
        return builder.putAll(LAYERS);
    }
}
