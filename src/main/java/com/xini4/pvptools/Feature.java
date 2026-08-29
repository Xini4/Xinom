package com.xini4.pvptools;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public interface Feature {
    default void init() {}
    default void clientTick(MinecraftClient client) {}
    default void onHudRender(MinecraftClient client, DrawContext drawContext, float tickDelta) {}
    default void onConfigReload() {}
}
