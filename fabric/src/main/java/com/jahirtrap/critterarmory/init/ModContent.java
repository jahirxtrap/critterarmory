package com.jahirtrap.critterarmory.init;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import com.jahirtrap.critterarmory.item.BaseItem;
import com.jahirtrap.critterarmory.util.AnimalMaterial;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModContent {
    public static final List<Item> IRON_MOB_ARMORS = registerMobArmors(ModMaterials.IRON, new Item.Properties());
    public static final List<Item> GOLD_MOB_ARMORS = registerMobArmors(ModMaterials.GOLD, new Item.Properties());
    public static final List<Item> DIAMOND_MOB_ARMORS = registerMobArmors(ModMaterials.DIAMOND, new Item.Properties());
    public static final List<Item> NETHERITE_MOB_ARMORS = registerMobArmors(ModMaterials.NETHERITE, new Item.Properties().fireResistant());
    public static final Item BALANCED_FEED = registerItem("balanced_feed", new BaseItem(new Item.Properties()));
    public static final Item VITALITY_FEED = registerItem("vitality_feed", new BaseItem(new Item.Properties()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(MODID, name), item);
    }

    private static List<Item> registerMobArmors(AnimalMaterial material, Item.Properties itemProp) {
        List<Item> items = new ArrayList<>();
        if (material != ModMaterials.IRON && material != ModMaterials.GOLD && material != ModMaterials.DIAMOND)
            items.add(registerItem(material.getName() + "_horse_armor", new BaseAnimalArmorItem.Vanilla(material, itemProp)));
        items.add(registerItem(material.getName() + "_wolf_armor", new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.CANINE, itemProp)));
        items.add(registerItem(material.getName() + "_chicken_armor", new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.CHICKEN, itemProp)));
        items.add(registerItem(material.getName() + "_cow_armor", new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.COW, itemProp)));
        items.add(registerItem(material.getName() + "_pig_armor", new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.PIG, itemProp)));
        items.add(registerItem(material.getName() + "_sheep_armor", new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.SHEEP, itemProp)));
        return items;
    }

    public static void init() {
    }
}
