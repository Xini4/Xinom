package com.xini4.pvptools/features;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.Feature;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;

public class TotemOverlayFeature implements Feature {
    private final PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;

    @Override
    public void onHudRender(MinecraftClient client, DrawContext drawContext, float tickDelta) {
        if (!cfg.totemOverlayEnabled) return;
        PlayerEntity player = client.player;
        if (player == null) return;

        boolean hasTotem = false;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack s = player.getInventory().getStack(i);
            if (s.getItem() == Items.TOTEM_OF_UNDYING) { hasTotem = true; break; }
        }
        boolean holdingTotem = player.getMainHandStack().getItem() == Items.TOTEM_OF_UNDYING || player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING;
        if (!hasTotem || holdingTotem) return;

        int screenW = client.getWindow().getScaledWidth();
        int screenH = client.getWindow().getScaledHeight();

        int alpha = (int) (255.0f * (cfg.totemOverlayOpacity / 100f));
        int color = (cfg.totemOverlayColor & 0x00FFFFFF) | (alpha << 24);

        // DrawContext.fill handles rectangle drawing
        drawContext.fill(0, 0, screenW, screenH, color);

        if (cfg.totemIconEnabled) {
            int x = cfg.totemIconX;
            int y = cfg.totemIconY;
            int size = cfg.totemIconSize;
            // Draw item via DrawContext
            ItemStack totem = new ItemStack(Items.TOTEM_OF_UNDYING);
            drawContext.drawItem(totem, x, y);
        }
    }
}
