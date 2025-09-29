package com.example.smallinconveniences.client;

import com.example.smallinconveniences.ModBlocks;
import com.example.smallinconveniences.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
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

            // Creates a shaped recipe that only consists of one item
            private void createMonoItemRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output,
                                              int quantity, ItemConvertible input, String[] pattern) {
                createShaped(category, output, quantity)
                        .pattern(pattern[0])
                        .pattern(pattern.length > 1 ? pattern[1] : "   ")
                        .pattern(pattern.length > 2 ? pattern[2] : "   ")
                        .input('S', input)
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter);

            }

            private void ShapelessRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output,
                                         int outputQualtity, ItemConvertible input, int inputQuantity) {
                createShapeless(category, output, outputQualtity)
                        .input(input, inputQuantity)
                        .criterion(hasItem(input), conditionsFromItem(input))
                        .offerTo(exporter);
            }

            @Override
            public void generate() {

                ShapelessRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK.asItem(),
                        1, ModItems.STEEL_INGOT, 9);
                ShapelessRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_INGOT,
                        9, ModBlocks.STEEL_BLOCK.asItem(), 1);
                ShapelessRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.COAL_DUST,
                        2, Items.COAL, 1);

                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_HELMET, 1,
                        ModItems.STEEL_INGOT, new String[]{"SSS", "S S"});
                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_CHESTPLATE, 1,
                        ModItems.STEEL_INGOT, new String[]{"S S", "SSS", "SSS"});
                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_LEGGINGS, 1,
                        ModItems.STEEL_INGOT, new String[]{"SSS", "S S", "S S"});
                createMonoItemRecipe(exporter, RecipeCategory.COMBAT, ModItems.STEEL_BOOTS, 1,
                        ModItems.STEEL_INGOT, new String[]{"S S", "S S"});


                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
            }
        };
    }

    @Override
    public String getName() {
        return "SmallInconveniencesRecipeProvider";
    }
}
