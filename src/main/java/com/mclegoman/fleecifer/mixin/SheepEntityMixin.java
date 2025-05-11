/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.mob.passive.animal.AnimalEntity;
import net.minecraft.entity.living.mob.passive.animal.SheepEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {
	public SheepEntityMixin(World world) {
		super(world);
	}
	@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/living/mob/passive/animal/SheepEntity;setDimensions(FF)V"))
	private void fleecifer$updateTexture(World par1, CallbackInfo ci) {
		this.texture = "/assets/fleecifer/textures/entity/sheep/sheep.png";
	}
	protected void attack(Entity target, float power) {
		if ((double)power < 2.5 && target.shape.maxY > this.shape.minY && target.shape.minY < this.shape.maxY) {
			this.attackTime = 20;
			target.damage(this, 2);
		}
	}
	protected Entity findClosestAttackablePlayer() {
		return this.world.f_2176096.getSquaredDistanceTo(this) < 256.0F ? this.world.f_2176096 : null;
	}
}