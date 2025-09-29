package com.example.heavierhorizons.screen;

import com.example.heavierhorizons.HeavierHorizons;
import com.example.heavierhorizons.screen.custom.SteelMillScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<SteelMillScreenHandler> STEEL_MILL_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(HeavierHorizons.MOD_ID, "steel_mill_screen_handler"),
                    new ExtendedScreenHandlerType<>(SteelMillScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void initialize() {

    }
}