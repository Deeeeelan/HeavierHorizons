package com.example.smallinconveniences;

import net.fabricmc.api.ModInitializer;

public class Smallinconveniences implements ModInitializer {

    public static final String MOD_ID = "smallinconveniences";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
    }
}
