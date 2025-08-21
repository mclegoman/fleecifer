/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.client.model;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class Models {
	public static final ModelLayerLocation sheepEyes = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("fleecifer", "sheep"), "eyes");
	public static final ModelLayerLocation babySheepEyes = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("fleecifer", "sheep"), "baby_eyes");
	public static final ModelLayerLocation sheepEyesEmissive = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("fleecifer", "sheep"), "eyes_emissive");
	public static final ModelLayerLocation babySheepEyesEmissive = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("fleecifer", "sheep"), "baby_eyes_emissive");
	public static void init() {
		EntityModelLayerRegistry.registerModelLayer(sheepEyes, () -> getSheepTexturedModelData(new CubeDeformation(0.001F)));
		EntityModelLayerRegistry.registerModelLayer(sheepEyesEmissive, () -> getSheepTexturedModelData(new CubeDeformation(0.002F)));

		EntityModelLayerRegistry.registerModelLayer(babySheepEyes, () -> getSheepTexturedModelData(new CubeDeformation(0.001F)).apply(SheepModel.BABY_TRANSFORMER));
		EntityModelLayerRegistry.registerModelLayer(babySheepEyesEmissive, () -> getSheepTexturedModelData(new CubeDeformation(0.002F)).apply(SheepModel.BABY_TRANSFORMER));
	}
	public static LayerDefinition getSheepTexturedModelData(CubeDeformation deformation) {
		MeshDefinition meshDefinition = QuadrupedModel.createBodyMesh(12, false, true, deformation);
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 6.0F, -8.0F));
		partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
		return LayerDefinition.create(meshDefinition, 64, 32);
	}
}
