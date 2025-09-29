package com.example.heavierhorizons.armor;

import com.example.heavierhorizons.HeavierHorizons;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

public class SteelArmorMaterial {
    public static final int BASE_DURABILITY = 15;
    public static final RegistryKey<EquipmentAsset> STEEL_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(HeavierHorizons.MOD_ID, "steel"));
    public static final TagKey<Item> REPAIRS_STEEL_ARMOR = TagKey.of(Registries.ITEM.getKey(), Identifier.of(HeavierHorizons.MOD_ID, "repairs_steel_armor"));

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET, 3,
                    EquipmentType.CHESTPLATE, 8,
                    EquipmentType.LEGGINGS, 6,
                    EquipmentType.BOOTS, 3
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            REPAIRS_STEEL_ARMOR,
            STEEL_ARMOR_MATERIAL_KEY
    );
}
