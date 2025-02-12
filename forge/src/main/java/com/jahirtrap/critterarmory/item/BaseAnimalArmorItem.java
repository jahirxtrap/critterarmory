package com.jahirtrap.critterarmory.item;

import net.minecraft.core.HolderSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;

public class BaseAnimalArmorItem {
    public static class Vanilla extends AnimalArmorItem {
        public Vanilla(ArmorMaterial material, BodyType type, Properties properties) {
            super(material, type, material.equipSound(), false, properties.stacksTo(1));
        }
    }

    public static class Modded extends Item {
        public Modded(ArmorMaterial material, BodyType type, Properties properties) {
            super(material.animalProperties(properties.stacksTo(1), material.equipSound(), false, type.allowedEntities));
        }
    }

    public enum BodyType {
        CHICKEN(SoundEvents.ITEM_BREAK, EntityType.CHICKEN),
        COW(SoundEvents.ITEM_BREAK, EntityType.COW, EntityType.MOOSHROOM),
        PIG(SoundEvents.ITEM_BREAK, EntityType.PIG),
        SHEEP(SoundEvents.ITEM_BREAK, EntityType.SHEEP);

        final SoundEvent breakingSound;
        final HolderSet<EntityType<?>> allowedEntities;

        BodyType(final SoundEvent soundEvent, final EntityType<?>... entityTypes) {
            this.breakingSound = soundEvent;
            this.allowedEntities = HolderSet.direct(EntityType::builtInRegistryHolder, entityTypes);
        }
    }
}


