package com.jahirtrap.critterarmory.init;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import com.jahirtrap.critterarmory.item.BaseItem;
import com.jahirtrap.critterarmory.util.AnimalMaterial;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModContent {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registry.ITEM_REGISTRY, MODID);

    public static final List<RegistryObject<Item>> IRON_MOB_ARMORS = registerMobArmors(ModMaterials.IRON, new Item.Properties());
    public static final List<RegistryObject<Item>> GOLD_MOB_ARMORS = registerMobArmors(ModMaterials.GOLD, new Item.Properties());
    public static final List<RegistryObject<Item>> DIAMOND_MOB_ARMORS = registerMobArmors(ModMaterials.DIAMOND, new Item.Properties());
    public static final List<RegistryObject<Item>> NETHERITE_MOB_ARMORS = registerMobArmors(ModMaterials.NETHERITE, new Item.Properties().fireResistant());
    public static final RegistryObject<Item> BALANCED_FEED = registerItem("balanced_feed", () -> new BaseItem(new Item.Properties()));
    public static final RegistryObject<Item> VITALITY_FEED = registerItem("vitality_feed", () -> new BaseItem(new Item.Properties()));

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    private static List<RegistryObject<Item>> registerMobArmors(AnimalMaterial material, Item.Properties itemProp) {
        List<RegistryObject<Item>> items = new ArrayList<>();
        if (material != ModMaterials.IRON && material != ModMaterials.GOLD && material != ModMaterials.DIAMOND)
            items.add(registerItem(material.getName() + "_horse_armor", () -> new BaseAnimalArmorItem.Vanilla(material, itemProp)));
        items.add(registerItem(material.getName() + "_wolf_armor", () -> new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.CANINE, itemProp)));
        items.add(registerItem(material.getName() + "_chicken_armor", () -> new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.CHICKEN, itemProp)));
        items.add(registerItem(material.getName() + "_cow_armor", () -> new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.COW, itemProp)));
        items.add(registerItem(material.getName() + "_pig_armor", () -> new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.PIG, itemProp)));
        items.add(registerItem(material.getName() + "_sheep_armor", () -> new BaseAnimalArmorItem.Modded(material, BaseAnimalArmorItem.BodyType.SHEEP, itemProp)));
        return items;
    }

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
