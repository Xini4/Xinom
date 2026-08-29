package com.xini4.pvptools.features;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.Feature;
import com.xini4.pvptools.config.PvPToolsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.entity.player.PlayerEntity;
import com.mojang.blaze3d.systems.RenderSystem;

public class TotemOverlayFeature implements Feature {
    private final PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;

    @Override
    public void onHudRender(MinecraftClient client, MatrixStack matrices, float tickDelta) {
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

        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        DrawableHelper.fill(matrices, 0, 0, screenW, screenH, color);
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();

        if (cfg.totemIconEnabled) {
            ItemRenderer itemRenderer = client.getItemRenderer();
            int x = cfg.totemIconX;
            int y = cfg.totemIconY;
            int size = cfg.totemIconSize;
            matrices.push();
            matrices.translate(x, y, 0);
            float scale = size / 16f;
            matrices.scale(scale, scale, scale);
            itemRenderer.renderInGui(new net.minecraft.item.ItemStack(Items.TOTEM_OF_UNDYING), 0, 0);
            matrices.pop();
        }
    }
}
