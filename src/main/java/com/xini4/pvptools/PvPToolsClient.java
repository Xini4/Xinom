package com.xini4.pvptools;

import com.xini4.pvptools.config.ConfigManager;
import com.xini4.pvptools.features.*;
import com.xini4.pvptools.input.Keybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class PvPToolsClient implements ClientModInitializer {
    public static final String MODID = "xinom";
    public static final FeatureManager FEATURES = new FeatureManager();
    public static final ConfigManager CONFIG = new ConfigManager();

    @Override
    public void onInitializeClient() {
        CONFIG.load();
        registerFeatures();
        Keybinds.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> FEATURES.clientTick(client));
        HudRenderCallback.EVENT.register((matrices, tickDelta) -> FEATURES.onHudRender(MinecraftClient.getInstance(), matrices, tickDelta));
    }

    private void registerFeatures() {
        FEATURES.register(new TotemOverlayFeature());
        FEATURES.register(new CrystalOptimizerFeature());
        FEATURES.register(new AntiFireFeature());
        FEATURES.register(new ShieldStatusFeature());
        FEATURES.register(new NoHurtCamFeature());
    }
}
