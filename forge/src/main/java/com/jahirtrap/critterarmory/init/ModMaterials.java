package com.jahirtrap.critterarmory.init;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.*;

import java.util.EnumMap;
import java.util.Map;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModMaterials {
    public interface MobArmor {
        Map.Entry<ArmorMaterial, String> IRON = copy(ArmorMaterials.IRON);
        Map.Entry<ArmorMaterial, String> GOLD = copy(ArmorMaterials.GOLD, "golden");
        Map.Entry<ArmorMaterial, String> DIAMOND = copy(ArmorMaterials.DIAMOND);
        Map.Entry<ArmorMaterial, String> NETHERITE = copy(ArmorMaterials.NETHERITE);

        private static EnumMap<ArmorType, Integer> createMap(int[] values) {
            EnumMap<ArmorType, Integer> enumMap = new EnumMap<>(ArmorType.class);
            for (int i = 0; i < values.length; i++) enumMap.put(ArmorType.values()[i], values[i]);
            return enumMap;
        }

        private static ResourceKey<EquipmentAsset> createAsset(String name) {
            return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(MODID, name));
        }

        private static Map.Entry<ArmorMaterial, String> copy(ArmorMaterial material) {
            return register(new ArmorMaterial(material.durability(), material.defense(), material.enchantmentValue(), material.equipSound(), material.toughness(), material.knockbackResistance(), material.repairIngredient(), createAsset(material.assetId().location().getPath())));
        }

        private static Map.Entry<ArmorMaterial, String> copy(ArmorMaterial material, String name) {
            return register(copy(material).getKey(), name);
        }

        private static Map.Entry<ArmorMaterial, String> register(ArmorMaterial material) {
            return register(material, material.assetId().location().getPath());
        }

        private static Map.Entry<ArmorMaterial, String> register(ArmorMaterial material, String name) {
            return Map.entry(material, name);
        }
    }
}
