package com.example.smallinconveniences.client;

import com.example.smallinconveniences.ModBlocks;
import com.example.smallinconveniences.ModItems;

import com.example.smallinconveniences.Smallinconveniences;
import com.nimbusds.oauth2.sdk.id.Identifier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SmallInconveniencesRecipeProvider extends FabricRecipeProvider {

    public SmallInconveniencesRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {

            private void createMonoItemRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output,
                                              int quantity, ItemConvertible input, String[] pattern, String name) {
                createShaped(category, output, quantity)
                        .pattern(pattern[0])
                        .pattern(pattern.length > 1 ? pattern[1] : "   ")
                        .pattern(pattern.length > 2 ? pattern[2] : "   ")
                        .input('S', input)
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter);

            }


            @Override
            public void generate() {


                createShapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK.asItem())
                        .input(ModItems.STEEL_INGOT, 9)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_HELMET, 1,
                        ModItems.STEEL_INGOT, new String[]{"SSS", "S S"}, "steel_helmet");
                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_CHESTPLATE, 1,
                        ModItems.STEEL_INGOT, new String[]{"S S", "SSS", "SSS"}, "steel_chestplate");
                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_LEGGINGS, 1,
                        ModItems.STEEL_INGOT, new String[]{"SSS", "S S", "S S"}, "steel_leggings");
                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_BOOTS, 1,
                        ModItems.STEEL_INGOT, new String[]{"S S", "S S"}, "steel_boots");


                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
            }
        };
    }

    @Override
    public String getName() {
        return "SmallInconveniencesRecipeProvider";
    }
}
