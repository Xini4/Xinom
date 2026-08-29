package com.xini4.pvptools.mixin;

import com.xini4.pvptools.PvPToolsClient;
import net.minecraft.client.sound.SoundEngine;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {
    @Redirect(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/SoundEngine;play(Lnet/minecraft/client/sound/SoundInstance;)V"))
    private void pvptools_play(SoundEngine engine, SoundInstance instance) {
        try {
            var cfg = PvPToolsClient.CONFIG.config;
            if (cfg.crystalDisableExplosionSound && instance.getSound() == SoundEvents.ENTITY_END_CRYSTAL_DEATH) {
                return;
            }
        } catch (Throwable ignored) {}
        engine.play(instance);
    }
}
