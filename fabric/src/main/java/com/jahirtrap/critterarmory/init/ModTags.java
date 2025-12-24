package com.jahirtrap.critterarmory.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModTags {
    public interface Items {
        TagKey<Item> HORSE_ARMOR = create(Identifier.fromNamespaceAndPath(MODID, "horse_armor"));
        TagKey<Item> WOLF_ARMOR = create(Identifier.fromNamespaceAndPath(MODID, "wolf_armor"));
        TagKey<Item> CAT_ARMOR = create(Identifier.fromNamespaceAndPath(MODID, "cat_armor"));
        TagKey<Item> CHICKEN_ARMOR = create(Identifier.fromNamespaceAndPath(MODID, "chicken_armor"));
        TagKey<Item> COW_ARMOR = create(Identifier.fromNamespaceAndPath(MODID, "cow_armor"));
        TagKey<Item> PIG_ARMOR = create(Identifier.fromNamespaceAndPath(MODID, "pig_armor"));
        TagKey<Item> SHEEP_ARMOR = create(Identifier.fromNamespaceAndPath(MODID, "sheep_armor"));

        private static TagKey<Item> create(Identifier name) {
            return TagKey.create(Registries.ITEM, name);
        }
    }
}
