package com.jahirtrap.critterarmory.init;

import com.jahirtrap.critterarmory.util.AnimalMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public enum ModMaterials implements AnimalMaterial {
    IRON(ArmorMaterials.IRON, "iron"),
    GOLD(ArmorMaterials.GOLD, "golden"),
    DIAMOND(ArmorMaterials.DIAMOND, "diamond"),
    NETHERITE(ArmorMaterials.NETHERITE, "netherite");

    private final String name;
    private final ResourceLocation location;
    private final int durability;
    private final int defense;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> ingredient;

    ModMaterials(String name, int durabilityMultiplier, int defense, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> ingredient) {
        this.name = name;
        this.location = new ResourceLocation(MODID, name);
        this.durability = durabilityMultiplier * 16;
        this.defense = defense;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.ingredient = ingredient;
    }

    ModMaterials(ArmorMaterial material, String name) {
        this.name = name;
        this.location = new ResourceLocation(MODID, material.getName());
        this.durability = material.getDurabilityForType(Type.CHESTPLATE);
        this.defense = material.getDefenseForType(Type.CHESTPLATE);
        this.enchantmentValue = material.getEnchantmentValue();
        this.sound = material.getEquipSound();
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.ingredient = material::getRepairIngredient;
    }

    public int getDurability() {
        return durability;
    }

    public int getDefense() {
        return defense;
    }

    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return sound;
    }

    public Ingredient getRepairIngredient() {
        return ingredient.get();
    }

    public String getName() {
        return name;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public float getToughness() {
        return toughness;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }
}
