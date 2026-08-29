package com.xini4.pvptools.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xini4.pvptools.PvPToolsClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final Path CONFIG_DIR = Path.of("config").resolve(PvPToolsClient.MODID);
    public PvPToolsConfig config = new PvPToolsConfig();

    public void load() {
        try {
            File dir = CONFIG_DIR.toFile();
            if (!dir.exists()) dir.mkdirs();
            File conf = CONFIG_DIR.resolve("config.json").toFile();
            if (conf.exists()) {
                try (FileReader r = new FileReader(conf)) {
                    config = GSON.fromJson(r, PvPToolsConfig.class);
                }
            } else {
                save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            File conf = CONFIG_DIR.resolve("config.json").toFile();
            try (FileWriter w = new FileWriter(conf)) {
                GSON.toJson(config, w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
