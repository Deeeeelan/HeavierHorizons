package com.example.smallinconveniences;

import com.example.smallinconveniences.armor.SteelArmorMaterial;
import com.example.smallinconveniences.item.ForgePickaxeItem;
import com.jcraft.jorbis.Block;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static com.example.smallinconveniences.Smallinconveniences.STEEL_TOOL_MATERIAL;

public class ModItems {
    // Creates a new item and registers it to the game.
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Smallinconveniences.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
    public static Item register2(String name, Item item) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Smallinconveniences.MOD_ID, name));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register((itemGroup) -> {
                itemGroup.add(ModItems.TEST_ITEM);
                itemGroup.add(ModItems.STEEL_INGOT);
                itemGroup.add(ModItems.COAL_DUST);
            });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> {
                    itemGroup.add(ModItems.STEEL_HELMET);
                    itemGroup.add(ModItems.STEEL_CHESTPLATE);
                    itemGroup.add(ModItems.STEEL_LEGGINGS);
                    itemGroup.add(ModItems.STEEL_BOOTS);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((itemGroup) -> {
                    itemGroup.add(ModItems.STEEL_SWORD);
                    itemGroup.add(ModItems.FORGE_PICKAXE);
                });

    }

    public static final Item TEST_ITEM = register("test_item", Item::new, new Item.Settings());

    public static final Item COAL_DUST = register("coal_dust", Item::new, new Item.Settings());

    // Steel Set
    public static final Item STEEL_INGOT = register("steel_ingot", Item::new, new Item.Settings());


    public static final Item STEEL_SWORD = register(
            "steel_sword",
            Item::new,
            new Item.Settings()
                    .sword(STEEL_TOOL_MATERIAL, 10.5f, -2.8f)
                    .repairable(ModItems.STEEL_INGOT)
    );
    public static final Item FORGE_PICKAXE = register2(
            "forge_pickaxe",
            new ForgePickaxeItem(new Item.Settings()
                    .pickaxe(ToolMaterial.IRON, 1, -2.8f)
                    .repairable(ModItems.STEEL_INGOT)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Smallinconveniences.MOD_ID, "forge_pickaxe")))
                    .enchantable(16)
            )
    );

    public static final Item STEEL_HELMET = register(
            "steel_helmet",
            Item::new,
            new Item.Settings().armor(SteelArmorMaterial.INSTANCE, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(SteelArmorMaterial.BASE_DURABILITY))
    );

    public static final Item STEEL_CHESTPLATE = register("steel_chestplate",
            Item::new,
            new Item.Settings().armor(SteelArmorMaterial.INSTANCE, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(SteelArmorMaterial.BASE_DURABILITY))
    );

    public static final Item STEEL_LEGGINGS = register(
            "steel_leggings",
            Item::new,
            new Item.Settings().armor(SteelArmorMaterial.INSTANCE, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(SteelArmorMaterial.BASE_DURABILITY))
    );

    public static final Item STEEL_BOOTS = register(
            "steel_boots",
            Item::new,
            new Item.Settings().armor(SteelArmorMaterial.INSTANCE, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(SteelArmorMaterial.BASE_DURABILITY))
    );
}
