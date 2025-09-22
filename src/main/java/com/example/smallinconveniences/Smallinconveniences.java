package com.example.smallinconveniences;

import net.minecraft.util.Identifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;

public class Smallinconveniences implements ModInitializer {

    public static final String MOD_ID = "smallinconveniences";

    private static final Identifier ARMOR_SPEED_PENALTY_ID = Identifier.of(Smallinconveniences.MOD_ID, "armor_speed_penalty");

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                float speedDecrease = -(float) (player.getArmor() / 1000.0); // Base speed is 0.1, 20% decrease at max armor
                if (speedDecrease < 0) {
                    EntityAttributeInstance playerSpeed = player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);

                    if (playerSpeed != null) {
                        if (playerSpeed.getModifier(ARMOR_SPEED_PENALTY_ID) != null) {
                            playerSpeed.removeModifier(ARMOR_SPEED_PENALTY_ID);
                        }
                        EntityAttributeModifier speedModifier = new EntityAttributeModifier(
                                ARMOR_SPEED_PENALTY_ID,
                                speedDecrease,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        );
                        playerSpeed.addPersistentModifier(speedModifier);
                        // System.out.println(speedDecrease);
                    }
                }
            }
        });

    }
}
