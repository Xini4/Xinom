package com.xini4.pvptools;

import com.xini4.pvptools.gui.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public java.util.function.Function<Screen, Screen> getModConfigScreenFactory() {
        return parent -> ConfigScreenFactory.create(parent);
    }
}
