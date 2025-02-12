package com.jahirtrap.critterarmory.init;

import com.jahirtrap.critterarmory.item.BaseAnimalArmorItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModContent {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final List<DeferredItem<Item>> IRON_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.IRON, new Item.Properties());
    public static final List<DeferredItem<Item>> GOLD_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.GOLD, new Item.Properties());
    public static final List<DeferredItem<Item>> DIAMOND_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.DIAMOND, new Item.Properties());
    public static final List<DeferredItem<Item>> NETHERITE_MOB_ARMORS = registerMobArmors(ModMaterials.MobArmor.NETHERITE, new Item.Properties().fireResistant());
    public static final DeferredItem<Item> BALANCED_FEED = registerItem("balanced_feed", Item::new, new Item.Properties());
    public static final DeferredItem<Item> VITALITY_FEED = registerItem("vitality_feed", Item::new, new Item.Properties());

    private static DeferredItem<Item> registerItem(String name, Function<Item.Properties, Item> function, Item.Properties itemProp) {
        return ITEMS.register(name, () -> function.apply(itemProp.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, name)))));
    }

    private static List<DeferredItem<Item>> registerMobArmors(Map.Entry<ArmorMaterial, String> entry, Item.Properties itemProp) {
        List<DeferredItem<Item>> items = new ArrayList<>();
        if (entry.getKey() != ModMaterials.MobArmor.IRON.getKey() && entry.getKey() != ModMaterials.MobArmor.GOLD.getKey() && entry.getKey() != ModMaterials.MobArmor.DIAMOND.getKey())
            items.add(registerItem(entry.getValue() + "_horse_armor", (p) -> new BaseAnimalArmorItem.Vanilla(entry.getKey(), AnimalArmorItem.BodyType.EQUESTRIAN, p), itemProp));
        items.add(registerItem(entry.getValue() + "_wolf_armor", (p) -> new BaseAnimalArmorItem.Vanilla(entry.getKey(), AnimalArmorItem.BodyType.CANINE, p), itemProp));
        items.add(registerItem(entry.getValue() + "_chicken_armor", (p) -> new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.CHICKEN, p), itemProp));
        items.add(registerItem(entry.getValue() + "_cow_armor", (p) -> new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.COW, p), itemProp));
        items.add(registerItem(entry.getValue() + "_pig_armor", (p) -> new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.PIG, p), itemProp));
        items.add(registerItem(entry.getValue() + "_sheep_armor", (p) -> new BaseAnimalArmorItem.Modded(entry.getKey(), BaseAnimalArmorItem.BodyType.SHEEP, p), itemProp));
        return items;
    }

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
