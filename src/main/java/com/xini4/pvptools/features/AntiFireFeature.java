package com.xini4.pvptools.features;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.Feature;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.DrawableHelper;
import com.mojang.blaze3d.systems.RenderSystem;

public class AntiFireFeature implements Feature {
    private final PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;

    @Override
    public void onHudRender(MinecraftClient client, MatrixStack matrices, float tickDelta) {
        if (!cfg.antiFireOverlay && !cfg.antiLavaOverlay) return;

        if (cfg.antiFireOverlay && client.player != null && client.player.isOnFire()) {
            int screenW = client.getWindow().getScaledWidth();
            int screenH = client.getWindow().getScaledHeight();
            int alpha = 80;
            int color = (alpha << 24) | 0x000000;
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            DrawableHelper.fill(matrices, 0, 0, screenW, screenH, color);
            RenderSystem.disableBlend();
            RenderSystem.enableTexture();
        }

        if (cfg.antiLavaOverlay && client.player != null && client.player.isInLava()) {
            int screenW = client.getWindow().getScaledWidth();
            int screenH = client.getWindow().getScaledHeight();
            int alpha = 100;
            int color = (alpha << 24) | 0x003300;
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            DrawableHelper.fill(matrices, 0, 0, screenW, screenH, color);
            RenderSystem.disableBlend();
            RenderSystem.enableTexture();
        }
    }
}
