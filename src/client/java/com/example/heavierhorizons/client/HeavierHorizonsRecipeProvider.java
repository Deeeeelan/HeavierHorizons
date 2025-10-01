package com.example.heavierhorizons.client;

import com.example.heavierhorizons.ModBlocks;
import com.example.heavierhorizons.ModItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class HeavierHorizonsRecipeProvider extends FabricRecipeProvider {

    public HeavierHorizonsRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
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

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_SWORD, 1)
                        .pattern(" S ")
                        .pattern(" S ")
                        .pattern(" B ")
                        .input('S', ModItems.STEEL_INGOT)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_PICKAXE, 1)
                        .pattern("SSS")
                        .pattern(" B ")
                        .pattern(" B ")
                        .input('S', ModItems.STEEL_INGOT)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_AXE, 1)
                        .pattern("SS ")
                        .pattern("SB ")
                        .pattern(" B ")
                        .input('S', ModItems.STEEL_INGOT)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_SHOVEL, 1)
                        .pattern(" S ")
                        .pattern(" B ")
                        .pattern(" B ")
                        .input('S', ModItems.STEEL_INGOT)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_HOE, 1)
                        .pattern("SS ")
                        .pattern(" B ")
                        .pattern(" B ")
                        .input('S', ModItems.STEEL_INGOT)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_MILL.asItem(), 1)
                        .pattern("IBI")
                        .pattern("BLB")
                        .pattern("IBI")
                        .input('L', Items.LAVA_BUCKET)
                        .input('I', Items.IRON_INGOT)
                        .input('B', Items.IRON_BLOCK)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.FORGE_PICKAXE, 1)
                        .pattern("SGS")
                        .pattern(" L ")
                        .pattern(" B ")
                        .input('S', ModItems.STEEL_INGOT)
                        .input('G', Items.FURNACE)
                        .input('L', Items.LAVA_BUCKET)
                        .input('B', Items.STICK)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.STEEL_MAGNET, 1)
                        .pattern("S S")
                        .pattern("S S")
                        .pattern("SRS")
                        .input('S', ModItems.STEEL_INGOT)
                        .input('R', Items.REDSTONE)
                        .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(exporter);


                // RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
            }
        };
    }

    @Override
    public String getName() {
        return "HeavierHorizonsRecipeProvider";
    }
}
