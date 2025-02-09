package com.jahirtrap.critterarmory.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

@Environment(EnvType.CLIENT)
public class ModModelLayers {
    public static final Map<ModelLayerLocation, LayerDefinition> LAYERS = new HashMap<>();

    public static final ModelLayerLocation CHICKEN_ARMOR = register("chicken_armor", ModLayerDefinitions.ChickenArmor());
    public static final ModelLayerLocation CHICKEN_BABY_ARMOR = register("chicken_baby_armor", ModLayerDefinitions.ChickenArmor().apply(ChickenModel.BABY_TRANSFORMER));
    public static final ModelLayerLocation COW_ARMOR = register("cow_armor", ModLayerDefinitions.CowArmor());
    public static final ModelLayerLocation COW_BABY_ARMOR = register("cow_baby_armor", ModLayerDefinitions.CowArmor().apply(CowModel.BABY_TRANSFORMER));
    public static final ModelLayerLocation PIG_ARMOR = register("pig_armor", ModLayerDefinitions.PigArmor());
    public static final ModelLayerLocation PIG_BABY_ARMOR = register("pig_baby_armor", ModLayerDefinitions.PigArmor().apply(PigModel.BABY_TRANSFORMER));
    public static final ModelLayerLocation SHEEP_ARMOR = register("sheep_armor", ModLayerDefinitions.SheepArmor());
    public static final ModelLayerLocation SHEEP_BABY_ARMOR = register("sheep_baby_armor", ModLayerDefinitions.SheepArmor().apply(SheepModel.BABY_TRANSFORMER));

    private static ModelLayerLocation register(String name, LayerDefinition layerDefinition) {
        ModelLayerLocation layerLocation = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MODID, name), ModelLayers.DEFAULT_LAYER);
        ModelLayers.ALL_MODELS.add(layerLocation);
        LAYERS.put(layerLocation, layerDefinition);
        return layerLocation;
    }
}
