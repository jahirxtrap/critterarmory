package com.jahirtrap.critterarmory.init;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {
    public static final Map<ModelLayerLocation, LayerDefinition> LAYERS = new HashMap<>();

    public static final ModelLayerLocation CAT_ARMOR = register("cat_armor", ModLayerDefinitions.CatArmor());
    public static final ModelLayerLocation CHICKEN_ARMOR = register("chicken_armor", ModLayerDefinitions.ChickenArmor());
    public static final ModelLayerLocation COW_ARMOR = register("cow_armor", ModLayerDefinitions.CowArmor());
    public static final ModelLayerLocation PIG_ARMOR = register("pig_armor", ModLayerDefinitions.PigArmor());
    public static final ModelLayerLocation SHEEP_ARMOR = register("sheep_armor", ModLayerDefinitions.SheepArmor());

    private static ModelLayerLocation register(String name, LayerDefinition layerDefinition) {
        ModelLayerLocation layerLocation = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, name), ModelLayers.DEFAULT_LAYER);
        ModelLayers.ALL_MODELS.add(layerLocation);
        LAYERS.put(layerLocation, layerDefinition);
        return layerLocation;
    }
}
