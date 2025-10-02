package com.example.heavierhorizons;

import com.example.heavierhorizons.armor.SteelArmorMaterial;
import com.example.heavierhorizons.item.ForgePickaxeItem;
import com.example.heavierhorizons.item.SteelMagnetItem;
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

import static com.example.heavierhorizons.HeavierHorizons.STEEL_TOOL_MATERIAL;

public class ModItems {
    // Creates a new item and registers it to the game.
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HeavierHorizons.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
    public static Item register2(String name, Item item) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HeavierHorizons.MOD_ID, name));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register((itemGroup) -> {
                itemGroup.add(ModItems.STEEL_INGOT);
                itemGroup.add(ModItems.COAL_DUST);
            });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> {
                    itemGroup.add(ModItems.STEEL_SWORD);
                    itemGroup.add(ModItems.STEEL_HELMET);
                    itemGroup.add(ModItems.STEEL_CHESTPLATE);
                    itemGroup.add(ModItems.STEEL_LEGGINGS);
                    itemGroup.add(ModItems.STEEL_BOOTS);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((itemGroup) -> {
                    itemGroup.add(ModItems.STEEL_PICKAXE);
                    itemGroup.add(ModItems.STEEL_AXE);
                    itemGroup.add(ModItems.STEEL_SHOVEL);
                    itemGroup.add(ModItems.STEEL_HOE);

                    itemGroup.add(ModItems.FORGE_PICKAXE);
                    itemGroup.add(ModItems.STEEL_MAGNET);
                });

    }

    public static final Item COAL_DUST = register("coal_dust", Item::new, new Item.Settings());

    // Steel Set
    public static final Item STEEL_INGOT = register("steel_ingot", Item::new, new Item.Settings());

    public static final Item STEEL_MAGNET = register2(
            "steel_magnet",
            new SteelMagnetItem(new Item.Settings()
                    .maxCount(1)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HeavierHorizons.MOD_ID, "steel_magnet")))
            )
    );
    public static final Item STEEL_SWORD = register(
            "steel_sword",
            Item::new,
            new Item.Settings()
                    .sword(STEEL_TOOL_MATERIAL, 4, -2.4f)
                    .repairable(ModItems.STEEL_INGOT)
    );
    public static final Item STEEL_PICKAXE = register(
            "steel_pickaxe",
            Item::new,
            new Item.Settings()
                    .pickaxe(STEEL_TOOL_MATERIAL, 2.0f, -2.8f)
                    .repairable(ModItems.STEEL_INGOT)
    );
    public static final Item STEEL_AXE = register(
            "steel_axe",
            Item::new,
            new Item.Settings()
                    .axe(STEEL_TOOL_MATERIAL, 5.0f, -3.0f)
                    .repairable(ModItems.STEEL_INGOT)
    );
    public static final Item STEEL_SHOVEL = register(
            "steel_shovel",
            Item::new,
            new Item.Settings()
                    .shovel(STEEL_TOOL_MATERIAL, 1.5f, -3f)
                    .repairable(ModItems.STEEL_INGOT)
    );
    public static final Item STEEL_HOE = register(
            "steel_hoe",
            Item::new,
            new Item.Settings()
                    .hoe(STEEL_TOOL_MATERIAL, -3.0f, -0.0f)
                    .repairable(ModItems.STEEL_INGOT)
    );

    public static final Item FORGE_PICKAXE = register2(
            "forge_pickaxe",
            new ForgePickaxeItem(new Item.Settings()
                    .pickaxe(ToolMaterial.IRON, 2, -2.8f)
                    .repairable(ModItems.STEEL_INGOT)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(HeavierHorizons.MOD_ID, "forge_pickaxe")))
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
