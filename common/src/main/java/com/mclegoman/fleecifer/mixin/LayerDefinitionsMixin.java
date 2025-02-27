/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import com.google.common.collect.ImmutableMap;
import com.mclegoman.fleecifer.client.model.EntityModelLayerRegistry;
import com.mclegoman.fleecifer.client.model.ModelLayerImpl;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(LayerDefinitions.class)
public abstract class LayerDefinitionsMixin {
	@Inject(method = "createRoots", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;", remap = false), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private static void fleecifer$registerModelData(CallbackInfoReturnable<Map<ModelLayerLocation, LayerDefinition>> cir, ImmutableMap.Builder builder, LayerDefinition layerDefinition, LayerDefinition layerDefinition2, LayerDefinition layerDefinition3, LayerDefinition layerDefinition4, LayerDefinition layerDefinition5, LayerDefinition layerDefinition6, LayerDefinition layerDefinition7, LayerDefinition layerDefinition8, MeshTransformer meshTransformer, LayerDefinition layerDefinition9, LayerDefinition layerDefinition10, LayerDefinition layerDefinition11, LayerDefinition layerDefinition12, LayerDefinition layerDefinition13, LayerDefinition layerDefinition14, LayerDefinition layerDefinition15, LayerDefinition layerDefinition16, LayerDefinition layerDefinition17, LayerDefinition layerDefinition18, LayerDefinition layerDefinition19, LayerDefinition layerDefinition20, LayerDefinition layerDefinition21, LayerDefinition layerDefinition22, LayerDefinition layerDefinition23, LayerDefinition layerDefinition24, LayerDefinition layerDefinition25, LayerDefinition layerDefinition26, LayerDefinition layerDefinition27, LayerDefinition layerDefinition28, LayerDefinition layerDefinition29, LayerDefinition layerDefinition30, LayerDefinition layerDefinition31, LayerDefinition layerDefinition32, LayerDefinition layerDefinition33, LayerDefinition layerDefinition34, LayerDefinition layerDefinition35, LayerDefinition layerDefinition36, LayerDefinition layerDefinition37, LayerDefinition layerDefinition38, LayerDefinition layerDefinition39, LayerDefinition layerDefinition40, LayerDefinition layerDefinition41, LayerDefinition layerDefinition42, LayerDefinition layerDefinition43, LayerDefinition layerDefinition44, LayerDefinition layerDefinition45, LayerDefinition layerDefinition46, LayerDefinition layerDefinition47, LayerDefinition layerDefinition48, LayerDefinition layerDefinition49, LayerDefinition layerDefinition50, LayerDefinition layerDefinition51, MeshTransformer meshTransformer2, MeshTransformer meshTransformer3, MeshTransformer meshTransformer4, MeshTransformer meshTransformer5, LayerDefinition layerDefinition52, LayerDefinition layerDefinition53, LayerDefinition layerDefinition54, LayerDefinition layerDefinition55) {
		for (Map.Entry<ModelLayerLocation, EntityModelLayerRegistry.LayerDefinitionProvider> entry : ModelLayerImpl.layers.entrySet()) {
			builder.put(entry.getKey(), entry.getValue().createLayerDefinition());
		}
	}
}
