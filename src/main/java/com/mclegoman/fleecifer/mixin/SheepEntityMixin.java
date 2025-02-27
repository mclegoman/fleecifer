/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import com.mclegoman.fleecifer.goals.SheepAttackGoal;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {
	public SheepEntityMixin(World world) {
		super(world);
	}
	@Inject(method = "initializeAttributes", at = @At("RETURN"))
	private void fleecifer$createAttributes(CallbackInfo ci) {
		this.getAttributeContainer().register(EntityAttributes.GENERIC_ATTACK_DAMAGE);
		this.initializeAttribute(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(3.0);
		this.initializeAttribute(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(20.0);
		this.initializeAttribute(EntityAttributes.GENERIC_FOLLOW_RANGE).setBaseValue(35.0);
		this.initializeAttribute(EntityAttributes.GENERIC_ARMOR).setBaseValue(2.0);
		this.getAttributeContainer().register(new ClampedEntityAttribute(null, "sheep.spawnReinforcements", 0.0, 0.0, 1.0).setName("Spawn Reinforcements Chance")).setBaseValue(this.random.nextDouble() * 0.10000000149011612);
	}
	@Inject(method = "initGoals", at = @At("RETURN"))
	private void fleecifer$initGoals(CallbackInfo ci) {
		this.goals.add(0, new SheepAttackGoal((SheepEntity) (Object)this));
		this.attackGoals.add(1, new RevengeGoal(this, true, SheepEntity.class));
		this.attackGoals.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
	}
	@Override
	public boolean tryAttack(Entity target) {
		boolean bl = hostileTryAttack(target);
		if (bl) {
			float f = this.world.getLocalDifficulty(new BlockPos(this)).getLocalDifficulty();
			if (this.getMainHandStack().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
				target.setOnFireFor(2 * (int)f);
			}
		}

		return bl;
	}

	@Unique
	private boolean hostileTryAttack(Entity target) {
		float f = (float)this.initializeAttribute(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue();
		int i = 0;
		if (target instanceof LivingEntity) {
			f += EnchantmentHelper.getAttackDamage(this.getMainHandStack(), ((LivingEntity)target).getGroup());
			i += EnchantmentHelper.getKnockback(this);
		}

		boolean bl = target.damage(DamageSource.mob(this), f);
		if (bl) {
			if (i > 0 && target instanceof LivingEntity) {
				((LivingEntity)target).method_6109(this, (float)i * 0.5F, MathHelper.sin(this.yaw * 0.017453292F), -MathHelper.cos(this.yaw * 0.017453292F));
				this.velocityX *= 0.6;
				this.velocityZ *= 0.6;
			}

			int j = EnchantmentHelper.getFireAspect(this);
			if (j > 0) {
				target.setOnFireFor(j * 4);
			}

			if (target instanceof PlayerEntity) {
				PlayerEntity playerEntity = (PlayerEntity)target;
				ItemStack itemStack = this.getMainHandStack();
				ItemStack itemStack2 = playerEntity.method_13061() ? playerEntity.method_13064() : ItemStack.EMPTY;
				if (!itemStack.isEmpty() && !itemStack2.isEmpty() && itemStack.getItem() instanceof AxeItem && itemStack2.getItem() == Items.SHIELD) {
					float g = 0.25F + (float)EnchantmentHelper.getEfficiency(this) * 0.05F;
					if (this.random.nextFloat() < g) {
						playerEntity.getItemCooldownManager().method_11384(Items.SHIELD, 100);
						this.world.sendEntityStatus(playerEntity, (byte)30);
					}
				}
			}

			this.dealDamage(this, target);
		}

		return bl;
	}
}
