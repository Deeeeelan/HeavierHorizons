package com.example.heavierhorizons.block;

import com.example.heavierhorizons.HeavierHorizons;
import com.example.heavierhorizons.ModBlocks;
import com.example.heavierhorizons.block.entity.custom.SteelMillBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<SteelMillBlockEntity> STEEL_MILL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(HeavierHorizons.MOD_ID, "steel_mill_be"),
                    FabricBlockEntityTypeBuilder.create(SteelMillBlockEntity::new, ModBlocks.STEEL_MILL).build());


    public static void initialize() {

    }
}