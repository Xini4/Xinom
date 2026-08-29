package com.xini4.pvptools;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public interface Feature {
    default void init() {}
    default void clientTick(MinecraftClient client) {}
    default void onHudRender(MinecraftClient client, MatrixStack matrices, float tickDelta) {}
    default void onConfigReload() {}
}
