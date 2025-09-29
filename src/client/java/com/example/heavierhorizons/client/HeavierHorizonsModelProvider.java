package com.example.heavierhorizons.client;

import com.example.heavierhorizons.ModBlocks;
import com.example.heavierhorizons.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class HeavierHorizonsModelProvider extends FabricModelProvider {
    public HeavierHorizonsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STEEL_MILL);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COAL_DUST);
        itemModelGenerator.register(ModItems.FORGE_PICKAXE);
    }

    @Override
    public String getName() {
        return "HeavierHorizons Model Provider";
    }
}
