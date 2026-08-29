package com.xini4.pvptools;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class FeatureManager {
    private final List<Feature> features = new ArrayList<>();

    public void register(Feature f) {
        f.init();
        features.add(f);
    }

    public void clientTick(MinecraftClient client) {
        for (Feature f : features) {
            try {
                f.clientTick(client);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public void onHudRender(MinecraftClient client, MatrixStack matrices, float tickDelta) {
        for (Feature f : features) {
            try {
                f.onHudRender(client, matrices, tickDelta);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
