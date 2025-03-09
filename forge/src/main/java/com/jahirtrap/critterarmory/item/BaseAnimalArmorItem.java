package com.jahirtrap.critterarmory.item;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class BaseAnimalArmorItem {
    public static class Vanilla extends AnimalArmorItem {
        private final ResourceLocation textureLocation;

        public Vanilla(Holder<ArmorMaterial> material, BodyType type, Properties properties) {
            super(material, type, false, properties.stacksTo(1));
            this.textureLocation = new ResourceLocation(material.getRegisteredName()).withPath(path -> "textures/entity/equipment/" + (type == BodyType.EQUESTRIAN ? "horse_body" : "wolf_body") + "/" + path + ".png");
        }

        @Override
        public ResourceLocation getTexture() {
            return this.textureLocation;
        }
    }

    public static class Modded extends ArmorItem {
        private final BodyType bodyType;

        public Modded(Holder<ArmorMaterial> material, BodyType type, Properties properties) {
            super(material, ArmorItem.Type.BODY, properties.stacksTo(1));
            this.bodyType = type;
        }

        public HolderSet<EntityType<?>> getAllowedEntities() {
            return this.bodyType.allowedEntities;
        }

        @Override
        public SoundEvent getBreakingSound() {
            return this.bodyType.breakingSound;
        }

        @Override
        public boolean isEnchantable(ItemStack stack) {
            return false;
        }
    }

    public enum BodyType {
        CHICKEN(SoundEvents.ITEM_BREAK, EntityType.CHICKEN),
        COW(SoundEvents.ITEM_BREAK, EntityType.COW, EntityType.MOOSHROOM),
        PIG(SoundEvents.ITEM_BREAK, EntityType.PIG),
        SHEEP(SoundEvents.ITEM_BREAK, EntityType.SHEEP);

        public final SoundEvent breakingSound;
        public final HolderSet<EntityType<?>> allowedEntities;

        BodyType(final SoundEvent soundEvent, final EntityType<?>... entityTypes) {
            this.breakingSound = soundEvent;
            this.allowedEntities = HolderSet.direct(EntityType::builtInRegistryHolder, entityTypes);
        }
    }
}
