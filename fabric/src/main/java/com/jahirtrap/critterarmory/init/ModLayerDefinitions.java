package com.jahirtrap.critterarmory.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.CatModel;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

@Environment(EnvType.CLIENT)
public class ModLayerDefinitions {
    private static final CubeDeformation cubeDeformation = new CubeDeformation(0.2f);

    public static LayerDefinition WolfArmor() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition partDefinition2 = partDefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1, 13.5F, -7));
        partDefinition2.addOrReplaceChild("real_head", CubeListBuilder.create().texOffs(0, 0).addBox(-2, -3, -2, 6, 6, 4, cubeDeformation).texOffs(16, 14).addBox(-2, -5, 0, 2, 2, 1, cubeDeformation).texOffs(16, 14).addBox(2, -5, 0, 2, 2, 1, cubeDeformation).texOffs(0, 10).addBox(-0.5F, -0.001F, -5, 3, 3, 4, cubeDeformation), PartPose.ZERO);
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 14).addBox(-3, -2, -3, 6, 9, 6, cubeDeformation), PartPose.offsetAndRotation(0, 14, 2, ((float) Math.PI / 2), 0, 0));
        partDefinition.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(21, 0).addBox(-3, -3, -3, 8, 6, 7, cubeDeformation), PartPose.offsetAndRotation(-1, 14, -3, ((float) Math.PI / 2), 0, 0));
        CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 18).addBox(0, 0, -1, 2, 8, 2, cubeDeformation);
        partDefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-2.5F, 16, 7));
        partDefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(0.5F, 16, 7));
        partDefinition.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-2.5F, 16, -4));
        partDefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(0.5F, 16, -4));
        PartDefinition partdefinition3 = partDefinition.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(-1, 12, 8, ((float) Math.PI / 5), 0, 0));
        partdefinition3.addOrReplaceChild("real_tail", CubeListBuilder.create().texOffs(9, 18).addBox(0, 0, -1, 2, 8, 2, cubeDeformation), PartPose.ZERO);
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition CatArmor() {
        return LayerDefinition.create(CatModel.createBodyMesh(cubeDeformation), 64, 32);
    }

    public static LayerDefinition ChickenArmor() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2, -6, -2, 4, 6, 3, cubeDeformation), PartPose.offset(0, 15, -4));
        partDefinition.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(14, 0).addBox(-2, -4, -4, 4, 2, 2, cubeDeformation), PartPose.offset(0, 15, -4));
        partDefinition.addOrReplaceChild("red_thing", CubeListBuilder.create().texOffs(14, 4).addBox(-1, -2, -3, 2, 2, 2, cubeDeformation), PartPose.offset(0, 15, -4));
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
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4, -4, -6, 8, 8, 6, cubeDeformation).texOffs(22, 0).addBox("right_horn", -5, -5, -4, 1, 3, 1, cubeDeformation).texOffs(22, 0).addBox("left_horn", 4, -5, -4, 1, 3, 1, cubeDeformation), PartPose.offset(0, 4, -8));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 4).addBox(-6, -10, -7, 12, 18, 10, cubeDeformation).texOffs(52, 0).addBox(-2, 2, -8, 4, 6, 1, cubeDeformation), PartPose.offsetAndRotation(0, 5, 2, ((float) Math.PI / 2), 0, 0));
        CubeListBuilder cubeListBuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2, 0, -2, 4, 12, 4, cubeDeformation);
        partDefinition.addOrReplaceChild("right_hind_leg", cubeListBuilder, PartPose.offset(-4, 12, 7));
        partDefinition.addOrReplaceChild("left_hind_leg", cubeListBuilder, PartPose.offset(4, 12, 7));
        partDefinition.addOrReplaceChild("right_front_leg", cubeListBuilder, PartPose.offset(-4, 12, -6));
        partDefinition.addOrReplaceChild("left_front_leg", cubeListBuilder, PartPose.offset(4, 12, -6));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    public static LayerDefinition PigArmor() {
        return PigModel.createBodyLayer(cubeDeformation);
    }

    public static LayerDefinition SheepArmor() {
        MeshDefinition meshDefinition = QuadrupedModel.createBodyMesh(12, cubeDeformation);
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3, -4, -6, 6, 6, 8, cubeDeformation), PartPose.offset(0, 6, -8));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4, -10, -7, 8, 16, 6, cubeDeformation), PartPose.offsetAndRotation(0, 5, 2, ((float) Math.PI / 2), 0, 0));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }
}
