/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import com.mclegoman.fleecifer.client.model.Models;
import com.mclegoman.fleecifer.client.renderer.SheepEyesOverlayFeatureRenderer;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepRenderer.class)
public abstract class SheepEntityRendererMixin extends LivingEntityRenderer<Sheep, SheepRenderState, SheepModel> {
	public SheepEntityRendererMixin(EntityRendererProvider.Context context, SheepModel entityModel, float f) {
		super(context, entityModel, f);
	}
	@Inject(method = "<init>", at = @At("TAIL"))
	private void fleecifer$init(EntityRendererProvider.Context context, CallbackInfo ci) {
		this.addLayer(new SheepEyesOverlayFeatureRenderer<>(this, new SheepModel(context.getModelSet().bakeLayer(Models.sheepEyes)), ResourceLocation.fromNamespaceAndPath("fleecifer", "textures/entity/sheep/sheep_eyes.png"), ResourceLocation.fromNamespaceAndPath("fleecifer", "textures/textured_entity/sheep/herobrine_eyes.png"), false));
		this.addLayer(new SheepEyesOverlayFeatureRenderer<>(this, new SheepModel(context.getModelSet().bakeLayer(Models.sheepEyesEmissive)), ResourceLocation.fromNamespaceAndPath("fleecifer", "textures/entity/sheep/sheep_eyes_emissive.png"), ResourceLocation.fromNamespaceAndPath("fleecifer", "textures/textured_entity/sheep/herobrine_eyes_emissive.png"), true));
	}
}
