package com.xini4.pvptools.gui;

import com.xini4.pvptools.PvPToolsClient;
import com.xini4.pvptools.config.PvPToolsConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreenFactory {
    public static Screen create(Screen parent) {
        PvPToolsConfig cfg = PvPToolsClient.CONFIG.config;
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("config.xinom.title"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var cat = builder.getOrCreateCategory(Text.translatable("config.xinom.category.totem"));
        cat.addEntry(entryBuilder.startBooleanToggle(Text.translatable("config.xinom.totem.enable"), cfg.totemOverlayEnabled)
                .setSaveConsumer(v -> { cfg.totemOverlayEnabled = v; PvPToolsClient.CONFIG.save(); })
                .build());
        cat.addEntry(entryBuilder.startIntField(Text.translatable("config.xinom.totem.opacity"), cfg.totemOverlayOpacity)
                .setSaveConsumer(v -> { cfg.totemOverlayOpacity = v; PvPToolsClient.CONFIG.save(); })
                .build());
        cat.addEntry(entryBuilder.startTextField(Text.translatable("config.xinom.totem.color"), String.format("#%08X", cfg.totemOverlayColor))
                .setSaveConsumer(v -> { try { cfg.totemOverlayColor = Integer.parseUnsignedInt(v.replace("#",""), 16); } catch (Exception ignored) {} PvPToolsClient.CONFIG.save(); })
                .build());

        return builder.build();
    }
}
