package com.jahirtrap.critterarmory.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModTags {
    public interface Items {
        TagKey<Item> HORSE_ARMOR = create(ResourceLocation.fromNamespaceAndPath(MODID, "horse_armor"));
        TagKey<Item> WOLF_ARMOR = create(ResourceLocation.fromNamespaceAndPath(MODID, "wolf_armor"));
        TagKey<Item> CAT_ARMOR = create(ResourceLocation.fromNamespaceAndPath(MODID, "cat_armor"));
        TagKey<Item> CHICKEN_ARMOR = create(ResourceLocation.fromNamespaceAndPath(MODID, "chicken_armor"));
        TagKey<Item> COW_ARMOR = create(ResourceLocation.fromNamespaceAndPath(MODID, "cow_armor"));
        TagKey<Item> PIG_ARMOR = create(ResourceLocation.fromNamespaceAndPath(MODID, "pig_armor"));
        TagKey<Item> SHEEP_ARMOR = create(ResourceLocation.fromNamespaceAndPath(MODID, "sheep_armor"));

        private static TagKey<Item> create(ResourceLocation name) {
            return TagKey.create(Registries.ITEM, name);
        }
    }
}
