package com.jahirtrap.critterarmory.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModMaterials {
    public static final DeferredRegister<ArmorMaterial> MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MODID);

    public static final Map.Entry<Holder<ArmorMaterial>, String> IRON = copy(ArmorMaterials.IRON);
    public static final Map.Entry<Holder<ArmorMaterial>, String> GOLD = copy(ArmorMaterials.GOLD, "golden");
    public static final Map.Entry<Holder<ArmorMaterial>, String> DIAMOND = copy(ArmorMaterials.DIAMOND);
    public static final Map.Entry<Holder<ArmorMaterial>, String> NETHERITE = copy(ArmorMaterials.NETHERITE);

    private static EnumMap<Type, Integer> createMap(int[] values) {
        EnumMap<Type, Integer> enumMap = new EnumMap<>(Type.class);
        for (int i = 0; i < values.length; i++) enumMap.put(Type.values()[i], values[i]);
        return enumMap;
    }

    private static Map.Entry<Holder<ArmorMaterial>, String> copy(Holder<ArmorMaterial> material) {
        var value = material.value();
        return register(material.getRegisteredName().substring(material.getRegisteredName().indexOf(ResourceLocation.NAMESPACE_SEPARATOR) + 1), (EnumMap<Type, Integer>) value.defense(), value.enchantmentValue(), value.equipSound(), value.toughness(), value.knockbackResistance(), value.repairIngredient());
    }

    private static Map.Entry<Holder<ArmorMaterial>, String> copy(Holder<ArmorMaterial> material, String name) {
        return register(copy(material).getKey(), name);
    }

    private static Map.Entry<Holder<ArmorMaterial>, String> register(String name, EnumMap<Type, Integer> defense, int i, Holder<SoundEvent> holder, float f, float g, Supplier<Ingredient> supplier) {
        return Map.entry(MATERIALS.register(name, () -> new ArmorMaterial(defense, i, holder, supplier, List.of(new ArmorMaterial.Layer(new ResourceLocation(MODID, name))), f, g)), name);
    }

    private static Map.Entry<Holder<ArmorMaterial>, String> register(Holder<ArmorMaterial> material, String name) {
        return Map.entry(material, name);
    }

    public static void init(IEventBus bus) {
        MATERIALS.register(bus);
    }
}
