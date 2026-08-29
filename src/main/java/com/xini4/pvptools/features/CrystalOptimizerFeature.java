package com.xini4.pvptools.features;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.Feature;
import com.xini4.pvptools.config.PvPToolsConfig;
import com.xini4.pvptools.optimizer.Optimizer;
import net.minecraft.client.MinecraftClient;

public class CrystalOptimizerFeature implements Feature {
    private final PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
    private Optimizer optimizer;

    @Override
    public void init() {
        optimizer = new Optimizer();
    }

    @Override
    public void clientTick(MinecraftClient client) {
        // минимальная логика
    }
}
