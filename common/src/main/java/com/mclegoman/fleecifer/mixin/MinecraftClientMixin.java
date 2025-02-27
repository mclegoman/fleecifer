/*
    Fleecifer
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.fleecifer.mixin;

import com.mclegoman.fleecifer.client.ClientMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V"))
    private void fleecifer$init(GameConfig gameConfig, CallbackInfo ci) {
        ClientMain.init();
    }
}
