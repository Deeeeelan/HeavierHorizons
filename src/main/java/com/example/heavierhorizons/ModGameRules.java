package com.example.heavierhorizons;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGameRules {

    public static final GameRules.Key<GameRules.BooleanRule> ARMOR_WEIGHT =
            GameRuleRegistry.register(
                    "armorWeight",
                    GameRules.Category.PLAYER,
                    GameRuleFactory.createBooleanRule(true)
            );

    public static void initialize() {

    }
}
