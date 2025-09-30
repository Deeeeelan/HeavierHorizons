package com.example.heavierhorizons;

import com.example.heavierhorizons.block.custom.SteelMillBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    // Creates a new block and registers it to the game.
    private static Block register(String name,
                                  Function<AbstractBlock.Settings, Block> blockFactory,
                                  AbstractBlock.Settings settings,
                                  boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static Block register2(String name, Block block) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        RegistryKey<Item> itemKey = keyOfItem(name);

        Block registeredBlock = Registry.register(Registries.BLOCK, blockKey, block);

        BlockItem blockItem = new BlockItem(registeredBlock, new Item.Settings().registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, blockItem);

        return registeredBlock;
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(HeavierHorizons.MOD_ID, name));
    }
    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HeavierHorizons.MOD_ID, name));
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register((itemGroup) -> {
                    itemGroup.add(ModBlocks.STEEL_BLOCK.asItem());
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
                .register((itemGroup) -> {
                    itemGroup.add(ModBlocks.STEEL_MILL.asItem());
                });
    }

    public static final Block STEEL_BLOCK = register(
            "steel_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.IRON)
                    .requiresTool()
                    .strength(5.0f, 6.0f),
            true);

    public static final Block STEEL_MILL = register2("steel_mill",
            new SteelMillBlock(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.IRON)
                    .requiresTool()
                    .strength(5.2f, 7.0f)
                    .registryKey(keyOfBlock("steel_mill"))
    ));
}
