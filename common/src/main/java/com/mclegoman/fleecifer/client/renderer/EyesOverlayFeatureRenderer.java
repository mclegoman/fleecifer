/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.io.FileNotFoundException;

public class EyesOverlayFeatureRenderer<T extends LivingEntityRenderState, M extends EntityModel<T>> extends RenderLayer<T, M> {
	protected final M model;
	protected final M babyModel;
	protected final ResourceLocation texture;
	protected final boolean emissive;
	public EyesOverlayFeatureRenderer(RenderLayerParent<T, M> context, M model, M babyModel, ResourceLocation texture, boolean emissive) {
		super(context);
		this.model = model;
		this.babyModel = babyModel;
		this.texture = texture;
		this.emissive = emissive;
	}
	protected RenderType getRenderLayer(T entity) throws FileNotFoundException {
		Minecraft.getInstance().getResourceManager().getResourceOrThrow(this.getTexture(entity));
		return this.emissive ? RenderType.eyes(this.getTexture(entity)) : RenderType.entityCutoutNoCull(this.getTexture(entity));
	}
	protected ResourceLocation getTexture(T entity) {
		return this.texture;
	}
	public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T livingEntityRenderState, float f, float g) {
		if (!livingEntityRenderState.isInvisible) {
			M model = livingEntityRenderState.isBaby ? this.babyModel : this.model;
			model.setupAnim(livingEntityRenderState);
			RenderType renderType = null;
			try {
				renderType = this.getRenderLayer(livingEntityRenderState);
			} catch (FileNotFoundException ignored) {}
			if (renderType != null) {
				VertexConsumer vertexConsumer = multiBufferSource.getBuffer(renderType);
				model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
			}
		}
	}
}
