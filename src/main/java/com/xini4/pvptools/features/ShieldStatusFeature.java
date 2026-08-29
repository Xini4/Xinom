package com.xini4.pvptools.features;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.Feature;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.item.Items;
import net.minecraft.entity.player.PlayerEntity;

public class ShieldStatusFeature implements Feature {
    private final PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
    private boolean notifiedReady = false;

    @Override
    public void onHudRender(MinecraftClient client, MatrixStack matrices, float tickDelta) {
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
            DrawableHelper.fill(matrices, 0, 0, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight(), overlayColor);
        } else if (disabled) {
            DrawableHelper.fill(matrices, 0, 0, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight(), color);
        }

        int x = cfg.shieldBarX;
        int y = cfg.shieldBarY;
        int size = cfg.shieldBarSize;
        float fill = progress;
        int bg = 0x88000000;
        DrawableHelper.fill(matrices, x, y, x + size, y + size, bg);
        int fg = (cfg.shieldReadyColor & 0x00FFFFFF) | 0xFF000000;
        int filled = x + Math.round(size * fill);
        DrawableHelper.fill(matrices, x+2, y+2, filled-2, y+size-2, fg);

        if (progress >= 1.0f && !notifiedReady) {
            notifiedReady = true;
            if (cfg.shieldRecoverySound) {
                client.getSoundManager().play(client.player, net.minecraft.sound.SoundEvents.UI_TOAST_IN, net.minecraft.sound.SoundCategory.PLAYERS, 1f, 1f);
            }
            client.player.sendMessage(new net.minecraft.text.LiteralText("Shield ready"), true);
        } else if (progress < 1.0f) {
            notifiedReady = false;
        }
    }
}
