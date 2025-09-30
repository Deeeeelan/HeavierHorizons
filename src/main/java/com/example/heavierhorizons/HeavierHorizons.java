package com.example.heavierhorizons;

import com.example.heavierhorizons.armor.SteelArmorMaterial;
import com.example.heavierhorizons.block.ModBlockEntities;
import com.example.heavierhorizons.screen.ModScreenHandlers;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;

public class HeavierHorizons implements ModInitializer {

    public static final String MOD_ID = "heavierhorizons";

    private static final Identifier ARMOR_SPEED_PENALTY_ID = Identifier.of(HeavierHorizons.MOD_ID, "armor_speed_penalty");

    public static final ToolMaterial STEEL_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            455,
            7.0F,
            2.5F,
            16,
            ItemTags.IRON_TOOL_MATERIALS
    );

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
        ModBlockEntities.initialize();
        ModScreenHandlers.initialize();

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

                // Decreases player speed based on armor
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
                    }
                }
            }
        });

    }
}
