package com.jahirtrap.critterarmory.init;

import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.FelineModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModLayerDefinitions {
    private static final CubeDeformation cubeDeformation = new CubeDeformation(0.2f);

    public static LayerDefinition CatArmor() {
        return LayerDefinition.create(FelineModel.createBodyMesh(cubeDeformation), 64, 32).apply(CatModel.CAT_TRANSFORMER);
    }

    public static LayerDefinition ChickenArmor() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2, -6, -2, 4, 6, 3, cubeDeformation), PartPose.offset(0, 15, -4));
        partDefinition2.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2, -4, -4, 4, 2, 2, cubeDeformation), PartPose.ZERO);
        partDefinition2.addOrReplaceChild("red_thing", CubeListBuilder.create().texOffs(14, 4).addBox(-1, -2, -3, 2, 2, 2, cubeDeformation), PartPose.ZERO);
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-3, -4, -3, 6, 8, 6, cubeDeformation), PartPose.offsetAndRotation(0, 16, 0, ((float) Math.PI / 2), 0, 0));
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().texOffs(26, 0).addBox(-1, 0, -3, 3, 5, 3, cubeDeformation);
        partDefinition.addOrReplaceChild("right_leg", cubeListBuilder, PartPose.offset(-2, 19, 1));
        partDefinition.addOrReplaceChild("left_leg", cubeListBuilder, PartPose.offset(1, 19, 1));
        partDefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(24, 13).addBox(0, 0, -3, 1, 4, 6), PartPose.offset(-4, 13, 0));
        partDefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(24, 13).addBox(-1, 0, -3, 1, 4, 6), PartPose.offset(4, 13, 0));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition CowArmor() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4, -4, -6, 8, 8, 6, cubeDeformation).texOffs(1, 33).addBox(-3, 1, -7, 6, 3, 1, cubeDeformation).texOffs(22, 0).addBox("right_horn", -5, -5, -5, 1, 3, 1, cubeDeformation).texOffs(22, 0).addBox("left_horn", 4, -5, -5, 1, 3, 1, cubeDeformation), PartPose.offset(0, 4, -8));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 4).addBox(-6, -10, -7, 12, 18, 10, cubeDeformation).texOffs(52, 0).addBox(-2, 2, -8, 4, 6, 1, cubeDeformation), PartPose.offsetAndRotation(0, 5, 2, ((float) Math.PI / 2), 0, 0));
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().mirror().texOffs(0, 16).addBox(-2, 0, -2, 4, 12, 4, cubeDeformation);
        CubeListBuilder cubeListBuilder2 = CubeListBuilder.create().texOffs(0, 16).addBox(-2, 0, -2, 4, 12, 4, cubeDeformation);
        partDefinition.addOrReplaceChild("right_hind_leg", cubeListBuilder2, PartPose.offset(-4, 12, 7));
        partDefinition.addOrReplaceChild("left_hind_leg", cubeListBuilder, PartPose.offset(4, 12, 7));
        partDefinition.addOrReplaceChild("right_front_leg", cubeListBuilder2, PartPose.offset(-4, 12, -5));
        partDefinition.addOrReplaceChild("left_front_leg", cubeListBuilder, PartPose.offset(4, 12, -5));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public static LayerDefinition PigArmor() {
        return PigModel.createBodyLayer(cubeDeformation);
    }

    public static LayerDefinition SheepArmor() {
        MeshDefinition meshDefinition = QuadrupedModel.createBodyMesh(12, false, true, cubeDeformation);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3, -4, -6, 6, 6, 8, cubeDeformation), PartPose.offset(0, 6, -8));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4, -10, -7, 8, 16, 6, cubeDeformation), PartPose.offsetAndRotation(0, 5, 2, ((float) Math.PI / 2), 0, 0));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }
}
