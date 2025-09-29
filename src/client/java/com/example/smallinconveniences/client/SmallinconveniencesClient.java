package com.example.smallinconveniences.client;

import com.example.smallinconveniences.screen.ModScreenHandlers;
import com.example.smallinconveniences.client.custom.SteelMillScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public class SmallinconveniencesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.STEEL_MILL_SCREEN_HANDLER, SteelMillScreen::new);
    }
}