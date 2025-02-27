/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.client.util;

import com.mclegoman.perspective.client.entity.TexturedEntity;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class PerspectiveCompat {
	public static ResourceLocation getPerspectiveTexturedEntitySheepEyesTexture(LivingEntityRenderState livingEntity, String entity_type, String suffix, ResourceLocation fallback) {
		return FabricLoader.getInstance().isModLoaded("perspective") ? TexturedEntity.getTexture(livingEntity, entity_type, suffix, fallback) : fallback;
	}
}
