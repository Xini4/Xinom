package com.xini4.pvptools.mixin;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererHurtMixin {
    @Redirect(method = "getCameraPitch", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getHurt()F"))
    private float pvptools_getHurt(MinecraftClient client) {
        try {
            PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
            if (cfg.noHurtCam) return 0f;
        } catch (Throwable ignored) {}
        return client.getHurt();
    }
}
