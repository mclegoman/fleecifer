/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(QuadrupedModel.class)
public interface QuadrupedModelAccessor {
	@Accessor("head")
	ModelRenderer getHead();
	@Accessor("body")
	ModelRenderer getBody();
	@Accessor("leg0")
	ModelRenderer getLeg0();
	@Accessor("leg1")
	ModelRenderer getLeg1();
	@Accessor("leg2")
	ModelRenderer getLeg2();
	@Accessor("leg3")
	ModelRenderer getLeg3();
}