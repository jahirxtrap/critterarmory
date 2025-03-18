package com.jahirtrap.critterarmory.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static com.jahirtrap.critterarmory.CritterArmoryMod.MODID;

public class ModTags {
    public interface Items {
        TagKey<Item> HORSE_ARMOR = create(new ResourceLocation(MODID, "horse_armor"));
        TagKey<Item> WOLF_ARMOR = create(new ResourceLocation(MODID, "wolf_armor"));
        TagKey<Item> CAT_ARMOR = create(new ResourceLocation(MODID, "cat_armor"));
        TagKey<Item> CHICKEN_ARMOR = create(new ResourceLocation(MODID, "chicken_armor"));
        TagKey<Item> COW_ARMOR = create(new ResourceLocation(MODID, "cow_armor"));
        TagKey<Item> PIG_ARMOR = create(new ResourceLocation(MODID, "pig_armor"));
        TagKey<Item> SHEEP_ARMOR = create(new ResourceLocation(MODID, "sheep_armor"));

        private static TagKey<Item> create(ResourceLocation name) {
            return TagKey.create(Registries.ITEM, name);
        }
    }
}
