/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.client.model;

import com.mclegoman.fleecifer.mixin.ModelLayersAccessor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.Objects;

public class EntityModelLayerRegistry {
	public static void registerModelLayer(ModelLayerLocation modelLayer, LayerDefinitionProvider provider) {
		Objects.requireNonNull(modelLayer, "ModelLayerLocation cannot be null");
		Objects.requireNonNull(provider, "LayerDefinition cannot be null");
		if (ModelLayerImpl.layers.putIfAbsent(modelLayer, provider) != null) throw new IllegalArgumentException("Model layer was already registered: " + modelLayer);
		else ModelLayersAccessor.fleecifer$getAllModels().add(modelLayer);
	}
	@FunctionalInterface
	public interface LayerDefinitionProvider {
		LayerDefinition createLayerDefinition();
	}
}

