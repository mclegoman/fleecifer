/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.loader_specific.client;

import com.mclegoman.perspective.client.entity.TexturedEntity;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.quiltmc.loader.api.QuiltLoader;

public class PerspectiveCompat {
	public static ResourceLocation getPerspectiveTexturedEntitySheepEyesTexture(LivingEntityRenderState livingEntity, String entity_type, String suffix, ResourceLocation fallback) {
		return QuiltLoader.isModLoaded("perspective") ? TexturedEntity.getTexture(livingEntity, entity_type, suffix, fallback) : fallback;
	}
}
