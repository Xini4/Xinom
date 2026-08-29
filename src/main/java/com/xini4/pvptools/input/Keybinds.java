package com.xini4.pvptools.input;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static KeyBinding TOGGLE_TOTEM;
    public static KeyBinding OPEN_CONFIG;

    public static void register() {
        TOGGLE_TOTEM = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.xinom.toggle_totem",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "category.xinom"
        ));
        OPEN_CONFIG = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.xinom.open_config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "category.xinom"
        ));
    }
}
