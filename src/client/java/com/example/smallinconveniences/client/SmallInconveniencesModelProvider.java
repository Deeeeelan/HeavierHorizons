package com.example.smallinconveniences.client;

import com.example.smallinconveniences.ModBlocks;
import com.example.smallinconveniences.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;

public class SmallInconveniencesModelProvider extends FabricModelProvider {
    public SmallInconveniencesModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STEEL_MILL);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COAL_DUST);
    }

    @Override
    public String getName() {
        return "SmallInconveniences Model Provider";
    }
}
