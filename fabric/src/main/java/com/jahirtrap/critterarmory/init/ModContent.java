package com.jahirtrap.critterarmory.init;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModContent {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final List<Item> IRON_MOB_ARMORS = registerMobArmors(ModMaterials.IRON, new Item.Properties());
    public static final List<Item> GOLD_MOB_ARMORS = registerMobArmors(ModMaterials.GOLD, new Item.Properties());
    public static final List<Item> DIAMOND_MOB_ARMORS = registerMobArmors(ModMaterials.DIAMOND, new Item.Properties());
    public static final List<Item> NETHERITE_MOB_ARMORS = registerMobArmors(ModMaterials.NETHERITE, new Item.Properties().fireResistant());
    public static final Item BALANCED_FEED = registerItem("balanced_feed", new Item(new Item.Properties()));
    public static final Item VITALITY_FEED = registerItem("vitality_feed", new Item(new Item.Properties()));

    private static Item registerItem(String name, Item item) {
        var itemReg = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, name), item);
        ITEMS.add(itemReg);
        return itemReg;
    }

    private static List<Item> registerMobArmors(Map.Entry<Holder<ArmorMaterial>, String> entry, Item.Properties itemProp) {
        List<Item> items = new ArrayList<>();
        if (entry.getKey() != ModMaterials.IRON.getKey() && entry.getKey() != ModMaterials.GOLD.getKey() && entry.getKey() != ModMaterials.DIAMOND.getKey())
            items.add(registerItem(entry.getValue() + "_horse_armor", new BaseAnimalArmorItem.Vanilla(entry.getKey(), AnimalArmorItem.BodyType.EQUESTRIAN, itemProp)));
        items.add(registerItem(entry.getValue() + "_wolf_armor", new BaseAnimalArmorItem.Vanilla(entry.getKey(), AnimalArmorItem.BodyType.CANINE, itemProp)));
        items.add(registerItem(entry.getValue() + "_cat_armor", new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.CAT, itemProp)));
        items.add(registerItem(entry.getValue() + "_chicken_armor", new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.CHICKEN, itemProp)));
        items.add(registerItem(entry.getValue() + "_cow_armor", new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.COW, itemProp)));
        items.add(registerItem(entry.getValue() + "_pig_armor", new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.PIG, itemProp)));
        items.add(registerItem(entry.getValue() + "_sheep_armor", new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.SHEEP, itemProp)));
        return items;
    }

    public static void init() {
    }
}
