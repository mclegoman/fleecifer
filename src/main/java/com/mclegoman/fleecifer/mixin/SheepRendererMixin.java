/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import com.mclegoman.fleecifer.client.entity.SheepEyesLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SheepRenderer;
import net.minecraft.client.renderer.entity.model.SheepModel;
import net.minecraft.entity.passive.SheepEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepRenderer.class)
public abstract class SheepRendererMixin extends MobRenderer<SheepEntity, SheepModel<SheepEntity>> {
	public SheepRendererMixin(EntityRendererManager entityRendererManager, SheepModel<SheepEntity> model, float i) {
		super(entityRendererManager, model, i);
	}

	@Inject(method = "<init>", at = @At("RETURN"))
	private void fleecifer$init(EntityRendererManager entityRendererManager, CallbackInfo ci) {
		this.addLayer(new SheepEyesLayer(this, false));
		this.addLayer(new SheepEyesLayer(this, true));
	}
}
