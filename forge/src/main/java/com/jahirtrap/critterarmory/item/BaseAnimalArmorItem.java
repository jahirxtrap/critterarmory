package com.jahirtrap.critterarmory.item;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

public class BaseAnimalArmorItem extends Item {
    public BaseAnimalArmorItem(ArmorMaterial material, BodyType type, Properties properties) {
        super(properties.attributes(material.createAttributes(ArmorType.BODY)).repairable(material.repairIngredient()).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.BODY).setEquipSound(material.equipSound()).setDamageOnHurt(false).setAsset(material.assetId()).setAllowedEntities(type.allowedEntities).setEquipOnInteract(true).setCanBeSheared(true).setShearingSound(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.ARMOR_UNEQUIP_WOLF)).build()).component(DataComponents.BREAK_SOUND, type.breakingSound).stacksTo(1));
    }

    public enum BodyType {
        CANINE(SoundEvents.WOLF_ARMOR_BREAK, EntityType.WOLF),
        CAT(SoundEvents.ITEM_BREAK, EntityType.CAT),
        CHICKEN(SoundEvents.ITEM_BREAK, EntityType.CHICKEN),
        COW(SoundEvents.ITEM_BREAK, EntityType.COW, EntityType.MOOSHROOM),
        PIG(SoundEvents.ITEM_BREAK, EntityType.PIG),
        SHEEP(SoundEvents.ITEM_BREAK, EntityType.SHEEP);

        public final Holder<SoundEvent> breakingSound;
        public final HolderSet<EntityType<?>> allowedEntities;

        BodyType(final Holder<SoundEvent> soundEvent, final EntityType<?>... entityTypes) {
            this.breakingSound = soundEvent;
            this.allowedEntities = HolderSet.direct(EntityType::builtInRegistryHolder, entityTypes);
        }
    }
}
