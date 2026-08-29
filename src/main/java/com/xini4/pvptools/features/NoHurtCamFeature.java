package com.xini4.pvptools.features;

import com.xini4.pvptools.Feature;

public class NoHurtCamFeature implements Feature {
    @Override
    public void init() {
        // Подавление реализовано в mixin'е GameRendererHurtMixin
    }
}
