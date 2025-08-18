/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.client.entity;

import com.mclegoman.fleecifer.mixin.QuadrupedModelAccessor;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SheepEyesLayer extends LayerRenderer<SheepEntity, SheepModel<SheepEntity>> {
    private static final ResourceLocation texture = new ResourceLocation("fleecifer", "textures/entity/sheep/sheep_eyes.png");
    private static final ResourceLocation emissiveTexture = new ResourceLocation("fleecifer", "textures/entity/sheep/sheep_eyes_emissive.png");
    private final SheepModel<SheepEntity> model = new SheepModel<>();
    private final boolean emissive;

    public SheepEyesLayer(IEntityRenderer<SheepEntity, SheepModel<SheepEntity>> entityRenderer, boolean emissive) {
        super(entityRenderer);
        this.emissive = emissive;
    }

    public void render(MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int i, SheepEntity sheepEntity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        if (!sheepEntity.isInvisible()) {
            this.getParentModel().copyPropertiesTo(this.model);
            ((QuadrupedModelAccessor)this.model).getHead().copyFrom(((QuadrupedModelAccessor)this.getParentModel()).getHead());
            ((QuadrupedModelAccessor)this.model).getBody().copyFrom(((QuadrupedModelAccessor)this.getParentModel()).getBody());
            ((QuadrupedModelAccessor)this.model).getLeg0().copyFrom(((QuadrupedModelAccessor)this.getParentModel()).getLeg0());
            ((QuadrupedModelAccessor)this.model).getLeg1().copyFrom(((QuadrupedModelAccessor)this.getParentModel()).getLeg1());
            ((QuadrupedModelAccessor)this.model).getLeg2().copyFrom(((QuadrupedModelAccessor)this.getParentModel()).getLeg2());
            ((QuadrupedModelAccessor)this.model).getLeg3().copyFrom(((QuadrupedModelAccessor)this.getParentModel()).getLeg3());
            IVertexBuilder vertexBuilder = renderTypeBuffer.getBuffer(getRenderLayer(this.emissive));
            this.model.renderToBuffer(matrixStack, vertexBuilder, i, LivingRenderer.getOverlayCoords(sheepEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public ResourceLocation getTexture(boolean isEmissive) {
        return isEmissive ? emissiveTexture : texture;
    }
    protected RenderType getRenderLayer(boolean emissive) {
        return emissive ? RenderType.eyes(this.getTexture(true)) : RenderType.entityCutoutNoCull(this.getTexture(false));
    }
}