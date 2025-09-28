package com.example.smallinconveniences.client;

import com.example.smallinconveniences.Smallinconveniences;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SmallinconveniencesDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(SmallInconveniencesRecipeProvider::new);
        pack.addProvider(SmallInconveniencesModelProvider::new);
    }
}
