package com.example.smallinconveniences;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // Creates a new item and registers it to the game.
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Smallinconveniences.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> itemGroup.add(ModItems.TEST_ITEM));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> itemGroup.add(ModItems.STEEL_INGOT));
    }
    public static final Item TEST_ITEM = register("test_item", Item::new, new Item.Settings());
    public static final Item STEEL_INGOT = register("steel_ingot", Item::new, new Item.Settings());

}
