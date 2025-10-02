package com.jahirtrap.critterarmory.init;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModContent {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final List<Item> COPPER_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.COPPER, new Item.Properties());
    public static final List<Item> IRON_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.IRON, new Item.Properties());
    public static final List<Item> GOLD_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.GOLD, new Item.Properties());
    public static final List<Item> DIAMOND_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.DIAMOND, new Item.Properties());
    public static final List<Item> NETHERITE_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.NETHERITE, new Item.Properties().fireResistant());
    public static final Item BALANCED_FEED = registerItem("balanced_feed", Item::new, new Item.Properties());
    public static final Item VITALITY_FEED = registerItem("vitality_feed", Item::new, new Item.Properties());

    private static Item registerItem(String name, Function<Item.Properties, Item> function, Item.Properties itemProp) {
        var itemReg = Registry.register(BuiltInRegistries.ITEM, ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, name)), function.apply(itemProp.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, name)))));
        ITEMS.add(itemReg);
        return itemReg;
    }

    private static List<Item> registerMobArmors(Map.Entry<ArmorMaterial, String> entry, Item.Properties itemProp) {
        List<Item> items = new ArrayList<>();
        if (entry.getKey() != ModMaterials.MobArmor.COPPER.getKey() && entry.getKey() != ModMaterials.MobArmor.IRON.getKey() && entry.getKey() != ModMaterials.MobArmor.GOLD.getKey() && entry.getKey() != ModMaterials.MobArmor.DIAMOND.getKey())
            items.add(registerItem(entry.getValue() + "_horse_armor", (p) -> new Item(p.horseArmor(entry.getKey())), itemProp));
        items.add(registerItem(entry.getValue() + "_wolf_armor", (p) -> new BaseAnimalArmorItem(entry.getKey(), BaseAnimalArmorItem.BodyType.CANINE, p), itemProp));
        items.add(registerItem(entry.getValue() + "_cat_armor", (p) -> new BaseAnimalArmorItem(entry.getKey(), BaseAnimalArmorItem.BodyType.CAT, p), itemProp));
        items.add(registerItem(entry.getValue() + "_chicken_armor", (p) -> new BaseAnimalArmorItem(entry.getKey(), BaseAnimalArmorItem.BodyType.CHICKEN, p), itemProp));
        items.add(registerItem(entry.getValue() + "_cow_armor", (p) -> new BaseAnimalArmorItem(entry.getKey(), BaseAnimalArmorItem.BodyType.COW, p), itemProp));
        items.add(registerItem(entry.getValue() + "_pig_armor", (p) -> new BaseAnimalArmorItem(entry.getKey(), BaseAnimalArmorItem.BodyType.PIG, p), itemProp));
        items.add(registerItem(entry.getValue() + "_sheep_armor", (p) -> new BaseAnimalArmorItem(entry.getKey(), BaseAnimalArmorItem.BodyType.SHEEP, p), itemProp));
        return items;
    }

    public static void init() {
    }
}
