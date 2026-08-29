package com.xini4.pvptools/features;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.Feature;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.item.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.sound.SimpleSoundInstance;
import net.minecraft.sound.SoundEvents;

public class ShieldStatusFeature implements Feature {
    private final PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
    private boolean notifiedReady = false;

    @Override
    public void onHudRender(MinecraftClient client, DrawContext drawContext, float tickDelta) {
        if (!cfg.shieldStatusEnabled) return;
        PlayerEntity p = client.player;
        if (p == null) return;

        float progress = p.getItemCooldownManager().getCooldownProgress(Items.SHIELD, 0f);
        boolean isBlocking = p.isUsingItem() && p.getActiveItem().getItem() == Items.SHIELD;
        boolean disabled = progress < 1.0f && !isBlocking;

        int color = disabled ? cfg.shieldDisabledColor : cfg.shieldReadyColor;
        if (!isBlocking && !disabled) {
            int alpha = (color >> 24) & 0xFF;
            int rgb = color & 0x00FFFFFF;
            int overlayColor = (alpha << 24) | rgb;
            drawContext.fill(0, 0, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight(), overlayColor);
        } else if (disabled) {
            drawContext.fill(0, 0, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight(), color);
        }

        int x = cfg.shieldBarX;
        int y = cfg.shieldBarY;
        int size = cfg.shieldBarSize;
        float fill = progress;
        int bg = 0x88000000;
        drawContext.fill(x, y, x + size, y + size, bg);
        int fg = (cfg.shieldReadyColor & 0x00FFFFFF) | 0xFF000000;
        int filled = x + Math.round(size * fill);
        drawContext.fill(x+2, y+2, filled-2, y+size-2, fg);

        if (progress >= 1.0f && !notifiedReady) {
            notifiedReady = true;
            if (cfg.shieldRecoverySound) {
                client.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_TOAST_IN, 1.0F));
            }
            client.inGameHud.getChatHud().addMessage(Text.literal("Shield ready"));
        } else if (progress < 1.0f) {
            notifiedReady = false;
        }
    }
}
