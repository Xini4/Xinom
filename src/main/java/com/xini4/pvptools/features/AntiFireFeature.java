package com.xini4.pvptools/features;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.Feature;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class AntiFireFeature implements Feature {
    private final PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;

    @Override
    public void onHudRender(MinecraftClient client, DrawContext drawContext, float tickDelta) {
        if (!cfg.antiFireOverlay && !cfg.antiLavaOverlay) return;

        if (cfg.antiFireOverlay && client.player != null && client.player.isOnFire()) {
            int screenW = client.getWindow().getScaledWidth();
            int screenH = client.getWindow().getScaledHeight();
            int alpha = 80;
            int color = (alpha << 24) | 0x000000;
            drawContext.fill(0, 0, screenW, screenH, color);
        }

        if (cfg.antiLavaOverlay && client.player != null && client.player.isInLava()) {
            int screenW = client.getWindow().getScaledWidth();
            int screenH = client.getWindow().getScaledHeight();
            int alpha = 100;
            int color = (alpha << 24) | 0x003300;
            drawContext.fill(0, 0, screenW, screenH, color);
        }
    }
}
