/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntityRenderer.class)
public abstract class SheepEntityRendererMixin {
	@Inject(method = "getTexture(Lnet/minecraft/entity/passive/SheepEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"), cancellable = true)
	private void fleecifer$replaceTexture(SheepEntity sheepEntity, CallbackInfoReturnable<Identifier> cir) {
		if (sheepEntity.hasCustomName() && sheepEntity.getCustomName().equalsIgnoreCase("Herobrine")) cir.setReturnValue(new Identifier("fleecifer", "textures/entity/sheep/herobrine.png"));
	}
}
