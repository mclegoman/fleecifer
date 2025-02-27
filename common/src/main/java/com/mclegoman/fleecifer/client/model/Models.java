/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.client.model;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class Models {
	public static final ModelLayerLocation sheepEyes = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("fleecifer", "sheep"), "eyes");
	public static final ModelLayerLocation sheepEyesEmissive = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("fleecifer", "sheep"), "eyes_emissive");
	public static void init() {
		EntityModelLayerRegistry.registerModelLayer(sheepEyes, () -> getSheepTexturedModelData(new CubeDeformation(0.001F)));
		EntityModelLayerRegistry.registerModelLayer(sheepEyesEmissive, () -> getSheepTexturedModelData(new CubeDeformation(0.002F)));
	}
	public static LayerDefinition getSheepTexturedModelData(CubeDeformation dilation) {
		MeshDefinition modelData = QuadrupedModel.createBodyMesh(12, dilation);
		PartDefinition modelPartData = modelData.getRoot();
		modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 6.0F, -8.0F));
		modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 64, 32);
	}
}
