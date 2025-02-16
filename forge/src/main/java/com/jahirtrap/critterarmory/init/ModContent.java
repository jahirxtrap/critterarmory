package com.jahirtrap.critterarmory.init;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModContent {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MODID);

    public static final List<RegistryObject<Item>> IRON_MOB_ARMORS = registerMobArmors(ModMaterials.IRON, new Item.Properties());
    public static final List<RegistryObject<Item>> GOLD_MOB_ARMORS = registerMobArmors(ModMaterials.GOLD, new Item.Properties());
    public static final List<RegistryObject<Item>> DIAMOND_MOB_ARMORS = registerMobArmors(ModMaterials.DIAMOND, new Item.Properties());
    public static final List<RegistryObject<Item>> NETHERITE_MOB_ARMORS = registerMobArmors(ModMaterials.NETHERITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> BALANCED_FEED = registerItem("balanced_feed", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VITALITY_FEED = registerItem("vitality_feed", () -> new Item(new Item.Properties()));

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    private static List<RegistryObject<Item>> registerMobArmors(Map.Entry<RegistryObject<ArmorMaterial>, String> entry, Item.Properties itemProp) {
        List<RegistryObject<Item>> items = new ArrayList<>();
        if (entry.getKey() != ModMaterials.IRON.getKey() && entry.getKey() != ModMaterials.GOLD.getKey() && entry.getKey() != ModMaterials.DIAMOND.getKey())
            items.add(registerItem(entry.getValue() + "_horse_armor", () -> new BaseAnimalArmorItem.Vanilla(entry.getKey().getHolder().get(), AnimalArmorItem.BodyType.EQUESTRIAN, itemProp)));
        items.add(registerItem(entry.getValue() + "_wolf_armor", () -> new BaseAnimalArmorItem.Vanilla(entry.getKey().getHolder().get(), AnimalArmorItem.BodyType.CANINE, itemProp)));
        items.add(registerItem(entry.getValue() + "_chicken_armor", () -> new BaseAnimalArmorItem.Modded(entry.getKey().getHolder().get(), BaseAnimalArmorItem.BodyType.CHICKEN, itemProp)));
        items.add(registerItem(entry.getValue() + "_cow_armor", () -> new BaseAnimalArmorItem.Modded(entry.getKey().getHolder().get(), BaseAnimalArmorItem.BodyType.COW, itemProp)));
        items.add(registerItem(entry.getValue() + "_pig_armor", () -> new BaseAnimalArmorItem.Modded(entry.getKey().getHolder().get(), BaseAnimalArmorItem.BodyType.PIG, itemProp)));
        items.add(registerItem(entry.getValue() + "_sheep_armor", () -> new BaseAnimalArmorItem.Modded(entry.getKey().getHolder().get(), BaseAnimalArmorItem.BodyType.SHEEP, itemProp)));
        return items;
    }

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
